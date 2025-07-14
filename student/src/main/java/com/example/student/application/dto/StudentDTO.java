package com.example.student.application.dto;

import com.example.student.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生数据传输对象
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    /**
     * 学生ID
     */
    private Long id;
    
    /**
     * 学号
     */
    private String studentId;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;
    
    /**
     * 性别（0-女，1-男）
     */
    private Integer gender;
    
    /**
     * 班级ID
     */
    private Long classId;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 入学日期
     */
    private LocalDate enrollmentDate;
    
    /**
     * 毕业日期
     */
    private LocalDate graduationDate;
    
    /**
     * 家长信息列表
     */
    private List<ParentDTO> parents;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 从实体转换为DTO
     * 
     * @param student 学生实体
     * @return 学生DTO
     */
    public static StudentDTO fromEntity(Student student) {
        if (student == null) {
            return null;
        }
        
        List<ParentDTO> parentDTOs = student.getParents() != null 
            ? student.getParents().stream()
                .map(ParentDTO::fromEntity)
                .collect(Collectors.toList())
            : null;
        
        return StudentDTO.builder()
            .id(student.getId())
            .studentId(student.getStudentId())
            .name(student.getName())
            .age(student.getAge())
            .dateOfBirth(student.getDateOfBirth())
            .gender(student.getGender())
            .classId(student.getClassId())
            .email(student.getContactInfo() != null ? student.getContactInfo().getEmail() : null)
            .phone(student.getContactInfo() != null ? student.getContactInfo().getPhone() : null)
            .address(student.getContactInfo() != null ? student.getContactInfo().getAddress() : null)
            .status(student.getStatus())
            .enrollmentDate(student.getEnrollmentDate())
            .graduationDate(student.getGraduationDate())
            .parents(parentDTOs)
            .createTime(student.getCreateTime())
            .updateTime(student.getUpdateTime())
            .build();
    }
} 