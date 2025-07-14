package com.example.student.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 更新学生请求DTO
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {
    
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    
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