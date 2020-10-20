package cn.cobight.ioc.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * fileName:Printer
 * description:
 * author:cobight
 * createTime:2020/9/23 8:32
 * version:1.0.0
 */
@Controller("printer")  //相当于前两个项目中中的bean
@Scope("prototype")
public class Printer {
    @Value("打印机1号")
    private String name;
    //@Autowired
    //@Qualifier("a4Paper")
    @Resource(name = "a4Paper")
    private Paper paper;
    //private Paper a4Paper;
    @Autowired
    private Lnk colorLnk;
    public void print(){
        System.out.println(name+"开始工作");
        paper.setPaper();
        colorLnk.setColor();
    }
}
