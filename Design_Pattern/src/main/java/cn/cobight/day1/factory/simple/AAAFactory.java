package cn.cobight.day1.factory.simple;

/**
 * fileName:AAAFactory
 * description:
 * author:zz
 * createTime:2020/9/3 10:29
 * version:1.0.0
 */
public class AAAFactory {

    /**
     * 静态的教授软件技术方法
     * @param stType
     * @return
     */
    public static SoftwareTechnology  teachST(int stType){
        if(stType==1){
            return new JavaDevolopTechnology();
        }else if(stType==2){
            return new PHPDevolopTechnology();
        }else  if(stType==3){
            return new PythonDevolopTechnology();
        }else{
            return null;
        }
    }
}
