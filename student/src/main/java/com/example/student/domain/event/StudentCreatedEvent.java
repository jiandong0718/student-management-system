package com.example.student.domain.event;

import com.example.student.domain.entity.Student;
import lombok.Getter;

/**
 * 学生创建事件
 * 
 * @author liujiandong
 */
@Getter
public class StudentCreatedEvent extends DomainEvent {
    
    /**
     * 学生实体
     */
    private final Student student;
    
    /**
     * 构造函数
     * 
     * @param student 学生实体
     */
    public StudentCreatedEvent(Student student) {
        super(student.getStudentId());
        this.student = student;
    }
} 