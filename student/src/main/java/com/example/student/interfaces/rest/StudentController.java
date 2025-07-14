package com.example.student.interfaces.rest;

import com.example.student.application.dto.ParentDTO;
import com.example.student.application.dto.StudentDTO;
import com.example.student.application.dto.command.AddParentRequest;
import com.example.student.application.dto.command.CreateStudentRequest;
import com.example.student.application.dto.command.UpdateStudentRequest;
import com.example.student.application.dto.command.UpdateStudentStatusRequest;
import com.example.student.application.service.StudentApplicationService;
import com.example.student.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生管理控制器
 * 
 * @author liujiandong
 */
@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {
    
    @Autowired
    private StudentApplicationService studentService;
    
    /**
     * 创建学生
     * 
     * @param request 创建学生请求
     * @return 学生DTO
     */
    @PostMapping
    public Result<StudentDTO> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        log.info("创建学生: {}", request);
        StudentDTO student = studentService.createStudent(request);
        return Result.success(student);
    }
    
    /**
     * 更新学生
     * 
     * @param id 学生ID
     * @param request 更新学生请求
     * @return 学生DTO
     */
    @PutMapping("/{id}")
    public Result<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequest request) {
        log.info("更新学生: id={}, request={}", id, request);
        StudentDTO student = studentService.updateStudent(id, request);
        return Result.success(student);
    }
    
    /**
     * 获取学生
     * 
     * @param id 学生ID
     * @return 学生DTO
     */
    @GetMapping("/{id}")
    public Result<StudentDTO> getStudent(@PathVariable Long id) {
        log.info("获取学生: id={}", id);
        StudentDTO student = studentService.getStudentById(id);
        return Result.success(student);
    }
    
    /**
     * 根据学号获取学生
     * 
     * @param studentId 学号
     * @return 学生DTO
     */
    @GetMapping("/by-student-id/{studentId}")
    public Result<StudentDTO> getStudentByStudentId(@PathVariable String studentId) {
        log.info("根据学号获取学生: studentId={}", studentId);
        StudentDTO student = studentService.getStudentByStudentId(studentId);
        return Result.success(student);
    }
    
    /**
     * 分页获取所有学生
     * 
     * @param page 页码
     * @param size 每页大小
     * @return 学生DTO列表
     */
    @GetMapping
    public Result<List<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("分页获取所有学生: page={}, size={}", page, size);
        List<StudentDTO> students = studentService.getAllStudents(page, size);
        return Result.success(students);
    }
    
    /**
     * 根据班级ID获取学生
     * 
     * @param classId 班级ID
     * @return 学生DTO列表
     */
    @GetMapping("/by-class/{classId}")
    public Result<List<StudentDTO>> getStudentsByClassId(@PathVariable Long classId) {
        log.info("根据班级ID获取学生: classId={}", classId);
        List<StudentDTO> students = studentService.getStudentsByClassId(classId);
        return Result.success(students);
    }
    
    /**
     * 根据姓名模糊查询学生
     * 
     * @param name 姓名
     * @return 学生DTO列表
     */
    @GetMapping("/search")
    public Result<List<StudentDTO>> getStudentsByName(@RequestParam String name) {
        log.info("根据姓名模糊查询学生: name={}", name);
        List<StudentDTO> students = studentService.getStudentsByName(name);
        return Result.success(students);
    }
    
    /**
     * 更新学生状态
     * 
     * @param id 学生ID
     * @param request 更新状态请求
     * @return 成功响应
     */
    @PatchMapping("/{id}/status")
    public Result<Void> updateStudentStatus(@PathVariable Long id, @Valid @RequestBody UpdateStudentStatusRequest request) {
        log.info("更新学生状态: id={}, request={}", id, request);
        studentService.updateStudentStatus(id, request);
        return Result.success();
    }
    
    /**
     * 为学生指定班级
     * 
     * @param id 学生ID
     * @param classId 班级ID
     * @return 成功响应
     */
    @PutMapping("/{id}/class/{classId}")
    public Result<Void> assignClassToStudent(@PathVariable Long id, @PathVariable Long classId) {
        log.info("为学生指定班级: id={}, classId={}", id, classId);
        studentService.assignClassToStudent(id, classId);
        return Result.success();
    }
    
    /**
     * 添加家长信息
     * 
     * @param id 学生ID
     * @param request 添加家长请求
     * @return 家长DTO
     */
    @PostMapping("/{id}/parents")
    public Result<ParentDTO> addParent(@PathVariable Long id, @Valid @RequestBody AddParentRequest request) {
        log.info("添加家长信息: id={}, request={}", id, request);
        ParentDTO parent = studentService.addParent(id, request);
        return Result.success(parent);
    }
    
    /**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 成功响应
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteStudent(@PathVariable Long id) {
        log.info("删除学生: id={}", id);
        studentService.deleteStudent(id);
        return Result.success();
    }
} 