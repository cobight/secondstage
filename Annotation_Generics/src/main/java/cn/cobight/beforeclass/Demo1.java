package cn.cobight.beforeclass;

/**
 * @ClassName Demo1
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/28 8:30
 * @Version 1.0'
 *
 * 编写MySelect注解，可以使用于方法上
 * 创建接口TeacherDao,编写List<Map> listTeacher()方法，并使用MySelect注解，注解中编写语句select * from tb_teacher
 * 编写TeacherDaoImpl实现类，重写List<Map> listTeacher()方法，获取注解中语句内容，并调用BaseDao,执行查询结果
 * 编写TestTeacher类，打印返回结果
 **/
public class Demo1 {

}
