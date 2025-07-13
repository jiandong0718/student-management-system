package com.example.student.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.mybatis.spring.annotation.MapperScan;

/**
 * MyBatis配置类
 * @author liujiandong
 */
@Configuration
@MapperScan("com.example.student.mapper")
public class MybatisConfig {
    // 这个类确保mapper接口被正确扫描和注册
} 