package com.jn;

import com.alibaba.fastjson.JSONObject;
import com.jingnan.data.DataApplication;
import com.jingnan.data.config.CountConstants;
import com.jingnan.data.config.IdWorker;
import com.jingnan.data.entity.*;
import com.jingnan.data.service.DataService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: sublun
 * @Date: 2021/11/29 21:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataApplication.class)
public class TestDataService {

    private static final Logger log = LoggerFactory.getLogger(TestDataService.class);

    @Autowired
    private DataService dataService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private ExecutorService deleteExecutorService;
    /**
     * 插入100条订单数据
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testInsertOrder() {
        for (int i = 1; i <= 2; i++) {
            String orderId  = idWorker.nextId() + "";
            System.out.println(orderId);
            Order order = new Order();
            order.setId(orderId);
            String userName = "hsj-01" + i;
            order.setUsername(userName);
            order.setTotalNum(10);
            order.setSourceType(i%2);
            dataService.insertOrder(order);
        }
    }

    /**
     * 插入100条订单，每个订单包含10条明细
     * 1、查询来源为1 -主订单
     * 2、查询子订单 数量超过5且未退货  支付方式为1 且 订单状态为1
     */
    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testInsertOrderItem() {
        for (int i = 1; i <= 1; i++) {
            String orderId  = idWorker.nextId() + "";
            System.out.println(orderId);
            Order order = new Order();
            order.setId(orderId);
            String userName = "qqq" + i;
            order.setUsername(userName);
            //总价
            order.setTotalNum(i*i);
            //来源
            order.setSourceType(i%2);
            //支付方式
            order.setPayType((i%2)+"");
            //创建时间
            order.setCreateTime(new Date());
            //物流单号
            order.setShippingCode(orderId);
            //是否评价
            order.setBuyerRate((i%2)+"");
            //订单状态
            order.setOrderStatus((i%2)+"");
            dataService.insertOrder(order);
            for (int j = 0; j < 10; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(idWorker.nextId() + "");
                orderItem.setOrderId(orderId);
                orderItem.setName(userName);
                orderItem.setSkuId("123456");
                //单价
                orderItem.setPrice(i);
                //数量
                orderItem.setNum(j);
                //总金额
                orderItem.setMoney(orderItem.getPrice()*orderItem.getNum());
                orderItem.setWeight(i);
                orderItem.setSourceType(i%2);
                //是否退货（1：退货；0：未退货）
                orderItem.setIsReturn((j%2)+"");
                dataService.insertOrderItem(orderItem);
            }
        }
    }
    /**
     *  <p>方法描述：单线程查询</p>
     * @MethodAuthor hsj 2022-11-10 15:27
     */
    @Test
    public void queryOrderItem1() {
        dataService.queryOrderItem1();
    }
    /**
     *  <p>方法描述：多线程查询</p>
     * @MethodAuthor hsj 2022-11-10 15:27
     */
    @Test
    public void queryOrderItem2() {
    }

    /**
     *  <p>方法描述：主子表查询</p>
     * @MethodAuthor hsj 2022-10-31 15:35
     */
    @Test
    public void queryOrderItem() {

//        dataService.queryOrderItem();
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        dataService.queryOrderItemThread(null);
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    }
    @Test
    @Async("deleteExecutorService")
    public void queryOrderItem11() {
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        Map<String, String> allType = CountConstants.getAllType();
        CompletionService completionService = new ExecutorCompletionService(deleteExecutorService);
        allType.forEach((key, value)->{
            if(StringUtils.isNotBlank(value)){
                //线程异步导入数据库,会异步开始执行新增方法,同时原线程不会等待，继续执行。实现了异步操作。
                //execute()无返回值 execute()只能执行实现Runnable接口类型的任务;而submit()不仅可以执实现
                completionService.submit(() -> dataService.queryOrderItemThread(value));
            }
        });
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    }

