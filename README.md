# 🎓 Student Management System | 学生管理系统

[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2021.0.8-green.svg)](https://spring.io/projects/spring-cloud)
[![MyBatis Plus](https://img.shields.io/badge/MyBatis%20Plus-3.5.3.1-red.svg)](https://baomidou.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-6.0+-red.svg)](https://redis.io/)
[![Kafka](https://img.shields.io/badge/Kafka-2.8.9-yellow.svg)](https://kafka.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

> 🚀 基于Spring Boot 2.7 + Spring Cloud 2021.x构建的多模块微服务学生管理系统，采用领域驱动设计（DDD），集成Redis、Kafka等主流中间件，专为教育机构设计的现代化管理平台。

## 📋 项目简介

本项目是一个现代化的教育机构管理系统，采用**多模块Maven项目架构**，基于Spring Boot 3.x和Spring Cloud 2023.x技术栈构建。系统采用领域驱动设计（DDD）思想，实现了学生服务和教师服务的独立部署，为教育机构提供高效、稳定、可扩展的管理解决方案。

### 🎯 核心特性

- **🏗️ 多模块架构**：Maven多模块项目，支持独立开发、测试、部署
- **⚡ 微服务设计**：服务间松耦合，支持水平扩展
- **🔐 多环境配置**：dev/test/prod环境配置分离，灵活部署
- **👨‍🎓 学生管理**：完整的学生档案管理和学习跟踪
- **👨‍🏫 教师管理**：教师档案、绩效评估、薪资管理
- **📚 课程管理**：课程库管理、教学资源整合
- **🏫 班级管理**：智能分班、班级动态监控
- **📊 数据分析**：多维度运营数据分析，智能决策支持
- **⚡ 高性能缓存**：Redis双客户端支持（Jedis + Redisson）
- **🔄 异步处理**：Kafka消息队列，系统解耦
- **📱 API文档**：集成Swagger，完整的API文档
- **🛡️ 服务治理**：预留Nacos配置，支持服务注册发现

## 🏗️ 系统架构

### 技术栈

| 层次 | 技术 | 版本 | 说明 |
|------|------|------|------|
| **后端框架** | Spring Boot | 2.7.x | 核心框架 |
| **微服务框架** | Spring Cloud | 2021.x | 微服务生态 |
| **服务调用** | OpenFeign | 3.x | 服务间通信 |
| **数据访问** | MyBatis Plus | 3.5.3.x | ORM框架 |
| **关系型数据库** | MySQL | 8.0+ | 主数据库 |
| **分布式缓存** | Redis | 6.0+ | 分布式缓存 |
| **本地缓存** | Caffeine | 3.x | 高性能本地缓存 |
| **注册中心** | Nacos | 2021.x | 服务注册与发现 |
| **配置中心** | Nacos | 2021.x | 分布式配置管理 |
| **任务调度** | XXL-Job | 2.4.x | 分布式任务调度 |
| **消息队列** | Kafka | 2.8.x | 消息分发与异步解耦 |
| **安全框架** | Spring Security | 5.x | 认证授权 |
| **文档工具** | Springdoc OpenAPI | 1.8.x | API文档 |
| **构建工具** | Maven | 3.8+ | 项目构建 |
| **Java版本** | JDK | 8 | 运行环境 |

### 项目架构

```
┌─────────────────────────────────────────────────────────────┐
│                    Gateway (预留)                           │
├─────────────────────────────────────────────────────────────┤
│  Student Service (8081)     │  Teacher Service (8082)       │
│  ├─ REST API               │  ├─ REST API                  │
│  ├─ Business Logic         │  ├─ Business Logic            │
│  ├─ Data Access           │  ├─ Data Access               │
│  └─ MySQL Database         │  └─ MySQL Database            │
├─────────────────────────────────────────────────────────────┤
│  Common Module (共享基础设施)                                │
│  ├─ Base Entities          ├─ API Response封装             │
│  ├─ Redis配置              ├─ Kafka配置                   │
│  ├─ Exception处理          ├─ 工具类                      │
├─────────────────────────────────────────────────────────────┤
│  Infrastructure (基础设施)                                   │
│  ├─ Redis (缓存)           ├─ Kafka (消息队列)             │
│  ├─ MySQL (数据库)         ├─ Nacos (服务治理-预留)        │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 快速开始

### 环境要求

- **JDK 8+**
- **Maven 3.8+**
- **MySQL 8.0+**
- **Redis 6.0+** (必需)
- **Kafka 2.8.x** (可选)
- **Nacos 2021.x** (可选，目前已禁用)

### 安装与运行

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/student-management.git
   cd student-management
   ```

2. **数据库配置**
   ```sql
   # 创建数据库
   CREATE DATABASE student CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE DATABASE teacher CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   
   # 可选：创建测试和生产环境数据库
   CREATE DATABASE student_db_test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE DATABASE teacher_db_test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE DATABASE student_db_prod CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE DATABASE teacher_db_prod CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **修改配置文件**
   ```bash
   # 修改开发环境配置
   vim student-service/src/main/resources/application-dev.yml
   vim teacher-service/src/main/resources/application-dev.yml
   
   # 更新数据库密码
   spring:
     datasource:
       password: your_mysql_password
   ```

4. **启动基础服务**
   ```bash
   # 启动Redis (必需)
   redis-server
   
   # 启动Kafka (可选)
   bin/kafka-server-start.sh config/server.properties
   ```

5. **编译和运行**
   ```bash
   # 方式1：使用脚本（推荐）
   ./build.sh        # 编译项目
   ./run-services.sh # 启动所有服务
   
   # 方式2：手动编译运行
   ./mvnw clean install -DskipTests
   java -jar student-service/target/student-service-1.0.0.jar &
   java -jar teacher-service/target/teacher-service-1.0.0.jar &
   
   # 方式3：使用Maven插件
   cd student-service && ../mvnw spring-boot:run &
   cd teacher-service && ../mvnw spring-boot:run &
   ```

6. **验证服务**
   ```bash
   # 检查服务状态
   curl http://localhost:8081/actuator/health
   curl http://localhost:8082/actuator/health
   
   # 查看服务信息
   curl http://localhost:8081/actuator/info
   curl http://localhost:8082/actuator/info
   ```

## 📦 项目结构

```
student-management/
├── pom.xml                           # 父POM聚合器
├── common/                           # 通用模块
│   ├── pom.xml
│   └── src/main/java/com/example/common/
│       ├── config/
│       │   ├── GlobalExceptionHandler.java
│       │   ├── KafkaConfig.java     # Kafka配置
│       │   └── RedisConfig.java     # Redis配置（Jedis+Redisson）
│       ├── model/                   # 基础模型
│       │   └── BaseEntity.java
│       ├── response/                # 响应封装
│       │   └── Result.java
│       └── util/                    # 工具类
├── student/                         # 学生服务模块 (端口8081)
│   ├── pom.xml
│   ├── src/main/java/com/example/student/
│   │   ├── common/                  # 通用组件
│   │   ├── config/                  # 配置类
│   │   │   ├── MybatisConfig.java
│   │   │   ├── MybatisPlusConfig.java
│   │   │   └── MyMetaObjectHandler.java
│   │   ├── entity/                  # 学生实体
│   │   │   └── Student.java
│   │   ├── mapper/                  # 数据访问层
│   │   │   └── StudentMapper.java
│   │   ├── service/                 # 业务逻辑层
│   │   │   ├── impl/
│   │   │   │   └── StudentServiceImpl.java
│   │   │   └── StudentService.java
│   │   ├── controller/              # 控制器层
│   │   │   └── StudentController.java
│   │   └── StudentApplication.java
│   └── src/main/resources/
│       ├── application.yml          # 主配置
│       ├── application-dev.yml      # 开发环境配置
│       ├── application-test.yml     # 测试环境配置
│       ├── application-prod.yml     # 生产环境配置
│       └── mapper/                  # MyBatis XML映射文件
│           └── StudentMapper.xml
├── teacher/                         # 教师服务模块 (端口8082)
│   ├── pom.xml
│   ├── src/main/java/com/example/teacher/
│   │   ├── common/                  # 通用组件
│   │   ├── config/                  # 配置类
│   │   │   └── MybatisPlusConfig.java
│   │   ├── entity/                  # 教师实体
│   │   │   └── Teacher.java
│   │   ├── mapper/                  # 数据访问层
│   │   │   └── TeacherMapper.java
│   │   ├── service/                 # 业务逻辑层
│   │   │   ├── impl/
│   │   │   │   └── TeacherServiceImpl.java
│   │   │   └── TeacherService.java
│   │   ├── controller/              # 控制器层
│   │   │   └── TeacherController.java
│   │   └── TeacherApplication.java
│   └── src/main/resources/
│       ├── application.yml          # 主配置
│       ├── application-dev.yml      # 开发环境配置
│       ├── application-test.yml     # 测试环境配置
│       └── application-prod.yml     # 生产环境配置
└── README.md                        # 项目文档
```

## 🔧 功能模块

### 学生服务 (Student Service)
- **端口**：8081
- **数据库**：student / student_db_test / student_db_prod
- **Redis数据库**：0
- **Kafka消费组**：student-service-{env}-group
- **功能**：
    - 学生档案管理
    - 学习记录跟踪
    - 成绩管理
    - 考勤统计

### 教师服务 (Teacher Service)
- **端口**：8082
- **数据库**：teacher / teacher_db_test / teacher_db_prod
- **Redis数据库**：1
- **Kafka消费组**：teacher-service-{env}-group
- **功能**：
    - 教师档案管理
    - 课程安排
    - 绩效评估
    - 薪资管理

### 通用模块 (Common Module)
- **Redis配置**：支持Jedis + Redisson双客户端
- **Kafka配置**：统一的消息队列配置
- **基础实体**：BaseEntity
- **响应封装**：统一的API响应格式
- **工具类**：通用工具和业务逻辑

## 🌐 访问地址

### 开发环境
- **学生服务**：http://localhost:8081
- **教师服务**：http://localhost:8082
- **学生服务API文档**：http://localhost:8081/swagger-ui/index.html
- **教师服务API文档**：http://localhost:8082/swagger-ui/index.html
- **学生服务健康检查**：http://localhost:8081/actuator/health
- **教师服务健康检查**：http://localhost:8082/actuator/health

### 服务监控
- **学生服务指标**：http://localhost:8081/actuator/metrics
- **教师服务指标**：http://localhost:8082/actuator/metrics
- **学生服务信息**：http://localhost:8081/actuator/info
- **教师服务信息**：http://localhost:8082/actuator/info

## 🔮 未来规划

- **🌐 API网关**：Spring Cloud Gateway集成
- **🔐 认证授权**：Spring Security + JWT
- **📊 监控告警**：Spring Boot Admin + Micrometer
- **🗂️ 配置中心**：启用Nacos配置管理
- **🔍 链路追踪**：Spring Cloud Sleuth + Zipkin
- **📱 移动端**：React Native移动应用
- **💾 多数据库支持**：添加MongoDB支持
- **🚀 升级技术栈**：升级到Spring Boot 3.x和Java 17

## 🛠️ 开发指南

### 编译命令
```bash
# 编译整个项目
./mvnw clean install

# 编译并跳过测试
./mvnw clean install -DskipTests

# 编译特定模块
./mvnw clean install -pl student-service -am
./mvnw clean install -pl teacher-service -am
```

### 运行命令
```bash
# 指定环境运行
java -jar student/target/student-1.0.0.jar --spring.profiles.active=test
java -jar teacher/target/teacher-1.0.0.jar --spring.profiles.active=prod

# 使用Maven插件运行
cd student && ../mvnw spring-boot:run -Dspring-boot.run.profiles=test
cd teacher && ../mvnw spring-boot:run -Dspring-boot.run.profiles=prod

# 禁用Nacos配置
java -jar student/target/student-1.0.0.jar --spring.cloud.nacos.discovery.enabled=false --spring.cloud.nacos.config.enabled=false
```

### 代码规范
- **命名约定**：Java驼峰命名规范
- **包结构**：按照功能模块组织代码
- **注释规范**：使用JavaDoc标准
- **配置管理**：环境配置分离

## 📊 API文档

系统集成了Springdoc OpenAPI 1.8.x，提供完整的API文档：

- **学生服务API**：http://localhost:8081/swagger-ui/index.html
- **教师服务API**：http://localhost:8082/swagger-ui/index.html
- **OpenAPI JSON**：
    - 学生服务：http://localhost:8081/v3/api-docs
    - 教师服务：http://localhost:8082/v3/api-docs

## 🧪 测试

```bash
# 运行所有测试
./mvnw test

# 运行特定模块测试
./mvnw test -pl student-service
./mvnw test -pl teacher-service

# 跳过测试编译
./mvnw clean install -DskipTests
```

## 🚀 部署

### 生产环境部署
```bash
# 1. 编译生产版本
./mvnw clean package -Pprod

# 2. 设置环境变量
export DB_USERNAME=prod_user
export DB_PASSWORD=prod_password
export REDIS_HOST=prod-redis
export REDIS_PASSWORD=prod_redis_password
export KAFKA_SERVERS=prod-kafka:9092

# 3. 启动服务
java -jar student/target/student-1.0.0.jar --spring.profiles.active=prod --spring.cloud.nacos.discovery.enabled=false --spring.cloud.nacos.config.enabled=false
java -jar teacher/target/teacher-1.0.0.jar --spring.profiles.active=prod --spring.cloud.nacos.discovery.enabled=false --spring.cloud.nacos.config.enabled=false
```

### Docker部署 (可选)
```bash
# 构建镜像
docker build -t student-service:1.0.0 student-service/
docker build -t teacher-service:1.0.0 teacher-service/

# 运行容器
docker run -p 8081:8081 -e SPRING_PROFILES_ACTIVE=prod student-service:1.0.0
docker run -p 8082:8082 -e SPRING_PROFILES_ACTIVE=prod teacher-service:1.0.0
```

## 🤝 贡献指南

1. Fork项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送分支 (`git push origin feature/AmazingFeature`)
5. 创建Pull Request

## 📄 许可证

本项目采用MIT许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 联系我们

- **项目主页**：https://github.com/jiandong0718/student-management
- **问题反馈**：https://github.com/jiandong0718/student-management/issues
- **邮箱**：jiandong.yh@gmail.com

---

⭐ 如果这个项目对您有帮助，请给我们一个Star！ 
