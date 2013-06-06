## 如何运行##
1. 使用sql/schema.sql创建数据库表
2. 根据实际调整src/main/resources/spring/dao.xml中dataSource配置
3. 在mybatis-web目录下命令行运行mvn jetty:run
4. 访问http://localhost:8080/=/view/account/name/tom 查看信息
