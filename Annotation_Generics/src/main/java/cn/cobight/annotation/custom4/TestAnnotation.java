package cn.cobight.annotation.custom4;


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
        Class<BaseController> baseControllerClass = BaseController.class;
        Class<DeptController> deptControllerClass = DeptController.class;
        //获取@RestController注解
        RestController annotation = baseControllerClass.getAnnotation(RestController.class);
        RestController annotation1 = deptControllerClass.getAnnotation(RestController.class);
        System.out.println(annotation);
        System.out.println(annotation1);
    }
}
