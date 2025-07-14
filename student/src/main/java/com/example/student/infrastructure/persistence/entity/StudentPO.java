package com.example.student.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学生持久化对象
 * 
 * @author liujiandong
 */
@Data
@TableName("t_student")
public class StudentPO {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 学号
     */
    private String studentNo;
    
    /**
     * 姓名
     */
    private String name;
    
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
     * 状态（ACTIVE-在读, INACTIVE-休学, GRADUATED-毕业, WITHDRAWN-退学, TRANSFERRED-转学）
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