package cn.cobight.beforeclass;

/**
 * @ClassName TestTeacher
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/28 8:37
 * @Version 1.0
 **/
public class TestTeacher {
    public static void main(String[] args) {
        TeacherDao t= new TeacherDaoImpl();
        System.out.println(t.listTeacher());
    }
}
