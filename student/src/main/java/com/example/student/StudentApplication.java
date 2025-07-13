package com.example.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学生管理系统启动类
 * 
 * @author liujiandong
 */
@SpringBootApplication
@MapperScan("com.example.student.mapper")
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
        System.out.println("Student Management System Started Successfully!");
    }

}
