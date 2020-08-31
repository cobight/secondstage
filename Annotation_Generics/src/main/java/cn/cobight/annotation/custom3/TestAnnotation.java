package cn.cobight.annotation.custom3;

import java.lang.reflect.Field;

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
        Class<UserServiceImpl> userServiceClassClass = UserServiceImpl.class;
        try {
            //反射获取field
            Field userDao = userServiceClassClass.getDeclaredField("userDao");
            //获取注解
            Autowired annotation = userDao.getAnnotation(Autowired.class);
            if (annotation!=null){
                System.out.println(annotation);
                System.out.println(annotation.name());
                System.out.println(annotation.name1());
                System.out.println(annotation.name2());
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
