1. 如何运行
使用sql/schema.sql创建数据库表
根据实际调整src/main/resources/spring/dao.xml中dataSource配置
在mybatis-web目录下命令行运行mvn jetty:run
访问http://localhost:8080/=/view/user/name/tom 查看信息