package com.example.student.config;

import com.example.common.config.MyBatisConfig;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Student模块的MyBatis配置类
 * @author liujiandong
 */
@Configuration
@MapperScan("com.example.student.mapper")
public class MybatisConfig extends MyBatisConfig {
    // 继承通用配置并指定本模块的Mapper扫描路径
} 