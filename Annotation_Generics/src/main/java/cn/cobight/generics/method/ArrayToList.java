package cn.cobight.generics.method;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArrayToList
 * @Description ? extends Cat
 *              ? supper Cat
 * @Author cobight
 * @CreateTime 2020/8/28 15:35
 * @Version 1.0
 **/
public class ArrayToList {
    public static  <T> List<T> aToL(T[] tArray){
        List<T> tList = new ArrayList<>();
        for (T t : tArray) {
            tList.add(t);
        }
        return tList;
    }

    public static void main(String[] args) {
        List<String> strings = aToL(new String[]{"a", "b", "c"});
        System.out.println(strings);
        List<Integer> integers = aToL(new Integer[]{1,2,3});
        System.out.println(integers);
    }

}
