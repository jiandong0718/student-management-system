# 学生管理系统

基于Spring Boot的学生管理系统，采用多模块架构，正在向DDD微服务架构演进。

## 项目架构

* **common模块**: 公共组件、配置和工具类
* **student模块**: 学生管理功能
* **teacher模块**: 教师管理功能

## 技术栈

* Spring Boot 2.7.18
* MyBatis-Plus 3.5.3.1
* MySQL
* Redis
* Kafka
* Spring Cloud Alibaba

## 待修复问题

1. StudentServiceImpl中的方法未正确实现，导致CRUD操作不可用
2. 依赖版本存在不一致问题（Spring Boot 2.x vs Spring Boot 3.x组件）
3. Teacher实体类表名和表前缀配置不一致
4. 缺少事务管理注解
5. 数据库安全配置不足（Druid弱密码、Redis无密码配置）
6. 接口设计未使用DTO模式，直接暴露实体类

## 如何运行

### 环境要求
* JDK 8+
* Maven 3.6+
* MySQL 5.7+
* Redis 6+

### 启动步骤
1. 创建数据库并执行初始化SQL脚本 (student/src/main/resources/db/init.sql)
2. 修改配置文件中的数据库连接信息
3. 运行 `mvn clean install` 安装依赖
4. 分别启动各个模块：
   ```
   cd student
   mvn spring-boot:run
   ```

## DDD微服务架构演进

项目正在按照DDD（领域驱动设计）原则进行重构，详见 [DDD架构文档](doc/DDD.md)。 
