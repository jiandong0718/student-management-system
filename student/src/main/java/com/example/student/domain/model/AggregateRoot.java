package com.example.student.domain.model;

import com.example.student.domain.event.DomainEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根抽象基类，提供领域事件收集和发布机制
 * 
 * @author liujiandong
 */
@Getter
public abstract class AggregateRoot {
    
    /**
     * 聚合ID
     */
    protected String aggregateId;
    
    /**
     * 未提交的领域事件列表
     */
    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();
    
    /**
     * 添加领域事件
     */
    protected void addDomainEvent(DomainEvent event) {
        this.uncommittedEvents.add(event);
    }
    
    /**
     * 获取所有未提交的领域事件
     */
    public List<DomainEvent> getUncommittedEvents() {
        return Collections.unmodifiableList(uncommittedEvents);
    }
    
    /**
     * 清除所有未提交的领域事件
     */
    public void clearDomainEvents() {
        this.uncommittedEvents.clear();
    }
} 