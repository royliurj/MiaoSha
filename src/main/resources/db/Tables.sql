# 订单详情
CREATE TABLE `miaosha`.`order_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL COMMENT '用户ID',
  `goods_id` BIGINT(20) NULL COMMENT '商品ID',
  `delivery_addr_id` BIGINT(20) NULL COMMENT '收获地址ID',
  `goods_name` VARCHAR(45) NULL COMMENT '冗余过来的商品名称',
  `goods_count` INT NULL COMMENT '商品数量',
  `goods_price` DECIMAL(10,2) NULL COMMENT '商品价格',
  `order_channel` TINYINT(4) NULL COMMENT '1pc,2android,3ios',
  `status` TINYINT(4) NULL COMMENT '订单状态,0新建未支付，1已支付，2已发货，3已收获，4已退款，5已完成',
  `create_date` DATETIME NULL,
  `pay_date` DATETIME NULL COMMENT '支付时间',
  PRIMARY KEY (`id`));

# 秒杀订单
CREATE TABLE `miaosha`.`miaosha_order` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NULL,
  `order_id` BIGINT(20) NULL,
  `goods_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`));

