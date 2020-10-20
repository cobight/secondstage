package com.aaa.ssm.util;

import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * fileName:FileHandlerUtil
 * description: 文件处理工具类
 * author:zz
 * createTime:2020/9/28 9:39
 * version:1.0.0
 */
public class FileHandlerUtil {

    /**
     * 文件上传封装方法
     * @param file
     * @param request
     * @param saveFilePath
     * @return
     */
    public static  String  fileUpload(MultipartFile file, HttpServletRequest request, String saveFilePath){
        try {

            //保存上传文件的路径（相对路径）
            // String saveFilePath="files/imgs/news/";
            //获取相对路径的绝对路径（为了IO读写）
            // String realPath = request.getRealPath(saveFilePath);
            //servlet :ServletContext  =  jsp: application   setAttribute("user","aaa")  作用全局域
            // tomcat 加载项目位置路径
            String realPath = request.getServletContext().getRealPath(saveFilePath);
            //绝对路径：E:\apache-tomcat-8.5.37\webapps\ROOT\files/imgs/news/
            System.out.println("绝对路径为："+realPath);
            //获取上传文件的原始名字   Original =原始的
            // originalFilename = 分布式.png    "files/imgs/news/分布式.jpg";
            String originalFilename = file.getOriginalFilename();
            //获取原始文件名称后缀     a.b.c.jpg
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //拼接新文件名称1   保证名字不能重复
            String newFileName = UUID.randomUUID()+suffixName;
            // 拼接新文件名称2
      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
          //随机数
        Random random = new Random();
        int randomInt = random.nextInt(1000000);//随机一个0-999999
        String newFileName = simpleDateFormat.format(new Date())+randomInt+suffixName;*/
            //实例化文件对象     绝对路径(files/imgs/news/随机名称.jpg)
            File newFile = new File(realPath+newFileName);
            //判断文件是否存在，如果不存在，创建父路径
            if(!newFile.exists()){ //如果文件不存在
                //创建父路径     files/imgs/news/
                newFile.mkdirs();
            }
            //使用springmvc底层提供io流进行文件读写
            file.transferTo(newFile);
            return saveFilePath+newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
