-- 如果表存在则删除
DROP TABLE IF EXISTS `t_student`;

-- 创建学生表
CREATE TABLE `t_student` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_no` varchar(20) NOT NULL COMMENT '学号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` tinyint DEFAULT NULL COMMENT '性别，0-女，1-男',
  `class_id` bigint DEFAULT NULL COMMENT '班级ID',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除，0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_no` (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 插入测试数据
INSERT INTO `t_student` (`student_no`, `name`, `age`, `gender`, `class_id`, `phone`, `email`, `address`, `create_time`, `update_time`, `deleted`)
VALUES
('S001', '张三', 20, 1, 1, '13800138001', 'zhangsan@example.com', '北京市朝阳区', NOW(), NOW(), 0),
('S002', '李四', 19, 1, 1, '13800138002', 'lisi@example.com', '北京市海淀区', NOW(), NOW(), 0),
('S003', '王五', 21, 1, 2, '13800138003', 'wangwu@example.com', '上海市浦东新区', NOW(), NOW(), 0),
('S004', '赵六', 18, 0, 2, '13800138004', 'zhaoliu@example.com', '上海市静安区', NOW(), NOW(), 0),
('S005', '钱七', 20, 0, 3, '13800138005', 'qianqi@example.com', '广州市天河区', NOW(), NOW(), 0); 