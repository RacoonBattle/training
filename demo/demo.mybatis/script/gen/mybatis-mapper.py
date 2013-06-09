#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os, sys, getpass, MySQLdb, _mysql_exceptions as dbexceptions

def toCamel(text):
    '''
    转换下划线命名为驼峰式, 如：user_id => userId
    '''
    items = text.split('_')
    for i in range(len(items)):
        items[i] = items[i].capitalize()
    return items[0].lower() + ''.join(items[1:])

def toPascal(text):
    '''
    转换下划线命名为帕斯卡式, 如：user_id => UserId
    '''
    items = text.split('_')
    for i in range(len(items)):
        items[i] = items[i].capitalize()
    return ''.join(items)

def toJdbcType(dbType):
    '''
    转换数据库字段类型为Java类型, 如: varchar(10) => String
    '''
    if dbType.startswith('int') or dbType.startswith('short') or dbType.startswith('enum'):
        return 'Integer'

    if dbType.startswith('varchar') or dbType.startswith('char'):
        return 'String'

    if dbType.startswith('decimal') or dbType.startswith('long'):
        return 'Long'

    return 'String'

class Field(object):
    '''
    字段描述
    '''

    def __init__(self, name, jdbcType, comment=''):
        '''
        构造函数
        '''
        self.name = name;
        self.jdbcType = jdbcType
        self.comment = comment

    def setName(self, name):
        '''
        设置字段名称
        '''
        self.name = name

    def setJdbcType(self, jdbcType):
        '''
        设置Jdbc类型
        '''
        self.jdbcType = jdbcType

    def setComment(self, comment):
        '''
        设置字段说明
        '''
        self.comment = comment

    def __str__(self):
        return 'name=%s, jdbcType=%s, comment=%s' % (self.name, self.jdbcType, self.comment)

class Table(object):
    '''
    类结构
    '''

    def __init__(self, name, fields, comment=''):
        '''
        构造函数
        '''
        self.name = name
        self.fields = fields
        self.comment = comment

    def setName(self, name):
        '''
        设置类名称
        '''
        self.name = name

    def setFields(self, fields):
        '''
        设置字段
        '''
        self.fields = fields

    def setComment(self, comment):
        '''
        设置表说明
        '''
        self.comment = comment

    def __str__(self):
        fieldText = ''
        for field in self.fields:
            fieldText += field.__str__()
        return 'name=%s, fields=%s, comment=%s' % (self.name, fieldText, self.comment)

    def toJava(self, package, author):
        '''
        生成Java类文件
        '''
        namePascal = toPascal(self.name)

        o = file(namePascal + '.java', 'w')

        o.write('/*\n')
        o.write(' * $Id$\n')
        o.write(' *\n')
        o.write(' * Copyright (c) 2011 demo.com. All Rights Reserved.\n')
        o.write(' */\n')
        if package:
            o.write('package ' + package + ';\n')
        o.write('\n')
        o.write('/**\n')
        if self.comment:
            o.write(' * ' + self.comment + '\n')
            o.write(' *\n')
        if author:
            o.write(' * @author ' + author + '\n')
        o.write(' */\n')
        o.write('public class ' + namePascal + ' {\n')

        # 输出字段
        for field in self.fields:
            o.write('\n')
            if field.comment:
                o.write('    /**\n')
                o.write('     * ' + field.comment + '\n')
                o.write('     */\n')
            o.write('    private ' + field.jdbcType + ' ' + toCamel(field.name) + ';\n')


        # 输出方法
        for field in self.fields:
            fieldNameCamel = toCamel(field.name)
            fieldNamePascal = toPascal(field.name)
            o.write('\n')
            if field.comment:
                o.write('    /**\n')
                o.write('     * 获取' + field.comment + '\n')
                o.write('     * \n')
                o.write('     * @return ' + field.comment + '\n')
                o.write('     */\n')
            o.write('    public ' + field.jdbcType + ' get' + fieldNamePascal + '() {\n')
            o.write('        return ' + fieldNameCamel + ';\n')
            o.write('    }\n')
            o.write('\n')
            if field.comment:
                o.write('    /**\n')
                o.write('     * 设置' + field.comment + '\n')
                o.write('     * \n')
                o.write('     * @param ' + fieldNameCamel + ' ' + field.comment + '\n')
                o.write('     */\n')
            o.write('    public void set' + fieldNamePascal + '(' + field.jdbcType + ' ' + fieldNameCamel +') {\n')
            o.write('        this.' + fieldNameCamel + ' = ' + fieldNameCamel + ';\n')
            o.write('    }\n')

        # 重载toString
        o.write('\n')
        o.write('    /* (non-Javadoc)\n')
        o.write('     * @see java.lang.Object#toString()\n')
        o.write('     */\n')
        o.write('    @Override\n')
        o.write('    public String toString() {\n')
        o.write('        StringBuilder builder = new StringBuilder();\n')
        for i in range(len(self.fields)):
            #if (field.jdbcType.endswith('[]')):
            #    o.write('        buffer.append(\", ' + field.name + '=\").append(Arrays.toString(' + field.name + '));\n')
            #else:
            fieldNameCamel = toCamel(self.fields[i].name)
            if i == 0:
                o.write('        buffer.append(\"' + fieldNameCamel + '=\").append(' + fieldNameCamel + ');\n')
            else:
                o.write('        buffer.append(\", ' + fieldNameCamel + '=\").append(' + fieldNameCamel + ');\n')
        o.write('        return buffer.toString();\n')
        o.write('    }\n')
        o.write('}\n')
        o.close()

    def toMapper(self, package):
        '''
        生成MyBatis配置文件
        '''
        fieldsLen = len(self.fields)
        typeName = toCamel(self.name)
        if package:
            typeName = package + '.' + typeName

        o = file(toCamel(self.name) + '.xml', 'w')

        o.write('<?xml version="1.0" encoding="UTF-8" ?>\n')
        o.write('<!DOCTYPE mapper\n')
        o.write('        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"\n')
        o.write('        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n')
        o.write('<mapper namespace="' + toCamel(self.name) + '">\n')
        o.write('\n')
        o.write('    <select id="get"\n')
        o.write('        parameterType="java.util.HashMap"\n')
        o.write('        resultType="' + typeName + '">\n')
        o.write('        <![CDATA[\n')
        o.write('            select\n')
        for i in range(fieldsLen):
            field = self.fields[i]
            if i < fieldsLen - 1:
                o.write('              `' + field.name + '` as ' + toCamel(field.name) + ',\n')
            else:
                o.write('              `' + field.name + '` as ' + toCamel(field.name) + '\n')    
        o.write('            from \n')
        o.write('                `' + self.name + '`\n')
        o.write('        ]]>\n')
        o.write('        <where>\n')
        for field in self.fields:
            fieldNameCamel = toCamel(field.name)
            o.write('            <if test="' + fieldNameCamel + ' != null and ' + fieldNameCamel + ' != ''">\n')
            o.write('                and `' + field.name + '` = #{' + fieldNameCamel + '}\n')
            o.write('            </if>\n')
        o.write('        </where>\n')
        o.write('    </select>\n')
        o.write('\n')
        o.write('    <insert id="insert"\n')
        o.write('        parameterType="' + typeName + '">\n')
        o.write('        <![CDATA[\n')
        o.write('        insert into `' + self.name + '` (\n')
        for i in range(fieldsLen):
            field = self.fields[i]
            if i < fieldsLen - 1:
                o.write('            `' + field.name + '`,\n')
            else:
                o.write('            `' + field.name + '`\n')
        o.write('        ) values (\n')
        for i in range(fieldsLen):
            field = self.fields[i]
            if i < fieldsLen - 1:
                o.write('            #{' + toCamel(field.name) + '},\n')
            else:
                o.write('            #{' + toCamel(field.name) + '}\n')
        o.write('        )\n')
        o.write('        ]]>\n')
        #o.write('        <selectKey resultType="java.lang.Long" keyProperty="id">\n')
        #o.write('            select @@IDENTITY as value\n')
        #o.write('        </selectKey>\n')
        o.write('    </insert>\n')
        o.write('\n')
        o.write('    <update id="update"\n')
        o.write('        parameterType="java.util.HashMap">\n')
        o.write('        <![CDATA[\n')
        o.write('        update\n')
        o.write('            `' + self.name + '`\n')
        o.write('        ]]>\n')
        o.write('        <set>\n')
        for field in self.fields:
            o.write('            `' + field.name + '` = #{' + toCamel(field.name) + '},\n')
        o.write('        </set>\n')
        o.write('        where\n')
        o.write('            `id` = #{id, jdbcType=INTEGER}\n')
        o.write('    </update>\n')
        o.write('</mapper>\n')

        o.close()

