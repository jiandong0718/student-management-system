package com.example.student.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.student.infrastructure.persistence.entity.StudentParentPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生家长数据访问接口
 * 
 * @author liujiandong
 */
@Mapper
public interface StudentParentMapper extends BaseMapper<StudentParentPO> {
} 