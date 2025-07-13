-- 创建教师表
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `teacher_no` varchar(20) NOT NULL COMMENT '教师工号',
  `name` varchar(50) NOT NULL COMMENT '教师姓名',
  `gender` tinyint DEFAULT NULL COMMENT '性别（0:女 1:男）',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `hire_date` date DEFAULT NULL COMMENT '入职日期',
  `title` tinyint DEFAULT NULL COMMENT '职称（1:讲师 2:副教授 3:教授）',
  `education` tinyint DEFAULT NULL COMMENT '学历（1:学士 2:硕士 3:博士）',
  `specialization` varchar(100) DEFAULT NULL COMMENT '专业领域',
  `department_id` bigint DEFAULT NULL COMMENT '所在院系ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标志（0:未删除 1:已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_teacher_no` (`teacher_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师表';

-- 创建院系表
CREATE TABLE IF NOT EXISTS `department` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '院系名称',
  `code` varchar(20) NOT NULL COMMENT '院系编码',
  `description` varchar(500) DEFAULT NULL COMMENT '院系描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标志（0:未删除 1:已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='院系表';

-- 初始化测试数据
INSERT INTO `department` (`id`, `name`, `code`, `description`, `create_time`, `update_time`, `create_by`, `update_by`, `deleted`)
VALUES
(1, '计算机科学与技术学院', 'CS001', '计算机科学与技术学院是培养高素质IT人才的重要基地', NOW(), NOW(), 'admin', 'admin', 0),
(2, '数学学院', 'MATH001', '数学学院专注于培养数学理论与应用的专业人才', NOW(), NOW(), 'admin', 'admin', 0),
(3, '外国语学院', 'FL001', '外国语学院致力于培养具有国际视野的语言人才', NOW(), NOW(), 'admin', 'admin', 0);

-- 初始化教师测试数据
INSERT INTO `teacher` (`id`, `teacher_no`, `name`, `gender`, `age`, `birthday`, `phone`, `email`, `address`, `hire_date`, `title`, `education`, `specialization`, `department_id`, `create_time`, `update_time`, `create_by`, `update_by`, `deleted`)
VALUES
(1, 'T20010001', '张教授', 1, 45, '1978-05-15', '13800138001', 'zhang@example.com', '北京市海淀区', '2010-09-01', 3, 3, '人工智能,机器学习', 1, NOW(), NOW(), 'admin', 'admin', 0),
(2, 'T20120002', '李副教授', 0, 38, '1985-11-20', '13800138002', 'li@example.com', '北京市朝阳区', '2012-07-01', 2, 3, '计算机网络,云计算', 1, NOW(), NOW(), 'admin', 'admin', 0),
(3, 'T20150003', '王讲师', 1, 32, '1991-03-08', '13800138003', 'wang@example.com', '北京市西城区', '2015-08-15', 1, 2, '数据库,大数据', 1, NOW(), NOW(), 'admin', 'admin', 0),
(4, 'T20110004', '赵教授', 1, 50, '1973-09-18', '13800138004', 'zhao@example.com', '上海市徐汇区', '2011-06-01', 3, 3, '数学分析,微积分', 2, NOW(), NOW(), 'admin', 'admin', 0),
(5, 'T20160005', '刘讲师', 0, 30, '1993-07-25', '13800138005', 'liu@example.com', '广州市天河区', '2016-07-01', 1, 2, '英语语言文学,翻译理论', 3, NOW(), NOW(), 'admin', 'admin', 0); 