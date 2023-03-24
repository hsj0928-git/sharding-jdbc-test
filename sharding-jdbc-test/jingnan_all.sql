/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.68.133
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.68.133:3306
 Source Schema         : jingnan_all

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 30/11/2021 23:42:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `total_num` int(11) NULL DEFAULT NULL COMMENT '数量合计',
  `total_money` int(11) NULL DEFAULT NULL COMMENT '金额合计',
  `pre_money` int(11) NULL DEFAULT NULL COMMENT '优惠金额',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '邮费',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `pay_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付类型，1、在线支付、0 货到付款',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '订单更新时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `buyer_message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家留言',
  `buyer_rate` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否评价',
  `receiver_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `receiver_mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人地址',
  `source_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面',
  `transaction_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `order_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  `consign_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货状态',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE,
  INDEX `status`(`order_status`) USING BTREE,
  INDEX `payment_type`(`pay_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item0
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item0`;
CREATE TABLE `tb_order_item0`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id1` int(11) NULL DEFAULT NULL COMMENT '1级分类',
  `category_id2` int(11) NULL DEFAULT NULL COMMENT '2级分类',
  `category_id3` int(11) NULL DEFAULT NULL COMMENT '3级分类',
  `spu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SPU_ID',
  `sku_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU_ID',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `weight` int(11) NULL DEFAULT NULL COMMENT '重量',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '运费',
  `is_return` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`sku_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item1
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item1`;
CREATE TABLE `tb_order_item1`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id1` int(11) NULL DEFAULT NULL COMMENT '1级分类',
  `category_id2` int(11) NULL DEFAULT NULL COMMENT '2级分类',
  `category_id3` int(11) NULL DEFAULT NULL COMMENT '3级分类',
  `spu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SPU_ID',
  `sku_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU_ID',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `weight` int(11) NULL DEFAULT NULL COMMENT '重量',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '运费',
  `is_return` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`sku_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item2
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item2`;
CREATE TABLE `tb_order_item2`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id1` int(11) NULL DEFAULT NULL COMMENT '1级分类',
  `category_id2` int(11) NULL DEFAULT NULL COMMENT '2级分类',
  `category_id3` int(11) NULL DEFAULT NULL COMMENT '3级分类',
  `spu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SPU_ID',
  `sku_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU_ID',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `weight` int(11) NULL DEFAULT NULL COMMENT '重量',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '运费',
  `is_return` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`sku_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item3
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item3`;
CREATE TABLE `tb_order_item3`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id1` int(11) NULL DEFAULT NULL COMMENT '1级分类',
  `category_id2` int(11) NULL DEFAULT NULL COMMENT '2级分类',
  `category_id3` int(11) NULL DEFAULT NULL COMMENT '3级分类',
  `spu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SPU_ID',
  `sku_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU_ID',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `weight` int(11) NULL DEFAULT NULL COMMENT '重量',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '运费',
  `is_return` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`sku_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_order_item4
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item4`;
CREATE TABLE `tb_order_item4`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `category_id1` int(11) NULL DEFAULT NULL COMMENT '1级分类',
  `category_id2` int(11) NULL DEFAULT NULL COMMENT '2级分类',
  `category_id3` int(11) NULL DEFAULT NULL COMMENT '3级分类',
  `spu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SPU_ID',
  `sku_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU_ID',
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `money` int(11) NULL DEFAULT NULL COMMENT '总金额',
  `pay_money` int(11) NULL DEFAULT NULL COMMENT '实付金额',
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `weight` int(11) NULL DEFAULT NULL COMMENT '重量',
  `post_fee` int(11) NULL DEFAULT NULL COMMENT '运费',
  `is_return` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否退货',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`sku_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '修改时间',
  `source_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员来源：1:PC，2：H5，3：Android，4：IOS',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用状态（1正常 0非正常）',
  `head_pic` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ号码',
  `is_mobile_check` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '手机是否验证 （0否  1是）',
  `is_email_check` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '邮箱是否检测（0否  1是）',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '性别，1男，0女',
  `user_level` int(11) NULL DEFAULT NULL COMMENT '会员等级',
  `points` int(11) NULL DEFAULT NULL COMMENT '积分',
  `experience_value` int(11) NULL DEFAULT NULL COMMENT '经验值',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生年月日',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`username`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- 广播表测试
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
                           `id` bigint(0) PRIMARY KEY NOT NULL,
                           `status_code` varchar(100)  NULL,
                           `status_value` varchar(100)  NULL,
                           `enabled` tinyint(1)  NULL DEFAULT 1
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
