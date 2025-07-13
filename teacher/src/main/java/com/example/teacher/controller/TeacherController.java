package com.example.teacher.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.response.Result;
import com.example.teacher.entity.Teacher;
import com.example.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师控制器
 */
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 新增教师
     *
     * @param teacher 教师信息
     * @return 操作结果
     */
    @PostMapping
    public Result<Teacher> save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.success(teacher);
    }

    /**
     * 修改教师
     *
     * @param teacher 教师信息
     * @return 操作结果
     */
    @PutMapping
    public Result<Teacher> update(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.success(teacher);
    }

    /**
     * 删除教师
     *
     * @param id 教师ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        teacherService.removeById(id);
        return Result.success();
    }

    /**
     * 根据ID查询教师
     *
     * @param id 教师ID
     * @return 教师信息
     */
    @GetMapping("/{id}")
    public Result<Teacher> getById(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.success(teacher);
    }

    /**
     * 分页查询教师
     *
     * @param page 当前页码
     * @param size 每页大小
     * @param teacher 查询条件
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<Page<Teacher>> page(
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "10") long size,
            Teacher teacher) {
        Page<Teacher> pageParam = new Page<>(page, size);
        Page<Teacher> result = teacherService.pageTeacher(pageParam, teacher);
        return Result.success(result);
    }

    /**
     * 查询教师列表
     *
     * @return 教师列表
     */
    @GetMapping("/list")
    public Result<List<Teacher>> list() {
        List<Teacher> list = teacherService.list();
        return Result.success(list);
    }

    /**
     * 根据部门ID查询教师列表
     *
     * @param departmentId 部门ID
     * @return 教师列表
     */
    @GetMapping("/department/{departmentId}")
    public Result<List<Teacher>> getByDepartment(@PathVariable Long departmentId) {
        List<Teacher> list = teacherService.getTeachersByDepartment(departmentId);
        return Result.success(list);
    }

    /**
     * 根据专业领域查询教师列表
     *
     * @param specialization 专业领域
     * @return 教师列表
     */
    @GetMapping("/specialization")
    public Result<List<Teacher>> getBySpecialization(@RequestParam String specialization) {
        List<Teacher> list = teacherService.getTeachersBySpecialization(specialization);
        return Result.success(list);
    }
} 