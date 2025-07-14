package com.example.student.domain.event;

import com.example.student.domain.entity.Student;
import lombok.Getter;

/**
 * 学生状态变更事件
 * 
 * @author liujiandong
 */
@Getter
public class StudentStatusChangedEvent extends DomainEvent {
    
    /**
     * 学生实体
     */
    private final Student student;
    
    /**
     * 旧状态
     */
    private final String oldStatus;
    
    /**
     * 新状态
     */
    private final String newStatus;
    
    /**
     * 变更原因
     */
    private final String reason;
    
    /**
     * 构造函数
     * 
     * @param student 学生实体
     * @param oldStatus 旧状态
     * @param newStatus 新状态
     * @param reason 变更原因
     */
    public StudentStatusChangedEvent(Student student, String oldStatus, String newStatus, String reason) {
        super(student.getStudentId());
        this.student = student;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.reason = reason;
    }
} 