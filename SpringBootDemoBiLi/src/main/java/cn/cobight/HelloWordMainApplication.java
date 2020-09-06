package cn.cobight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * fileName:HelloWordMainApplication
 * description:
 * author:cobight
 * createTime:2020/9/1 11:06
 * version:1.0.0
 */
//标注主程序类，说明这是一个springboot应用
@SpringBootApplication
public class HelloWordMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWordMainApplication.class,args);
    }
}
