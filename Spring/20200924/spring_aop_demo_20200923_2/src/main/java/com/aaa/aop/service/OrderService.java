package com.aaa.aop.service;

import com.aaa.aop.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * fileName:OrderService
 * description:
 * author:zz
 * createTime:2020/9/23 9:50
 * version:1.0.0
 */
public interface OrderService {

    /**
     * 分页列表
     * @param map
     * @return
     */
    List<Order> page(Map map);

    /**
     * 添加
     * @param Order
     * @return
     */
    int add(Order Order);
    /**
     * 更新
     * @param Order
     * @return
     */
    int update(Order Order);

    /**
     * 删除
     * @param OrderId
     * @return
     */
    int delete(int OrderId);
}
