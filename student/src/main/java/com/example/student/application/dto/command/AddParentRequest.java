package com.example.student.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 添加家长请求DTO
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddParentRequest {
    
    /**
     * 家长姓名
     */
    @NotBlank(message = "家长姓名不能为空")
    private String name;
    
    /**
     * 与学生的关系（父亲、母亲、监护人等）
     */
    @NotBlank(message = "关系不能为空")
    private String relationship;
    
    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String phone;
    
    /**
     * 电子邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;
    
    /**
     * 职业
     */
    private String occupation;
    
    /**
     * 工作单位
     */
    private String workPlace;
    
    /**
     * 是否为主要监护人
     */
    private boolean isPrimary;
} 