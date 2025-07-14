package com.example.student.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 导入通用MyBatis-Plus配置
 */
@Configuration
@Import(com.example.common.config.MyBatisPlusConfig.class)
public class MybatisPlusConfig {
    // 通过Import导入通用配置，可以在这里添加Student模块特有的MyBatis-Plus配置
} 