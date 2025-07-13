# 学生管理系统

基于Spring Boot 3.5.3、MyBatis-Plus和Druid的学生管理系统。

## 项目特点

- 基于Spring Boot 3.5.3、Java 17
- 集成MyBatis-Plus，简化数据库操作
- 使用Druid连接池，提供监控功能
- 多环境配置：dev、test、prod
- RESTful API设计
- 统一响应结果和全局异常处理

## 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

## 项目结构

```
student/
├── src/main/java/com/example/student/
│   ├── common/                      # 通用组件
│   │   ├── GlobalExceptionHandler.java  # 全局异常处理器
│   │   └── Result.java              # 统一响应结果
│   ├── config/                      # 配置类
│   │   ├── MybatisPlusConfig.java   # MyBatis-Plus配置
│   │   └── MyMetaObjectHandler.java # 字段自动填充
│   ├── controller/                  # 控制器层
│   │   └── StudentController.java   # 学生控制器
│   ├── entity/                      # 实体类
│   │   └── Student.java             # 学生实体
│   ├── mapper/                      # 数据访问层
│   │   └── StudentMapper.java       # 学生Mapper
│   ├── service/                     # 服务层
│   │   ├── StudentService.java      # 学生服务接口
│   │   └── impl/                    # 服务实现
│   │       └── StudentServiceImpl.java  # 学生服务实现
│   └── StudentApplication.java      # 应用启动类
├── src/main/resources/
│   ├── mapper/                      # MyBatis XML映射文件
│   │   └── StudentMapper.xml        # 学生Mapper XML
│   ├── db/
│   │   └── init.sql                 # 数据库初始化脚本
│   ├── application.yml              # 主配置文件
│   ├── application-dev.yml          # 开发环境配置
│   ├── application-test.yml         # 测试环境配置
│   └── application-prod.yml         # 生产环境配置
└── pom.xml                          # Maven配置
```

## 快速开始

### 1. 克隆项目

```bash
git clone [项目地址]
cd student-management/student
```

### 2. 数据库准备

- 创建数据库：
  ```sql
  CREATE DATABASE student;
  ```

- 执行初始化脚本：
  ```sql
  mysql -u username -p student < src/main/resources/db/init.sql
  ```

### 3. 修改配置

根据实际环境修改`application-dev.yml`、`application-test.yml`和`application-prod.yml`中的数据库连接信息。

### 4. 启动应用

```bash
# 开发环境（默认）
mvn spring-boot:run

# 测试环境
mvn spring-boot:run -Dspring.profiles.active=test

# 生产环境
mvn spring-boot:run -Dspring.profiles.active=prod
```

### 5. 访问应用

- API地址: http://localhost:8080/student/api/students
- Druid监控: http://localhost:8080/student/druid (用户名: admin, 密码: admin123)

## API文档

### 学生管理

- 获取学生列表
  ```
  GET /student/api/students
  ```

- 分页查询学生
  ```
  GET /student/api/students/page?current=1&size=10&name=张三
  ```

- 获取单个学生
  ```
  GET /student/api/students/{id}
  ```

- 创建学生
  ```
  POST /student/api/students
  ```

- 更新学生
  ```
  PUT /student/api/students/{id}
  ```

- 删除学生
  ```
  DELETE /student/api/students/{id}
  ```

## 多环境配置说明

- `application-dev.yml`: 开发环境，默认配置，包含详细日志
- `application-test.yml`: 测试环境，适用于QA测试
- `application-prod.yml`: 生产环境，优化连接池配置，关闭详细日志 