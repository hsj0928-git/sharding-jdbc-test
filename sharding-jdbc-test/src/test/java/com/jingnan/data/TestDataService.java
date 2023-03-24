package com.jingnan.data;

import com.jingnan.data.config.IdWorker;
import com.jingnan.data.entity.Order;
import com.jingnan.data.entity.OrderItem;
import com.jingnan.data.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: sublun
 * @Date: 2021/11/29 21:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplication.class)
public class TestDataService {

    @Autowired
    private DataService dataService;
    @Autowired
    private IdWorker idWorker;
    /**
     * 插入100条订单数据
     */
    @Test
    public void testInsertOrder() {
        for (int i = 1; i <= 100; i++) {
            String orderId  = idWorker.nextId() + "";
            System.out.println(orderId);
            Order order = new Order();
            order.setId(orderId);
            String userName = "zhangsan" + i;
            order.setUsername(userName);
            order.setTotalNum(10);
            dataService.insertOrder(order);
        }
    }

    /**
     * 插入100条订单，每个订单包含10条明细
     */
    @Test
    public void testInsertOrderItem() {
        for (int i = 1; i <= 100; i++) {
            String orderId  = idWorker.nextId() + "";
            System.out.println(orderId);
            Order order = new Order();
            order.setId(orderId);
            String userName = "zhangsan" + i;
            order.setUsername(userName);
            order.setTotalNum(10);
            dataService.insertOrder(order);
            for (int j = 0; j < 10; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(idWorker.nextId() + "");
                orderItem.setOrderId(orderId);
                orderItem.setName(userName);
                orderItem.setSkuId("123456");
                dataService.insertOrderItem(orderItem);
            }
        }
    }
}
