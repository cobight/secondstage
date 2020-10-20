package cn.cobight.sb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * fileName:HelloController
 * description:
 * author:cobight
 * createTime:2020/10/12 9:27
 * version:1.0.0
 */
@RestController //ResponseBoy + Controller
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
