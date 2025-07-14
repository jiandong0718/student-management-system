package com.example.student.domain.event;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件抽象基类
 * 
 * @author liujiandong
 */
@Getter
public abstract class DomainEvent {
    
    /**
     * 事件ID
     */
    private final String eventId;
    
    /**
     * 聚合ID
     */
    private final String aggregateId;
    
    /**
     * 事件发生时间
     */
    private final LocalDateTime occurredOn;
    
    /**
     * 事件类型
     */
    private final String eventType;
    
    /**
     * 构造函数
     * 
     * @param aggregateId 聚合ID
     */
    protected DomainEvent(String aggregateId) {
        this.eventId = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.occurredOn = LocalDateTime.now();
        this.eventType = this.getClass().getSimpleName();
    }
} 