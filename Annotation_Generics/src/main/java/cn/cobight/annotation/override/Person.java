package cn.cobight.annotation.override;

import java.util.Map;

/**
 * fileName:Person
 * description:
 * author:zz
 * createTime:2020/8/27 8:51
 * version:1.0.0
 */
public  abstract class Person {


    /**
     * 自我介绍
     * @param name
     * @param age
     * @return
     */
     abstract Map introduce(String name, int age) throws  Exception;
}
