package cn.cobight.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PersonMan
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/28 16:23
 * @Version 1.0
 **/
public class PersonMan {
    /**
     * @Author cobight
     * @Date 2020/8/28
     * @Description 喂养猫
     * @Param [catList]
     * @Return void
     **/
    public void feed(List<? extends Cat> catList){
        if (catList!=null && catList.size()>0){
            for (Cat cat : catList) {
                cat.eat();
            }
        }
    }

    public static void main(String[] args) {
        PersonMan personMan = new PersonMan();
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        personMan.feed(cats);

        ArrayList<SmallCat> smallCats = new ArrayList<>();
        smallCats.add(new SmallCat());
        personMan.feed(smallCats);
    }
}
