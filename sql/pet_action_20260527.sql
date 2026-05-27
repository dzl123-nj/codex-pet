-- QQ-pet style desktop pet action system.
-- Run once after the base RuoYi pet tables are created.

ALTER TABLE virtual_pet
  ADD COLUMN intimacy bigint(20) DEFAULT 0 COMMENT '亲密度：0-100' AFTER status,
  ADD COLUMN growth_stage varchar(20) DEFAULT 'child' COMMENT '成长阶段：child/growing/adult' AFTER intimacy,
  ADD COLUMN mood_state varchar(30) DEFAULT 'normal' COMMENT '当前情绪状态' AFTER growth_stage,
  ADD COLUMN desktop_x bigint(20) DEFAULT NULL COMMENT '桌面宠物横坐标' AFTER mood_state,
  ADD COLUMN desktop_y bigint(20) DEFAULT NULL COMMENT '桌面宠物纵坐标' AFTER desktop_x,
  ADD COLUMN desktop_visible tinyint(1) DEFAULT 1 COMMENT '是否显示桌面宠物：0-隐藏，1-显示' AFTER desktop_y,
  ADD COLUMN last_interact_time datetime DEFAULT NULL COMMENT '最后互动时间' AFTER desktop_visible,
  ADD COLUMN last_decay_time datetime DEFAULT NULL COMMENT '最后属性衰减时间' AFTER last_interact_time;

CREATE TABLE pet_action (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动作ID',
  action_code varchar(50) NOT NULL COMMENT '动作编码',
  action_name varchar(50) NOT NULL COMMENT '动作名称',
  trigger_type varchar(20) DEFAULT 'user' COMMENT '触发类型：auto/user/status/timed',
  duration bigint(20) DEFAULT 1200 COMMENT '动作持续时长，毫秒',
  cooldown bigint(20) DEFAULT 0 COMMENT '冷却时间，秒',
  hunger_change bigint(20) DEFAULT 0 COMMENT '饱食变化',
  happiness_change bigint(20) DEFAULT 0 COMMENT '心情变化',
  energy_change bigint(20) DEFAULT 0 COMMENT '精力变化',
  cleanliness_change bigint(20) DEFAULT 0 COMMENT '清洁变化',
  health_change bigint(20) DEFAULT 0 COMMENT '健康变化',
  intimacy_change bigint(20) DEFAULT 0 COMMENT '亲密度变化',
  experience_reward bigint(20) DEFAULT 0 COMMENT '奖励经验',
  bubble_text varchar(255) DEFAULT NULL COMMENT '气泡文案',
  animation_name varchar(50) DEFAULT NULL COMMENT '前端动画名称',
  sort_order bigint(20) DEFAULT 0 COMMENT '排序',
  enabled tinyint(1) DEFAULT 1 COMMENT '是否启用：0-停用，1-启用',
  deleted tinyint(1) DEFAULT 0 COMMENT '逻辑删除：0-未删除，1-已删除',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_pet_action_code (action_code)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='宠物动作定义表';

CREATE TABLE pet_action_record (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  pet_id bigint(20) NOT NULL COMMENT '宠物ID',
  user_id bigint(20) DEFAULT NULL COMMENT '用户ID',
  action_code varchar(50) NOT NULL COMMENT '动作编码',
  action_name varchar(50) DEFAULT NULL COMMENT '动作名称',
  trigger_source varchar(20) DEFAULT 'user' COMMENT '触发来源：user/auto/status/system',
  before_snapshot text COMMENT '动作前快照',
  after_snapshot text COMMENT '动作后快照',
  reward_exp bigint(20) DEFAULT 0 COMMENT '奖励经验',
  bubble_text varchar(255) DEFAULT NULL COMMENT '气泡文案',
  action_time datetime DEFAULT NULL COMMENT '动作时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id),
  KEY idx_pet_action_record_pet (pet_id),
  KEY idx_pet_action_record_user (user_id),
  KEY idx_pet_action_record_action (action_code),
  KEY idx_pet_action_record_time (action_time)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='宠物动作记录表';

INSERT INTO pet_action
(action_code, action_name, trigger_type, duration, cooldown, hunger_change, happiness_change, energy_change, cleanliness_change, health_change, intimacy_change, experience_reward, bubble_text, animation_name, sort_order, enabled, deleted, create_time)
VALUES
('idle', '待机', 'auto', 1800, 0, 0, 0, 0, 0, 0, 0, 0, '我在这里陪着你。', 'breathe', 10, 1, 0, NOW()),
('walk', '散步', 'auto', 900, 8, 0, 1, -1, 0, 0, 0, 0, '去屏幕边上溜达一下。', 'walk', 20, 1, 0, NOW()),
('talk', '冒泡说话', 'timed', 1200, 10, 0, 2, 0, 0, 0, 1, 1, '工作辛苦啦，记得喝水。', 'talk', 30, 1, 0, NOW()),
('drag', '拖拽', 'user', 300, 0, 0, 1, 0, 0, 0, 1, 0, '搬家完成。', 'drag', 40, 1, 0, NOW()),
('feed', '喂食', 'user', 1400, 5, 20, 5, 0, -2, 2, 2, 5, '吃饱了，精神一点点回来了。', 'eat', 50, 1, 0, NOW()),
('clean', '清洁', 'user', 1600, 8, 0, 4, 0, 25, 5, 2, 5, '香香软软，清爽完成。', 'bath', 60, 1, 0, NOW()),
('play', '玩耍', 'user', 1800, 8, -3, 20, -10, -5, 0, 5, 10, '玩得好开心。', 'play', 70, 1, 0, NOW()),
('sleep', '睡觉', 'user', 1200, 5, -1, 0, 15, 0, 2, 1, 2, '我先睡一小会儿。', 'sleep', 80, 1, 0, NOW()),
('wake', '叫醒', 'user', 1000, 3, 0, 2, -2, 0, 0, 1, 2, '醒啦，伸个懒腰。', 'wake', 90, 1, 0, NOW()),
('sick', '生病', 'status', 1600, 30, -3, -8, -8, -5, -10, 0, 0, '有点不舒服，需要照顾。', 'sick', 100, 1, 0, NOW());
