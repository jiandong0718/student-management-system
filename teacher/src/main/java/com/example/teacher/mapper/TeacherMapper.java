package com.example.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teacher.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教师Mapper接口
 * @author liujiandong
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
} 