package com.aaa.aop.service;

import com.aaa.aop.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * fileName:OrderServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/23 9:54
 * version:1.0.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> page(Map map) {
        System.out.println("模拟订单查询");
        return null;
    }

    @Override
    public int add(Order order) {
        System.out.println("模拟订单添加");
       //System.out.println("模拟日志记录功能1");
        return 0;
    }

    @Override
    public int update(Order order) {
        System.out.println("模拟订单更新");
        //System.out.println("模拟日志记录功能1");
        return 0;
    }

    @Override
    public int delete(int orderId) {
        System.out.println("模拟订单删除");
       // System.out.println("模拟日志记录功能1");
        return 0;
    }
}
