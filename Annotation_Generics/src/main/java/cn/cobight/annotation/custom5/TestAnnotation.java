package cn.cobight.annotation.custom5;


import java.util.List;
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
        //实例化
        TeacherDao teacherDao =new TeacherDaoImpl();
        Teacher teacher = new Teacher();
//        teacher.setAge(10);
        teacher.setEmail("7777@qq.com,8888@qq.com");
        List<Map> maps = teacherDao.listTeacherByParams(teacher);
        System.out.println(maps);
    }
}
