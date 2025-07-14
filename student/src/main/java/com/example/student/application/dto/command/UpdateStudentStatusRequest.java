package com.example.student.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 更新学生状态请求DTO
 * 
 * @author liujiandong
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentStatusRequest {
    
    /**
     * 新状态
     */
    @NotBlank(message = "新状态不能为空")
    private String newStatus;
    
    /**
     * 状态变更原因
     */
    private String reason;
} 