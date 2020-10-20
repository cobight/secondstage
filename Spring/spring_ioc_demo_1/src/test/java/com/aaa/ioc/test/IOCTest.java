package com.aaa.ioc.test;

import com.aaa.ioc.controller.UserController;
import org.junit.Test;

/**
 * fileName:IOCTest
 * description:
 * author:cobight
 * createTime:2020/9/21 19:11
 * version:1.0.0
 */
public class IOCTest {
    @Test
    public void testGetBean(){
        UserController.getUser();
    }
}
