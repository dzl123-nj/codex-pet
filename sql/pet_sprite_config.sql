-- Pet sprite configuration table.
-- Stores sprite sheet metadata and action definitions per pet type.
-- Run once after pet_type table exists.

CREATE TABLE pet_sprite_config (
  id           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  pet_type_id  bigint(20)   NOT NULL COMMENT '关联 pet_type.id',
  sprite_image varchar(500) DEFAULT NULL COMMENT 'sprite 图片路径',
  cell_w       int(11)      DEFAULT 64 COMMENT '每格宽度(px)',
  cell_h       int(11)      DEFAULT 64 COMMENT '每格高度(px)',
  fps          int(11)      DEFAULT 8  COMMENT '动画帧率',
  actions      text         COMMENT '动作定义 JSON 数组',
  status       char(1)      DEFAULT '0' COMMENT '状态：0=启用，1=停用',
  create_time  datetime     DEFAULT NULL COMMENT '创建时间',
  update_time  datetime     DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY uk_pet_type_sprite (pet_type_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='宠物精灵图表配置';
