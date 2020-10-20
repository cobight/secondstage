package cn.cobight.ssm.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * fileName:FileHandlerUtil
 * description:
 * author:cobight
 * createTime:2020/9/28 9:39
 * version:1.0.0
 */
public class FileHandlerUtil {
    /*
    * @description: 文件上传封装方法
    * @author: cobight
    * @date: 2020/9/28
    * @params:
     	* @param file:
 	* @param request:
 	* @param saveFilePath:
    * @return: java.lang.String
    */
    public static String fileUpload(MultipartFile file, HttpServletRequest request,String saveFilePath) {

        //绝对路径
        //request.getRealPath(saveFilePath);
        //servlet  : ServletContext = jsp : Application
        //tomcat 加载项目路径位置
        String realPath = request.getServletContext().getRealPath(saveFilePath);
        System.out.println("real name:" + realPath);
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID()+ suffixName;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            //随机数
//            Random random = new Random();
//            int randomInt = random.nextInt(1000000);//随机一个0-999999
//            String newFileName = sdf.format(new Date())+randomInt+suffixName;
        //实例化一个文件对象 :绝对路径（files/imgs/news/****随机名称***.jpg）
        File newFile = new File(realPath+newFileName);
        //判断文件是否存在，不存在就创建父路径
        if (!newFile.exists()){//如果文件不存在
            newFile.mkdirs();
        }
        //使用Spriingmvc底层提供IO流进行文件读写
        try {
            file.transferTo(newFile);
            return saveFilePath+newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}









