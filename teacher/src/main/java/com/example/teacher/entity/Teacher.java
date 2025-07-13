package com.example.teacher.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 教师实体类
 */
@Data
@TableName("teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 教师工号
     */
    private String teacherNo;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 性别（0:女 1:男）
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 入职日期
     */
    private LocalDate hireDate;

    /**
     * 职称（1:讲师 2:副教授 3:教授）
     */
    private Integer title;

    /**
     * 学历（1:学士 2:硕士 3:博士）
     */
    private Integer education;

    /**
     * 专业领域
     */
    private String specialization;

    /**
     * 所在院系ID
     */
    private Long departmentId;

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
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 逻辑删除标志（0:未删除 1:已删除）
     */
    @TableLogic
    private Integer deleted;
} 