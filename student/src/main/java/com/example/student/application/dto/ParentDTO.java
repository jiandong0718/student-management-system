package com.example.student.application.dto;

import com.example.student.domain.valueobject.ParentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家长数据传输对象
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {
    
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
     * 是否为主要监护人
     */
    private boolean isPrimary;
    
    /**
     * 从值对象转换为DTO
     * 
     * @param parentInfo 家长信息值对象
     * @return 家长DTO
     */
    public static ParentDTO fromEntity(ParentInfo parentInfo) {
        if (parentInfo == null) {
            return null;
        }
        
        return ParentDTO.builder()
            .name(parentInfo.getName())
            .relationship(parentInfo.getRelationship())
            .phone(parentInfo.getPhone())
            .email(parentInfo.getEmail())
            .occupation(parentInfo.getOccupation())
            .workPlace(parentInfo.getWorkPlace())
            .isPrimary(parentInfo.isPrimary())
            .build();
    }
} 