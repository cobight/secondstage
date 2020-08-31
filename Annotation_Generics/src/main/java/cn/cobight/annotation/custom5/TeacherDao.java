package cn.cobight.annotation.custom5;

import java.util.List;
import java.util.Map;

/**
 * fileName:TeacherDao
 * description:
 * author:zz
 * createTime:2020/8/27 11:22
 * version:1.0.0
 */
public interface TeacherDao {

    /**
     * 根据参数获取列表   不传递参数，查询全部，  传入不同参数，就查询不同结果
     * @param teacher
     * @return
     */
    List<Map>  listTeacherByParams(Teacher teacher);
}
