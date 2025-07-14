-- 创建学生表
CREATE TABLE IF NOT EXISTS `t_student` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_no` VARCHAR(20) NOT NULL COMMENT '学号',
    `name` VARCHAR(50) NOT NULL COMMENT '姓名',
    `date_of_birth` DATE COMMENT '出生日期',
    `gender` TINYINT COMMENT '性别（0-女，1-男）',
    `class_id` BIGINT COMMENT '班级ID',
    `email` VARCHAR(100) COMMENT '电子邮箱',
    `phone` VARCHAR(20) COMMENT '联系电话',
    `address` VARCHAR(255) COMMENT '地址',
    `status` VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' COMMENT '状态（ACTIVE-在读, INACTIVE-休学, GRADUATED-毕业, WITHDRAWN-退学, TRANSFERRED-转学）',
    `enrollment_date` DATE COMMENT '入学日期',
    `graduation_date` DATE COMMENT '毕业日期',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_no` (`student_no`),
    INDEX `idx_class_id` (`class_id`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 创建学生家长表
CREATE TABLE IF NOT EXISTS `t_student_parent` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT NOT NULL COMMENT '学生ID',
    `name` VARCHAR(50) NOT NULL COMMENT '家长姓名',
    `relationship` VARCHAR(20) NOT NULL COMMENT '与学生的关系',
    `phone` VARCHAR(20) NOT NULL COMMENT '联系电话',
    `email` VARCHAR(100) COMMENT '电子邮箱',
    `occupation` VARCHAR(50) COMMENT '职业',
    `work_place` VARCHAR(100) COMMENT '工作单位',
    `is_primary` TINYINT DEFAULT 0 COMMENT '是否为主要监护人（0-否，1-是）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    INDEX `idx_student_id` (`student_id`),
    CONSTRAINT `fk_student_parent` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生家长表';

-- 插入测试数据
INSERT INTO `t_student` (`student_no`, `name`, `date_of_birth`, `gender`, `class_id`, `email`, `phone`, `address`, `status`, `enrollment_date`)
VALUES
    ('2023001', '张三', '2005-01-15', 1, 1, 'zhangsan@example.com', '13800138001', '北京市海淀区', 'ACTIVE', '2023-09-01'),
    ('2023002', '李四', '2005-03-22', 1, 1, 'lisi@example.com', '13800138002', '北京市朝阳区', 'ACTIVE', '2023-09-01'),
    ('2023003', '王五', '2005-06-10', 1, 2, 'wangwu@example.com', '13800138003', '北京市西城区', 'ACTIVE', '2023-09-01'),
    ('2023004', '赵六', '2005-08-18', 1, 2, 'zhaoliu@example.com', '13800138004', '北京市东城区', 'ACTIVE', '2023-09-01'),
    ('2023005', '钱七', '2005-11-25', 0, 3, 'qianqi@example.com', '13800138005', '北京市丰台区', 'ACTIVE', '2023-09-01'),
    ('2023006', '孙八', '2005-04-06', 0, 3, 'sunba@example.com', '13800138006', '北京市石景山区', 'ACTIVE', '2023-09-01'),
    ('2023007', '周九', '2005-07-14', 0, 1, 'zhoujiu@example.com', '13800138007', '北京市昌平区', 'ACTIVE', '2023-09-01'),
    ('2023008', '吴十', '2005-09-30', 0, 2, 'wushi@example.com', '13800138008', '北京市大兴区', 'ACTIVE', '2023-09-01');

-- 插入家长测试数据
INSERT INTO `t_student_parent` (`student_id`, `name`, `relationship`, `phone`, `email`, `occupation`, `work_place`, `is_primary`)
VALUES
    (1, '张大明', '父亲', '13900139001', 'zhangdaming@example.com', '工程师', '某科技公司', 1),
    (1, '王小丽', '母亲', '13900139002', 'wangxiaoli@example.com', '教师', '某中学', 0),
    (2, '李志强', '父亲', '13900139003', 'lizhiqiang@example.com', '医生', '某医院', 1),
    (2, '陈梅', '母亲', '13900139004', 'chenmei@example.com', '会计', '某公司', 0),
    (3, '王建国', '父亲', '13900139005', 'wangjianguo@example.com', '律师', '某律所', 1),
    (4, '赵丽华', '母亲', '13900139006', 'zhaolihua@example.com', '银行职员', '某银行', 1); 