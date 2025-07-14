package com.example.student.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * 创建学生请求DTO
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    
    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空")
    private String studentId;
    
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    
    /**
     * 出生日期
     */
    @NotNull(message = "出生日期不能为空")
    @Past(message = "出生日期必须是过去的时间")
    private LocalDate dateOfBirth;
    
    /**
     * 性别（0-女，1-男）
     */
    @NotNull(message = "性别不能为空")
    private Integer gender;
    
    /**
     * 电子邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 地址
     */
    private String address;
} 