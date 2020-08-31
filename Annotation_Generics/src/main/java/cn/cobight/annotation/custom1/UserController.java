package cn.cobight.annotation.custom1;

/**
 * fileName:UserController
 * description:
 * author:zz
 * createTime:2020/8/27 9:47
 * version:1.0.0
 */
@Service
@Controller(value = "userController")
public class UserController {

    /**
     * 根据ID查询对象
     * @param userId
     * @return
     */
    public Object  getById(Integer userId){
        return  null;
    }
}
