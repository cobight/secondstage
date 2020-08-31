package cn.cobight.beforeclass;

import java.util.List;
import java.util.Map;

public interface TeacherDao {
    @MySelect(value = "select * from tb_teacher")
    List<Map> listTeacher();
}