def main():

    host = ''
    user = ''
    password = ''
    database = ''
    package = ''
    author = ''

    # 读取命令行参数
    # --help

    # 读取配置文件
    try:
        config = file('config', 'r')
    except IOError, e:
        pass

    # 验证配置
    while not host:
        host = raw_input('Database Server: ')
    while not user:
        user = raw_input('User: ')
    #while not password:
        password = raw_input('Password: ')

    # 连接数据库
    conn = None
    try:
        conn = MySQLdb.connect(host, user, password)
    except dbexceptions.OperationalError, e:
        sys.exit(e)

    cursor = conn.cursor()

    # 获取数据库列表
    if not database:
        cursor.execute('show databases')
        databases = cursor.fetchall()
        print 'Choose a database:\n'
        for item in databases:
            # +----------+
            # | Database |
            # +----------+
            print item[0]
        while not database:
            database = raw_input('\nEnter choice: ')

    cursor.execute('use `' + database + '`');
    cursor.execute('show table status')
    # +------+--------+---------+------------+------+----------------+-------------+-----------------+--------------+-----------+----------------+-------------+-------------+------------+-----------+----------+----------------+---------+
    # | Name | Engine | Version | Row_format | Rows | Avg_row_length | Data_length | Max_data_length | Index_length | Data_free | Auto_increment | Create_time | Update_time | Check_time | Collation | Checksum | Create_options | Comment |
    # +------+--------+---------+------------+------+----------------+-------------+-----------------+--------------+-----------+----------------+-------------+-------------+------------+-----------+----------+----------------+---------+
    tables = cursor.fetchall()
    if len(tables) == 0:
        sys.exit('No table found.')

    # 包名
    package = raw_input('Enter package: ')

    # 作者
    if not author:
        author = raw_input('Enter author: ')
    if not author:
        author = getpass.getuser()

    for item in tables:
        cursor.execute('show full fields from ' + item[0])
        fields = []
        for column in cursor.fetchall():
            # +-------+------+-----------+------+-----+---------+-------+------------+---------+
            # | Field | Type | Collation | Null | Key | Default | Extra | Privileges | Comment |
            # +-------+------+-----------+------+-----+---------+-------+------------+---------+
            fields.append(Field(column[0], toJdbcType(column[1]), column[8]))

        table = Table(item[0], fields, item[17])
        table.toJava(package, author)
        table.toMapper(package)

    conn.close()

if __name__ == '__main__':
    main()
