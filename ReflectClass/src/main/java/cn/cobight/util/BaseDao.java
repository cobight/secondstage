package cn.cobight.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class BaseDao {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/qy119?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    public static final String USER = "root";
    public static final String PWD = "root";
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    static {
        //只会加载一次，在BaseDao类代码加载的时候加载一次
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //封装获取连接
    public static Connection getCon() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static int executeUpd(String sql, Object... params) {
        int result = -1;//保存受影响的行数
        Connection con = getCon();
        PreparedStatement psmt = null;//准备报表
        try {
            psmt = con.prepareStatement(sql);
            //给SQL语句的问号赋值
            setPms(psmt, params);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con, psmt, null);
        }
        return result;
    }

    public static void setPms(PreparedStatement psmt, Object... params) {
        if (null == psmt) {
            return; //直接终止该方法的运行
        }
        if (null != params && params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                //前面是问号的顺序
                //后面是数组的下标
                try {
                    psmt.setObject(i + 1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (null != rs) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (null != stmt) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (null != con) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //插入返回主键的值
    public static int executeInsert(String sql, Object... params) {
        int id = -1;//用来接收返回的主键
        Connection con = getCon();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //给SQL语句的问号赋值
            setPms(psmt, params);
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con, psmt, rs);
        }
        return id;
    }

    //封装查询
    public static List<Map<String, Object>> selectAll(String sql, Object... obs) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        con = getCon();
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
        try {
            psmt = con.prepareStatement(sql);
            setPms(psmt, obs);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int num = rsmt.getColumnCount();
            while (rs.next()) {//判断是否有一行，如果有的话则读取到这一行
                //循环读取每行
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= num; i++) {
                    map.put(rsmt.getColumnName(i), rs.getObject(i));
                }
                ls.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con, psmt, rs);
        }
        return ls;
    }

    //泛型反射封装
    //通用的返回实体集合
    public static <T> List<T> selectEntityByParam(String sql,Class<T> classT,Object... params){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            con = getCon();//连接数据库
            preparedStatement = con.prepareStatement(sql);//打包执行对象
            setPms(preparedStatement,params);//设置参数
            resultSet = preparedStatement.executeQuery();//执行查询，返回结果

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //定义一个返回的结果集合
            List<T> tlist= new ArrayList<T>();
            T t = null;
            //select  `id`, `username`, `realname`, `age`, `mobilenum`, `email` from tb_teacher
            while (resultSet.next()){
                t=classT.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Field declaredField = classT.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(t,resultSet.getObject(i));
                }
                tlist.add(t);
            }
            return tlist;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,preparedStatement,resultSet);
        }
        return null;
    }
    public static <T> List<T> selectEntityBy_Param(String sql,Class<T> classT,Object... params){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            con = getCon();//连接数据库
            preparedStatement = con.prepareStatement(sql);//打包执行对象
            setPms(preparedStatement,params);//设置参数
            resultSet = preparedStatement.executeQuery();//执行查询，返回结果

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            //定义一个返回的结果集合
            List<T> tlist= new ArrayList<T>();
            T t = null;
            //select  `id`, `username`, `realname`, `age`, `mobilenum`, `email` from tb_teacher
            while (resultSet.next()){
                t=classT.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    if (columnName.contains("_")){
                        int index = columnName.indexOf("_");
                        String prefixStr = columnName.substring(0,index );
                        String upperStr = columnName.substring(index+1, index+ 2).toUpperCase();
                        String suffStr = columnName.substring(index + 2);
                        columnName=prefixStr+upperStr+suffStr;
                    }
                    Field declaredField = classT.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(t,resultSet.getObject(i));
                }
                tlist.add(t);
            }
            return tlist;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,preparedStatement,resultSet);
        }
        return null;
    }
}