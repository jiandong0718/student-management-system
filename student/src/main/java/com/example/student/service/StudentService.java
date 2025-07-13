package com.example.student.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.student.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 * @author liujiandong
 */
public interface StudentService {

    Student getById(Long id);

    List<Student> list();

    Page<Student> page(Page<Student> page, LambdaQueryWrapper<Student> queryWrapper);

    boolean save(Student student);

    boolean updateById(Student student);

    boolean removeById(Long id);
}