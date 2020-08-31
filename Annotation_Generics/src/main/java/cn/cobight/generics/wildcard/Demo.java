package cn.cobight.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Demo
 * @Description 通配符？用法
 * @Author cobight
 * @CreateTime 2020/8/28 15:48
 * @Version 1.0
 **/
public class Demo {
    /**
     * @Author cobight
     * @Date 2020/8/28
     * @Description /打印集合
     * @Param [olist]
     * @Return void
     **/
    public static void printList(List<?> olist) {
        if (olist != null && olist.size() > 0) {
            StringBuilder s =new StringBuilder("[");
            for (Object o : olist) {
                s.append(o.toString()).append(",");
//                System.out.println(o);
            }
            s.delete(s.length()-1,s.length());
            s.append("]");
            System.out.println(s.toString());
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(1);
        integers.add(3);
        printList(integers);
    }
}
