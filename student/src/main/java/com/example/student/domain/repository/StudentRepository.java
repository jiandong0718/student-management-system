package com.example.student.domain.repository;

import com.example.student.domain.entity.Student;
import com.example.student.domain.valueobject.ParentInfo;

import java.util.List;
import java.util.Optional;

/**
 * 学生仓储接口
 * 
 * @author liujiandong
 */
public interface StudentRepository {
    
    /**
     * 保存学生
     * 
     * @param student 学生实体
     * @return 保存后的学生实体
     */
    Student save(Student student);
    
    /**
     * 根据ID查询学生
     * 
     * @param id 学生ID
     * @return 学生实体
     */
    Optional<Student> findById(Long id);
    
    /**
     * 根据学号查询学生
     * 
     * @param studentId 学号
     * @return 学生实体
     */
    Optional<Student> findByStudentId(String studentId);
    
    /**
     * 查询所有学生
     * 
     * @return 学生列表
     */
    List<Student> findAll();
    
    /**
     * 分页查询学生
     * 
     * @param page 页码
     * @param size 每页大小
     * @return 学生列表
     */
    List<Student> findAll(int page, int size);
    
    /**
     * 根据班级ID查询学生
     * 
     * @param classId 班级ID
     * @return 学生列表
     */
    List<Student> findByClassId(Long classId);
    
    /**
     * 根据姓名模糊查询学生
     * 
     * @param name 姓名
     * @return 学生列表
     */
    List<Student> findByNameLike(String name);
    
    /**
     * 查询学生数量
     * 
     * @return 学生数量
     */
    long count();
    
    /**
     * 判断学号是否已存在
     * 
     * @param studentId 学号
     * @return 是否存在
     */
    boolean existsByStudentId(String studentId);
    
    /**
     * 删除学生
     * 
     * @param id 学生ID
     */
    void deleteById(Long id);
    
    /**
     * 保存学生家长信息
     * 
     * @param studentId 学生ID
     * @param parentInfo 家长信息
     */
    void saveParent(Long studentId, ParentInfo parentInfo);
    
    /**
     * 获取学生的家长信息列表
     * 
     * @param studentId 学生ID
     * @return 家长信息列表
     */
    List<ParentInfo> findParentsByStudentId(Long studentId);
} 