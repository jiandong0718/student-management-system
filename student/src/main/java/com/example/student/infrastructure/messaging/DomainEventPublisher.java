package com.example.student.infrastructure.messaging;

import com.example.student.domain.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 领域事件发布服务，将领域事件发布到Spring事件总线
 * 
 * @author liujiandong
 */
@Component
@Slf4j
public class DomainEventPublisher {
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    /**
     * 发布领域事件
     * 
     * @param event 领域事件
     */
    public void publish(DomainEvent event) {
        log.info("发布领域事件: {} [{}]", event.getEventType(), event.getEventId());
        applicationEventPublisher.publishEvent(event);
    }
} 