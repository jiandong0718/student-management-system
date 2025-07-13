package com.example.teacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liujiandong
 */
@SpringBootApplication
@MapperScan("com.example.teacher.mapper")
public class TeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherApplication.class, args);
        System.out.println("Teacher Application Started");
    }

}
