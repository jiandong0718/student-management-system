package com.example.student.infrastructure.persistence.converter;

import com.example.student.domain.entity.Student;
import com.example.student.domain.valueobject.ContactInfo;
import com.example.student.infrastructure.persistence.entity.StudentPO;
import org.springframework.stereotype.Component;

/**
 * 学生实体转换器，负责领域模型和持久化对象之间的转换
 * 
 * @author liujiandong
 */
@Component
public class StudentConverter {
    
    /**
     * 将领域实体转换为持久化对象
     * 
     * @param student 学生领域实体
     * @return 学生持久化对象
     */
    public StudentPO toDataObject(Student student) {
        if (student == null) {
            return null;
        }
        
        StudentPO studentPO = new StudentPO();
        studentPO.setId(student.getId());
        studentPO.setStudentNo(student.getStudentId());
        studentPO.setName(student.getName());
        studentPO.setDateOfBirth(student.getDateOfBirth());
        studentPO.setGender(student.getGender());
        studentPO.setClassId(student.getClassId());
        
        // 转换联系信息
        if (student.getContactInfo() != null) {
            studentPO.setEmail(student.getContactInfo().getEmail());
            studentPO.setPhone(student.getContactInfo().getPhone());
            studentPO.setAddress(student.getContactInfo().getAddress());
        }
        
        studentPO.setStatus(student.getStatus());
        studentPO.setEnrollmentDate(student.getEnrollmentDate());
        studentPO.setGraduationDate(student.getGraduationDate());
        studentPO.setCreateTime(student.getCreateTime());
        studentPO.setUpdateTime(student.getUpdateTime());
        studentPO.setDeleted(student.getDeleted());
        
        return studentPO;
    }
    
    /**
     * 将持久化对象转换为领域实体
     * 
     * @param studentPO 学生持久化对象
     * @return 学生领域实体
     */
    public Student toDomain(StudentPO studentPO) {
        if (studentPO == null) {
            return null;
        }
        
        Student student = new Student();
        student.setId(studentPO.getId());
        student.setStudentId(studentPO.getStudentNo());
        student.setName(studentPO.getName());
        student.setDateOfBirth(studentPO.getDateOfBirth());
        student.setGender(studentPO.getGender());
        student.setClassId(studentPO.getClassId());
        
        // 转换联系信息
        ContactInfo contactInfo = new ContactInfo(
            studentPO.getEmail(),
            studentPO.getPhone(),
            studentPO.getAddress()
        );
        student.setContactInfo(contactInfo);
        
        student.setStatus(studentPO.getStatus());
        student.setEnrollmentDate(studentPO.getEnrollmentDate());
        student.setGraduationDate(studentPO.getGraduationDate());
        student.setCreateTime(studentPO.getCreateTime());
        student.setUpdateTime(studentPO.getUpdateTime());
        student.setDeleted(studentPO.getDeleted());
        
        // 聚合根ID
//        student.setAggregateId(studentPO.getStudentNo());
        
        return student;
    }
} 