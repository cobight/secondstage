package cn.cobight.beforeclass;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherDaoImpl
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/28 8:36
 * @Version 1.0
 **/
public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Map> listTeacher() {
        Class<TeacherDao> teacherDaoClass = TeacherDao.class;
        try {
            Method method = teacherDaoClass.getDeclaredMethod("listTeacher");
            MySelect annotation = method.getAnnotation(MySelect.class);
            String sql = annotation.value();
            return BaseDao.selectAll(sql);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
