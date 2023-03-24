package com.jingnan.data.service;

import com.jingnan.data.mapper.*;
import com.jingnan.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


/**
 * @Author: sublun
 * 将本地数据库中的用户信息导入到分片数据库中
 */
@Service
public class DataService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderItemCsMapper orderItemCsMapper;
    @Autowired
    private DictMapper dictMapper;

    public Result insertOrder(Order order) {
        orderMapper.insert(order);
        return Result.builder().build();
    }

    public Result insertOrderItem(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
        return Result.builder().build();
    }
    public Result insertUser(User user) {
        userMapper.insert(user);
        return Result.builder().build();
    }


    public Result insertItemCs(OrderItemCs itemCs) {
        orderItemCsMapper.insert(itemCs);
        return Result.builder().build();
    }
    /**
     *  <p>方法描述：查询</p>
     * @MethodAuthor hsj 2022-10-27 9:22
     */
    public void queryItemCs() {
        orderItemCsMapper.selectAll();

    }

    public Result queryOrderItem() {
        List<OrderItem> oo = orderItemMapper.queryOrderItem();
        System.out.println(oo.size());
        return Result.builder().build();
    }
    public Result queryOrderItems() {
        List<OrderItem> oo = orderItemMapper.queryOrderItems();
        System.out.println(oo.size());
        return Result.builder().build();
    }

    public Result insertDict(Dict dict) {
        dictMapper.insert(dict);
        return Result.builder().build();
    }

    public Result queryOrderItem1() {
        System.out.println("开始查询："+ new Date());
        List<OrderItem> oo = orderItemMapper.queryOrderItem1();
        System.out.println("结束查询："+ new Date());
        System.out.println(oo.size());
        return Result.builder().build();
    }

    public List<OrderItem> queryOrderItemThread(String value) {
        return orderItemMapper.queryOrderItemThread(value);
    }

    public void queryUser() {
        List<User>  users =  userMapper.selectAll();
        if(!CollectionUtils.isEmpty(users)){
           for (User u:users){
               System.out.println(u.toString());
           }
        }
//      List list =   orderItemMapper.findUser();
//      if(!CollectionUtils.isEmpty(list)){
//           for (Object u:list){
//               System.out.println(u.toString());
//           }
//        }
    }
}