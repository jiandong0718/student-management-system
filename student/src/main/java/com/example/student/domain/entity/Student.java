package com.example.student.domain.entity;

import com.example.student.domain.event.StudentCreatedEvent;
import com.example.student.domain.model.AggregateRoot;
import com.example.student.domain.valueobject.ContactInfo;
import com.example.student.domain.valueobject.ParentInfo;
import com.example.student.domain.valueobject.StudentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生领域实体 - 聚合根
 * 
 * @author liujiandong
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends AggregateRoot {
    
    /**
     * 学生ID（数据库ID）
     */
    private Long id;
    
    /**
     * 学号（业务标识）
     */
    private String studentId;
    
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
     * 联系信息
     */
    private ContactInfo contactInfo;
    
    /**
     * 班级ID
     */
    private Long classId;
    
    /**
     * 学生状态
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
     * 家长信息列表
     */
    private List<ParentInfo> parents = new ArrayList<>();
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 是否删除
     */
    private Integer deleted;
    
    /**
     * 工厂方法 - 创建新学生
     *
     * @param studentId 学号
     * @param name 姓名
     * @param dateOfBirth 出生日期
     * @param gender 性别
     * @param contactInfo 联系信息
     * @return 学生实体
     */
    public static Student create(String studentId, String name, LocalDate dateOfBirth, 
                                 Integer gender, ContactInfo contactInfo) {
        Student student = new Student();
        student.studentId = studentId;
        student.aggregateId = studentId;
        student.name = name;
        student.dateOfBirth = dateOfBirth;
        student.gender = gender;
        student.contactInfo = contactInfo;
        student.status = StudentStatus.ACTIVE.getValue();
        student.enrollmentDate = LocalDate.now();
        student.deleted = 0;
        
        // 添加领域事件
        student.addDomainEvent(new StudentCreatedEvent(student));
        
        return student;
    }
    
    /**
     * 更新学生状态
     *
     * @param newStatus 新状态
     * @param reason 原因
     */
    public void updateStatus(String newStatus, String reason) {
        // 检查状态转换的合法性
        if (!isValidStatusTransition(this.status, newStatus)) {
            throw new IllegalStateException("无效的状态转换: " + this.status + " -> " + newStatus);
        }
        
        String oldStatus = this.status;
        this.status = newStatus;
        
        // 如果是毕业状态，设置毕业日期
        if (StudentStatus.GRADUATED.getValue().equals(newStatus)) {
            this.graduationDate = LocalDate.now();
        }
        
        // TODO: 添加状态变更事件
        // this.addDomainEvent(new StudentStatusChangedEvent(this, oldStatus, newStatus, reason));
    }
    
    /**
     * 添加家长信息
     *
     * @param parentInfo 家长信息
     */
    public void addParent(ParentInfo parentInfo) {
        // 如果添加的是主要监护人，需要将其他监护人设为非主要
        if (parentInfo.isPrimary()) {
            for (ParentInfo parent : this.parents) {
                if (parent.isPrimary()) {
                    parent.setPrimary(false);
                }
            }
        }
        
        this.parents.add(parentInfo);
        
        // TODO: 添加家长信息变更事件
        // this.addDomainEvent(new ParentAddedEvent(this, parentInfo));
    }
    
    /**
     * 获取学生年龄
     *
     * @return 年龄
     */
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
    
    /**
     * 判断学生是否处于活跃状态
     *
     * @return 是否活跃
     */
    public boolean isActive() {
        return StudentStatus.ACTIVE.getValue().equals(this.status);
    }
    
    /**
     * 判断学生是否已毕业
     *
     * @return 是否已毕业
     */
    public boolean isGraduated() {
        return StudentStatus.GRADUATED.getValue().equals(this.status);
    }
    
    // 私有辅助方法 - 检查状态转换是否合法
    private boolean isValidStatusTransition(String currentStatus, String newStatus) {
        // 如果当前状态和新状态相同，则合法
        if (currentStatus.equals(newStatus)) {
            return true;
        }
        
        // 毕业状态不能转为其他状态
        if (StudentStatus.GRADUATED.getValue().equals(currentStatus)) {
            return false;
        }
        
        // 退学状态不能转为在读或毕业状态
        if (StudentStatus.WITHDRAWN.getValue().equals(currentStatus) &&
            (StudentStatus.ACTIVE.getValue().equals(newStatus) || 
             StudentStatus.GRADUATED.getValue().equals(newStatus))) {
            return false;
        }
        
        // 非活跃状态不能直接转为毕业状态
        if (StudentStatus.INACTIVE.getValue().equals(currentStatus) && 
            StudentStatus.GRADUATED.getValue().equals(newStatus)) {
            return false;
        }
        
        return true;
    }
} 