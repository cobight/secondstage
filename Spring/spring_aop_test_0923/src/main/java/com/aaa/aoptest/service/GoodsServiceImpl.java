package com.aaa.aoptest.service;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:GoodsServiceImpl
 * description:
 * author:cobight
 * createTime:2020/9/24 8:32
 * version:1.0.0
 */
public class GoodsServiceImpl implements GoodService
{
    @Override
    public int delete() {
        System.out.println("模拟商品删除");
        List list = new ArrayList();
        list.add(1);
        list.get(2);
        //throw new IndexOutOfBoundsException("数组越界异常");


        return 0;
    }
}
