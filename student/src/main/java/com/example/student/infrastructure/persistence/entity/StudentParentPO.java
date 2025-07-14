package com.example.student.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生家长持久化对象
 * 
 * @author liujiandong
 */
@Data
@TableName("t_student_parent")
public class StudentParentPO {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 学生ID
     */
    private Long studentId;
    
    /**
     * 家长姓名
     */
    private String name;
    
    /**
     * 与学生的关系
     */
    private String relationship;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 电子邮箱
     */
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
     * 是否为主要监护人（0-否，1-是）
     */
    private Integer isPrimary;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 是否删除（0-未删除，1-已删除）
     */
    @TableLogic
    private Integer deleted;
} 