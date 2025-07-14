package com.example.student.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 联系信息值对象，表示学生的联系方式
 * 
 * @author liujiandong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    
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
     * 值对象模式 - 创建具有新邮箱的联系信息副本
     */
    public ContactInfo withEmail(String newEmail) {
        return new ContactInfo(newEmail, this.phone, this.address);
    }
    
    /**
     * 值对象模式 - 创建具有新电话的联系信息副本
     */
    public ContactInfo withPhone(String newPhone) {
        return new ContactInfo(this.email, newPhone, this.address);
    }
    
    /**
     * 值对象模式 - 创建具有新地址的联系信息副本
     */
    public ContactInfo withAddress(String newAddress) {
        return new ContactInfo(this.email, this.phone, newAddress);
    }
} 