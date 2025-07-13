package com.example.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生服务实现类
 *
 * @author liujiandong
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public List<Student> list() {
        return studentMapper.selectList(null);
    }

    @Override
    public Page<Student> page(Page<Student> page, LambdaQueryWrapper<Student> queryWrapper) {
        return studentMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Student student) {
        return studentMapper.insert(student) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Student student) {
        return studentMapper.updateById(student) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Long id) {
        return studentMapper.deleteById(id) > 0;
    }
}