    @Test
    public void natualUserCityCount() throws Exception {
        // 根据存放量 指定map大小  避免扩容  【此处为多线程，故map为线程安全的ConcurrentHashMap】
//        Map<String, List<OrderItem>> result = new ConcurrentHashMap<>(CountConstants.MAP_SIZE);
        // 根据某个属性 统计不同的(本次测试：不同的支付方式)
        Map<String, String> allType = CountConstants.getAllType();
        // 线程数量 计算 因为值为空 无需查询数据库 故过滤值为空的数量
        int count = Math.toIntExact(allType.values().stream().filter(areaVal -> StringUtils.isNotBlank(areaVal)).count());
        log.info("需等待进程数：" + count+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(15, 30,

                10, TimeUnit.MICROSECONDS, new SynchronousQueue<>());
        //  因需要接收返回值 故此处需根据线程数 计算线程是否全部执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(count);
        // 循环获取常量类中的区域值   一个区域开启一个线程
        allType.forEach((key, value)->{
            if(StringUtils.isNotBlank(value)){
                threadPoolExecutor.submit(() -> {
                    log.info("{}执行开始|{}:{}", Thread.currentThread().getName());
                    //查询
                    List<OrderItem> orderItems = dataService.queryOrderItemThread(value);
                    log.info("{}执行完毕|{}:{}:{}", Thread.currentThread().getName());
                    // 业务执行完毕 必须手动标记改线程结束 此处对应上面CountDownLatch ， 若次数不手动操作，则主线程一直为等待状态 认为线程没有全部执行完毕
                    countDownLatch.countDown();
                    log.info("{}执行完毕|{}", Thread.currentThread().getName());
                });
            }
        });
        // 线程池关闭
        threadPoolExecutor.shutdown();
        // 等待所有线程执行完毕
        countDownLatch.await();
        log.info("所有线程执行完毕"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    }
    @Test
    public void queryOrderItems() {
        dataService.queryOrderItems();
    }


    /**
     *  <p>方法描述：插入用户</p>
     * @MethodAuthor hsj 2022-10-12 11:09
     */
    @Test
    public void testInsertUser() {
        for (int i = 1; i <= 2; i++) {
            String orderId  = idWorker.nextId() + "";
            User user = new User();
            user.setUsername(orderId);
            user.setName("ii"+i);
            user.setPassword("123456");
            user.setPhone("1836236545"+i);
            user.setCreated(new Date());
            user.setUpdated(new Date());
            user.setBirthday(new Date());
            dataService.insertUser(user);
        }
    }
    @Test
    public void queryUser() {
        dataService.queryUser();
    }
    @Test
    public void testItemCs() {
        for (int i = 1; i <= 5; i++) {
            String orderId  = idWorker.nextId() + "";
            OrderItemCs itemCs = new OrderItemCs();
            itemCs.setId(orderId);
            itemCs.setName("aa"+i);
            dataService.insertItemCs(itemCs);
        }
    }
    /**
     *  <p>方法描述：查询</p>
     * @MethodAuthor hsj 2022-10-27 9:22
     */
    @Test
    public void queryItemCs() {
        dataService.queryItemCs();
    }
    /**
     *  <p>方法描述：广播表测试</p>
     * @MethodAuthor hsj 2022-10-31 15:35
     */
    @Test
    public void testSave() {
        Dict dict = new Dict();
        dict.setId(idWorker.nextId());
        dict.setEnabled(1);
        dict.setStatusCode("1");
        dict.setStatusValue("正常");
        dataService.insertDict(dict);

        Dict dict2 = new Dict();
        dict2.setId(idWorker.nextId());
        dict.setEnabled(1);
        dict2.setStatusCode("2");
        dict2.setStatusValue("待认证");
        dataService.insertDict(dict2);
    }

//    public static void main(String[] args) {
//        //常规策略
////        getHashCode("1580028152206704640");
////        getHashCode("1580028152554831873");
//    }
    private static String str =null;
    public static void main(String[] args) {
        System.out.println("test exception");
        try {
//            if(str.equals("name")){
//                System.out.println("test exception");
//            }
            BigDecimal a =  new BigDecimal(0);
            BigDecimal b =  new BigDecimal(0);
            BigDecimal b1 = b.divide(a);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println("异常信息："+e);
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    private static void getHashCode(String var) {
        Integer hashCode = var.hashCode();
        System.out.println(hashCode);
        System.out.println(Math.abs(hashCode) % 2);

    }
}

