package cn.cobight.annotation.custom2;


import java.lang.reflect.Method;
import java.util.Map;

/**
 * fileName:TestAnnotation
 * description:
 * author:zz
 * createTime:2020/8/27 9:50
 * version:1.0.0
 */
public class TestAnnotation {
    public static void main(String[] args) {
        //获取class对象
        Class<UserDao> userDaoClass = UserDao.class;

        try {
            //获取方法
            Method getUserByParam = userDaoClass.getDeclaredMethod("getUserByParam", Map.class);
            //获取指定反射类
            Select annotation = getUserByParam.getAnnotation(Select.class);
            if(annotation!=null){
                System.out.println(annotation);
                System.out.println("值："+annotation.value());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
