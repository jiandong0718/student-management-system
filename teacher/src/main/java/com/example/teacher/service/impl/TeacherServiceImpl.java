package com.example.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teacher.entity.Teacher;
import com.example.teacher.mapper.TeacherMapper;
import com.example.teacher.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 教师服务实现类
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * 分页查询教师列表
     *
     * @param page    分页参数
     * @param teacher 查询条件
     * @return 分页结果
     */
    @Override
    public Page<Teacher> pageTeacher(Page<Teacher> page, Teacher teacher) {
        LambdaQueryWrapper<Teacher> queryWrapper = Wrappers.lambdaQuery();
        
        // 设置查询条件
        if (teacher != null) {
            // 根据教师工号模糊查询
            if (StringUtils.hasText(teacher.getTeacherNo())) {
                queryWrapper.like(Teacher::getTeacherNo, teacher.getTeacherNo());
            }
            
            // 根据教师姓名模糊查询
            if (StringUtils.hasText(teacher.getName())) {
                queryWrapper.like(Teacher::getName, teacher.getName());
            }
            
            // 根据性别精确查询
            if (teacher.getGender() != null) {
                queryWrapper.eq(Teacher::getGender, teacher.getGender());
            }
            
            // 根据部门ID精确查询
            if (teacher.getDepartmentId() != null) {
                queryWrapper.eq(Teacher::getDepartmentId, teacher.getDepartmentId());
            }
            
            // 根据职称精确查询
            if (teacher.getTitle() != null) {
                queryWrapper.eq(Teacher::getTitle, teacher.getTitle());
            }
            
            // 根据学历精确查询
            if (teacher.getEducation() != null) {
                queryWrapper.eq(Teacher::getEducation, teacher.getEducation());
            }
            
            // 根据专业领域模糊查询
            if (StringUtils.hasText(teacher.getSpecialization())) {
                queryWrapper.like(Teacher::getSpecialization, teacher.getSpecialization());
            }
        }
        
        // 按创建时间降序排序
        queryWrapper.orderByDesc(Teacher::getCreateTime);
        
        return this.page(page, queryWrapper);
    }

    /**
     * 根据部门ID查询教师列表
     *
     * @param departmentId 部门ID
     * @return 教师列表
     */
    @Override
    public List<Teacher> getTeachersByDepartment(Long departmentId) {
        LambdaQueryWrapper<Teacher> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Teacher::getDepartmentId, departmentId);
        return this.list(queryWrapper);
    }

    /**
     * 根据专业领域查询教师列表
     *
     * @param specialization 专业领域
     * @return 教师列表
     */
    @Override
    public List<Teacher> getTeachersBySpecialization(String specialization) {
        LambdaQueryWrapper<Teacher> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(Teacher::getSpecialization, specialization);
        return this.list(queryWrapper);
    }
} 