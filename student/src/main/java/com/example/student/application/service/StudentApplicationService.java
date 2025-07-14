package com.example.student.application.service;

import com.example.student.application.dto.ParentDTO;
import com.example.student.application.dto.StudentDTO;
import com.example.student.application.dto.command.AddParentRequest;
import com.example.student.application.dto.command.CreateStudentRequest;
import com.example.student.application.dto.command.UpdateStudentRequest;
import com.example.student.application.dto.command.UpdateStudentStatusRequest;
import com.example.student.domain.entity.ClassEnrollment;
import com.example.student.domain.entity.Student;
import com.example.student.domain.event.DomainEvent;
import com.example.student.domain.repository.StudentRepository;
import com.example.student.domain.valueobject.ContactInfo;
import com.example.student.domain.valueobject.ParentInfo;
import com.example.student.infrastructure.messaging.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 * 学生应用服务类，处理学生相关的业务逻辑
 * 
 * @author liujiandong
 */
@Service
@Transactional
public class StudentApplicationService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private DomainEventPublisher eventPublisher;
    
    /**
     * 创建学生
     * 
     * @param request 创建学生请求
     * @return 学生DTO
     */
    public StudentDTO createStudent(CreateStudentRequest request) {
        // 检查学号是否已存在
        if (studentRepository.existsByStudentId(request.getStudentId())) {
            throw new IllegalArgumentException("学号已存在: " + request.getStudentId());
        }
        
        // 创建联系信息值对象
        ContactInfo contactInfo = new ContactInfo(
            request.getEmail(),
            request.getPhone(),
            request.getAddress()
        );
        
        // 使用工厂方法创建学生
        Student student = Student.create(
            request.getStudentId(),
            request.getName(),
            request.getDateOfBirth(),
            request.getGender(),
            contactInfo
        );
        
        // 保存学生
        Student savedStudent = studentRepository.save(student);
        
        // 发布领域事件
        publishEvents(student);
        
        // 返回DTO
        return StudentDTO.fromEntity(savedStudent);
    }
    
    /**
     * 更新学生信息
     * 
     * @param id 学生ID
     * @param request 更新学生请求
     * @return 学生DTO
     */
    public StudentDTO updateStudent(Long id, UpdateStudentRequest request) {
        // 获取学生
        Student student = findStudentById(id);
        
        // 更新学生信息
        student.setName(request.getName());
        
        // 更新联系信息
        ContactInfo contactInfo = new ContactInfo(
            request.getEmail(),
            request.getPhone(),
            request.getAddress()
        );
        student.setContactInfo(contactInfo);
        
        // 保存学生
        Student savedStudent = studentRepository.save(student);
        
        // 发布领域事件
        publishEvents(student);
        
        // 返回DTO
        return StudentDTO.fromEntity(savedStudent);
    }
    
    /**
     * 更新学生状态
     * 
     * @param id 学生ID
     * @param request 更新状态请求
     */
    public void updateStudentStatus(Long id, UpdateStudentStatusRequest request) {
        // 获取学生
        Student student = findStudentById(id);
        
        // 更新状态
        student.updateStatus(request.getNewStatus(), request.getReason());
        
        // 保存学生
        studentRepository.save(student);
        
        // 发布领域事件
        publishEvents(student);
    }
    
    /**
     * 添加家长信息
     * 
     * @param studentId 学生ID
     * @param request 添加家长请求
     * @return 家长DTO
     */
    public ParentDTO addParent(Long studentId, AddParentRequest request) {
        // 获取学生
        Student student = findStudentById(studentId);
        
        // 创建家长信息
        ParentInfo parentInfo = new ParentInfo(
            request.getName(),
            request.getRelationship(),
            request.getPhone(),
            request.getEmail(),
            request.getOccupation(),
            request.getWorkPlace(),
            request.isPrimary()
        );
        
        // 添加家长
        student.addParent(parentInfo);
        
        // 保存家长信息
        studentRepository.saveParent(studentId, parentInfo);
        
        // 发布领域事件
        publishEvents(student);
        
        // 返回DTO
        return ParentDTO.fromEntity(parentInfo);
    }
    
    /**
     * 根据ID获取学生
     * 
     * @param id 学生ID
     * @return 学生DTO
     */
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = findStudentById(id);
        
        // 加载家长信息
        List<ParentInfo> parents = studentRepository.findParentsByStudentId(id);
        student.setParents(parents);
        
        return StudentDTO.fromEntity(student);
    }
    
    /**
     * 根据学号获取学生
     * 
     * @param studentId 学号
     * @return 学生DTO
     */
    @Transactional(readOnly = true)
    public StudentDTO getStudentByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
            .orElseThrow(() -> new EntityNotFoundException("学生不存在: " + studentId));
        
        // 加载家长信息
        List<ParentInfo> parents = studentRepository.findParentsByStudentId(student.getId());
        student.setParents(parents);
        
        return StudentDTO.fromEntity(student);
    }
    
    /**
     * 分页获取所有学生
     * 
     * @param page 页码
     * @param size 每页大小
     * @return 学生DTO列表
     */
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents(int page, int size) {
        List<Student> students = studentRepository.findAll(page, size);
        return students.stream()
            .map(StudentDTO::fromEntity)
            .toList();
    }
    
    /**
     * 根据班级ID获取学生
     * 
     * @param classId 班级ID
     * @return 学生DTO列表
     */
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByClassId(Long classId) {
        List<Student> students = studentRepository.findByClassId(classId);
        return students.stream()
            .map(StudentDTO::fromEntity)
            .toList();
    }
    
    /**
     * 根据姓名模糊查询学生
     * 
     * @param name 姓名
     * @return 学生DTO列表
     */
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameLike("%" + name + "%");
        return students.stream()
            .map(StudentDTO::fromEntity)
            .toList();
    }
    
    /**
     * 为学生指定班级
     * 
     * @param studentId 学生ID
     * @param classId 班级ID
     */
    public void assignClassToStudent(Long studentId, Long classId) {
        // 获取学生
        Student student = findStudentById(studentId);
        
        // 设置班级ID
        student.setClassId(classId);
        
        // 创建班级选择
        ClassEnrollment enrollment = new ClassEnrollment(
            studentId,
            classId,
            LocalDate.now(),
            null,
            "ACTIVE"
        );
        
        // 保存学生
        studentRepository.save(student);
        
        // 发布领域事件
        publishEvents(student);
    }
    
    /**
     * 删除学生
     * 
     * @param id 学生ID
     */
    public void deleteStudent(Long id) {
        // 检查学生是否存在
        if (!studentRepository.findById(id).isPresent()) {
            throw new EntityNotFoundException("学生不存在: " + id);
        }
        
        // 逻辑删除学生
        studentRepository.deleteById(id);
    }
    
    /**
     * 根据ID查找学生
     * 
     * @param id 学生ID
     * @return 学生实体
     */
    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("学生不存在: " + id));
    }
    
    /**
     * 发布领域事件
     * 
     * @param student 学生实体
     */
    private void publishEvents(Student student) {
        List<DomainEvent> events = student.getUncommittedEvents();
        events.forEach(eventPublisher::publish);
        student.clearDomainEvents();
    }
} 