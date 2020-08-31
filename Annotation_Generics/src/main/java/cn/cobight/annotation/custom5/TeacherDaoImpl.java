package cn.cobight.annotation.custom5;

import cn.cobight.beforeclass.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * fileName:TeacherDaoImpl
 * description:
 * author:zz
 * createTime:2020/8/27 11:24
 * version:1.0.0
 */
public class TeacherDaoImpl implements TeacherDao {

    @Override
    public List<Map> listTeacherByParams(Teacher teacher) {
            try {
                    //获取teacher的class对象
                    Class teacherClass = teacher.getClass();
                System.out.println("11111111111111");
                    //判断是否存在Table
                    boolean annotationPresentTable = teacherClass.isAnnotationPresent(Table.class);
                System.out.println(annotationPresentTable);
                    //如果不存在table注解，直接返回null
                    if(!annotationPresentTable){
                        return null;
                    }
                System.out.println("2222");
                    //获取库名和表名称
                    Table annotationTable = (Table)teacherClass.getAnnotation(Table.class);
                    //库名称
                    String schema = annotationTable.schema();
                    //表名
                    String tableName = annotationTable.tableName();
                    //拼装SQL
                    StringBuffer sql = new StringBuffer();
                    //使用StringBuffer效率高
                    sql.append("select * from "+schema+"."+tableName+" where 1=1 ");
                    // select * from qy119.tb_teacher1 where 1=1
                    //获取所有属性类
                    Field[] declaredFields = teacherClass.getDeclaredFields();
                    //循环
                    for (Field declaredField : declaredFields) {
                        //根据属性类获取注解  拿到列名称 拼装  and ..=..  条件语句
                        Column annotationColumn = declaredField.getAnnotation(Column.class);
                        //拿到列名称  第一次循环  id   第2次  user_name    ....最后 email
                        String columnName = annotationColumn.name();
                        //获取属性名称 第一次   id   第2次  userName
                        String fieldName = declaredField.getName();
                        //执行get方法拿到属性值， 拼装get方法
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);


                        //通过反射和方法名称，拿到当前方法对象
                        Method declaredMethod = teacherClass.getDeclaredMethod(getMethodName);
                        //通过get方法获取当前属性的值
                        Object returnValue = declaredMethod.invoke(teacher);
                        //判断返回值是否为空，不为空，拼装sql语句
                        if (returnValue != null) {
                            if (returnValue instanceof Integer) {
                                sql.append(" and " + columnName + "=" + returnValue);
                            } else if (returnValue instanceof String) {
                                String returnStrValue = (String) returnValue;
                                if ((returnStrValue).contains(",")) { // ['zhangsanfeng','mayun']
                                    String[] strArray = returnStrValue.split(",");
                                    sql.append(" and " + columnName + " in (");
                                    for (String str : strArray) {
                                        sql.append("'" + str + "',");
                                    }
                                    sql.delete(sql.length()-1,sql.length());
                                    sql.append(")");
                                } else {
                                    sql.append(" and " + columnName + "='" + returnValue + "'");
                                }
                            }
                        }
                    }
                System.out.println("执行的sql为："+sql);
                return BaseDao.selectAll(sql.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        return null;
    }

  /*  public static void main(String[] args) {
        String fieldName = "mobileNum";
        String getMethodName =  "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        System.out.println(getMethodName);
    }*/
}
