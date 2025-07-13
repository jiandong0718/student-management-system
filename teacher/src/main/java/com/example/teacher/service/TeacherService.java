package com.example.teacher.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teacher.entity.Teacher;

import java.util.List;

/**
 * 教师服务接口
 */
public interface TeacherService extends IService<Teacher> {
    
    /**
     * 分页查询教师列表
     *
     * @param page      分页参数
     * @param teacher   查询条件
     * @return          分页结果
     */
    Page<Teacher> pageTeacher(Page<Teacher> page, Teacher teacher);
    
    /**
     * 根据部门ID查询教师列表
     *
     * @param departmentId  部门ID
     * @return              教师列表
     */
    List<Teacher> getTeachersByDepartment(Long departmentId);
    
    /**
     * 根据专业领域查询教师列表
     *
     * @param specialization    专业领域
     * @return                  教师列表
     */
    List<Teacher> getTeachersBySpecialization(String specialization);
} 