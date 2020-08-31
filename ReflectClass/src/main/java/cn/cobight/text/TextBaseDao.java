package cn.cobight.text;

import cn.cobight.entity.Boy;
import cn.cobight.entity.Teacher;
import cn.cobight.util.BaseDao;

import java.util.List;

/**
 * @ClassName TextBaseDao
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/26 16:04
 * @Version 1.0
 **/
public class TextBaseDao {
    public static void main(String[] args) {
//        List<Map<String, Object>> maps = BaseDao.selectAll("select * from tb_teacher");
//        if (maps!=null && maps.size()>0){
//            for (Map<String, Object> map : maps) {
//                Set keySet = map.keySet();
//                for (Object key : keySet) {
//                    System.out.println("column: "+key+", value: "+map.get(key));
//                }
//                System.out.println("----------------------------");
//            }
//        }

//        List<Teacher> teachers = BaseDao.selectEntityByParam("select id,realname,username,age from tb_teacher", Teacher.class);
//        for (Teacher teacher : teachers) {
//            System.out.println("名字："+teacher.getRealname()+", 工号："+teacher.getUsername());
//        }
        List<Boy> teachers = BaseDao.selectEntityByParam2("select id,real_name,user_name,age from tb_teacher1", Boy.class);
        for (Boy teacher : teachers) {
            System.out.println("名字："+teacher.getRealName()+", 工号："+teacher.getUserName());
        }
    }
}
