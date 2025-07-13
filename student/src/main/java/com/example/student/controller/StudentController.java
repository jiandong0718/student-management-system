package com.example.student.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.response.Result;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理控制器
 * @author liujiandong
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询学生列表
     */
    @GetMapping
    public Result<List<Student>> list() {
        return Result.success(studentService.list());
    }

    /**
     * 分页查询学生
     */
    @GetMapping("/page")
    public Result<Page<Student>> page(@RequestParam(defaultValue = "1") long current,
                              @RequestParam(defaultValue = "10") long size,
                              @RequestParam(required = false) String name) {
        Page<Student> page = new Page<>(current, size);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        
        if (name != null && !name.isEmpty()) {
            queryWrapper.like(Student::getName, name);
        }
        
        return Result.success(studentService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        if (student == null) {
            return Result.error("学生不存在");
        }
        return Result.success(student);
    }

    /**
     * 新增学生
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody Student student) {
        boolean success = studentService.save(student);
        if (success) {
            return Result.success(true);
        }
        return Result.error("添加学生失败");
    }

    /**
     * 更新学生
     */
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        boolean success = studentService.updateById(student);
        if (success) {
            return Result.success(true);
        }
        return Result.error("更新学生失败");
    }

    /**
     * 删除学生
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        boolean success = studentService.removeById(id);
        if (success) {
            return Result.success(true);
        }
        return Result.error("删除学生失败");
    }
} 