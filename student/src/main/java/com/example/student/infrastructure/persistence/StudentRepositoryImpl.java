package com.example.student.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.student.domain.entity.Student;
import com.example.student.domain.repository.StudentRepository;
import com.example.student.domain.valueobject.ContactInfo;
import com.example.student.domain.valueobject.ParentInfo;
import com.example.student.infrastructure.persistence.converter.StudentConverter;
import com.example.student.infrastructure.persistence.entity.StudentPO;
import com.example.student.infrastructure.persistence.entity.StudentParentPO;
import com.example.student.infrastructure.persistence.mapper.StudentMapper;
import com.example.student.infrastructure.persistence.mapper.StudentParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 学生仓储实现类
 * 
 * @author liujiandong
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private StudentParentMapper studentParentMapper;
    
    @Autowired
    private StudentConverter studentConverter;
    
    @Override
    public Student save(Student student) {
        StudentPO studentPO = studentConverter.toDataObject(student);
        
        if (studentPO.getId() == null) {
            studentMapper.insert(studentPO);
        } else {
            studentMapper.updateById(studentPO);
        }
        
        // 更新领域实体ID
        student.setId(studentPO.getId());
        return student;
    }
    
    @Override
    public Optional<Student> findById(Long id) {
        StudentPO studentPO = studentMapper.selectById(id);
        if (studentPO == null) {
            return Optional.empty();
        }
        
        return Optional.of(studentConverter.toDomain(studentPO));
    }
    
    @Override
    public Optional<Student> findByStudentId(String studentId) {
        LambdaQueryWrapper<StudentPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentPO::getStudentNo, studentId);
        
        StudentPO studentPO = studentMapper.selectOne(queryWrapper);
        if (studentPO == null) {
            return Optional.empty();
        }
        
        return Optional.of(studentConverter.toDomain(studentPO));
    }
    
    @Override
    public List<Student> findAll() {
        List<StudentPO> studentPOList = studentMapper.selectList(null);
        return studentPOList.stream()
            .map(studentConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> findAll(int page, int size) {
        Page<StudentPO> pageParam = new Page<>(page, size);
        Page<StudentPO> pageResult = studentMapper.selectPage(pageParam, null);
        
        return pageResult.getRecords().stream()
            .map(studentConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> findByClassId(Long classId) {
        LambdaQueryWrapper<StudentPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentPO::getClassId, classId);
        
        List<StudentPO> studentPOList = studentMapper.selectList(queryWrapper);
        return studentPOList.stream()
            .map(studentConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> findByNameLike(String name) {
        LambdaQueryWrapper<StudentPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StudentPO::getName, name);
        
        List<StudentPO> studentPOList = studentMapper.selectList(queryWrapper);
        return studentPOList.stream()
            .map(studentConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return studentMapper.selectCount(null);
    }
    
    @Override
    public boolean existsByStudentId(String studentId) {
        LambdaQueryWrapper<StudentPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentPO::getStudentNo, studentId);
        
        return studentMapper.selectCount(queryWrapper) > 0;
    }
    
    @Override
    public void deleteById(Long id) {
        studentMapper.deleteById(id);
    }
    
    @Override
    public void saveParent(Long studentId, ParentInfo parentInfo) {
        // 转换为PO
        StudentParentPO parentPO = new StudentParentPO();
        parentPO.setStudentId(studentId);
        parentPO.setName(parentInfo.getName());
        parentPO.setRelationship(parentInfo.getRelationship());
        parentPO.setPhone(parentInfo.getPhone());
        parentPO.setEmail(parentInfo.getEmail());
        parentPO.setOccupation(parentInfo.getOccupation());
        parentPO.setWorkPlace(parentInfo.getWorkPlace());
        parentPO.setIsPrimary(parentInfo.isPrimary() ? 1 : 0);
        
        // 如果是主要监护人，需要先将其他监护人设置为非主要
        if (parentInfo.isPrimary()) {
            LambdaQueryWrapper<StudentParentPO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(StudentParentPO::getStudentId, studentId)
                       .eq(StudentParentPO::getIsPrimary, 1);
            
            List<StudentParentPO> primaryParents = studentParentMapper.selectList(queryWrapper);
            for (StudentParentPO primaryParent : primaryParents) {
                primaryParent.setIsPrimary(0);
                studentParentMapper.updateById(primaryParent);
            }
        }
        
        // 保存家长信息
        studentParentMapper.insert(parentPO);
    }
    
    @Override
    public List<ParentInfo> findParentsByStudentId(Long studentId) {
        LambdaQueryWrapper<StudentParentPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentParentPO::getStudentId, studentId);
        
        List<StudentParentPO> parentPOList = studentParentMapper.selectList(queryWrapper);
        
        return parentPOList.stream()
            .map(po -> new ParentInfo(
                po.getName(),
                po.getRelationship(),
                po.getPhone(),
                po.getEmail(),
                po.getOccupation(),
                po.getWorkPlace(),
                po.getIsPrimary() == 1
            ))
            .collect(Collectors.toList());
    }
} 