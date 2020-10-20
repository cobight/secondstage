package cn.cobight.sb.controller;

import cn.cobight.sb.entity.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * fileName:GreetingController
 * description:
 * author:cobight
 * createTime:2020/10/12 11:12
 * version:1.0.0
 */
@RestController
public class GreetingController {

    //原子自增类，线程安全的 对long自增
    private final AtomicLong atomicLong = new AtomicLong();

    @GetMapping("greeting")
//    @RequestMapping(value = "greeting",method = RequestMethod.GET)
/*    @PutMapping更新
    @PostMapping添加
    DeleteMapping删除*/
//      从前端请求获取键为nameA的数据 赋值给变量name，
//            required 默认为true，允许为空，设置false代表必须传递
//                        设置false后，defaultValue就没用了
//                    然而并没有什么卵用，设置false跟true效果一样
//    @RequestParam(value = "nameA",required = true,defaultValue = "World") String name
    public Greeting greeting(@RequestParam(value = "nameA",required = true,defaultValue = "World") String name){
        return new Greeting(atomicLong.incrementAndGet(),"hello"+name);
    }
}
