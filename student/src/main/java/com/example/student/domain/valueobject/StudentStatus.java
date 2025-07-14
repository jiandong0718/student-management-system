package com.example.student.domain.valueobject;

/**
 * 学生状态枚举
 * 
 * @author liujiandong
 */
public enum StudentStatus {
    /**
     * 在读
     */
    ACTIVE("ACTIVE", "在读"),
    
    /**
     * 休学
     */
    INACTIVE("INACTIVE", "休学"),
    
    /**
     * 毕业
     */
    GRADUATED("GRADUATED", "毕业"),
    
    /**
     * 退学
     */
    WITHDRAWN("WITHDRAWN", "退学"),
    
    /**
     * 转学
     */
    TRANSFERRED("TRANSFERRED", "转学");
    
    private final String value;
    private final String description;
    
    StudentStatus(String value, String description) {
        this.value = value;
        this.description = description;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * 根据状态值获取枚举实例
     */
    public static StudentStatus fromValue(String value) {
        for (StudentStatus status : StudentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid student status: " + value);
    }
} 