package com.example.teacher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.example.common.config.MyBatisPlusConfig;

/**
 * 导入通用MyBatis-Plus配置
 */
@Configuration
@Import(com.example.common.config.MyBatisPlusConfig.class)
public class MybatisPlusConfig {
    // 通过Import导入通用配置，可以在这里添加Teacher模块特有的MyBatis-Plus配置
} 