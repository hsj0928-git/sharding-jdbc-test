package com.jingnan.data.mapper;

import com.jingnan.data.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: sublun
 * @Date: 2021/5/21 15:55
 */
public interface OrderItemMapper extends Mapper<OrderItem> {
    List<OrderItem>  queryOrderItem();

    List<OrderItem> queryOrderItems();

    List<OrderItem> queryOrderItem1();

    List<OrderItem> queryOrderItemThread(@Param("value") String value);

    List findUser();
}
