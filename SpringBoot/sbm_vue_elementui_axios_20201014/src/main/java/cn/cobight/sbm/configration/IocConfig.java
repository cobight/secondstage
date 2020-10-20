package cn.cobight.sbm.configration;

import cn.cobight.sbm.icodemo.ChiTu;
import cn.cobight.sbm.icodemo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fileName:IocConfig
 * description:
 * author:zz
 * createTime:2020/10/13 9:55
 * version:1.0.0
 */
@Configuration  //相当于applictionContext.xml
public class IocConfig {


    /**
     * 把chiTu交给spring管理
     * @return
     */
    @Bean  //<bean id="horse" class="com.aaa.sbm.iocdemo.ChiTu">    @Bean("id")
    public ChiTu horse(){
        return new ChiTu();
    }

    /**
     * 把person交给spring管理
     * @return
     */
    @Bean  //<bean id="persion" class="com.aaa.sbm.iocdemo.Person">
           // <property name="name" value="关羽">
           // <property name="horse" ref="horse">
           // </bean>
    public Person person(){
        Person person = new Person();
        person.setName("关羽");
        person.setHorse(horse());
        return person;
    }
}
