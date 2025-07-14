package com.example.student.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家长信息值对象，表示学生的家长信息
 * 
 * @author liujiandong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentInfo {
    
    /**
     * 家长姓名
     */
    private String name;
    
    /**
     * 与学生的关系（父亲、母亲、监护人等）
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
     * 是否为主要监护人
     */
    private boolean isPrimary;
} 