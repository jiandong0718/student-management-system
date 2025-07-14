package com.example.student.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 班级选择实体，表示学生与班级的关联关系
 * 
 * @author liujiandong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEnrollment {
    
    /**
     * 学生ID
     */
    private Long studentId;
    
    /**
     * 班级ID
     */
    private Long classId;
    
    /**
     * 加入日期
     */
    private LocalDate joinDate;
    
    /**
     * 离开日期
     */
    private LocalDate leaveDate;
    
    /**
     * 状态（ACTIVE-在读, INACTIVE-休学, GRADUATED-毕业, TRANSFERRED-转班）
     */
    private String status;
    
    /**
     * 判断是否为活跃状态
     *
     * @return 是否活跃
     */
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }
} 