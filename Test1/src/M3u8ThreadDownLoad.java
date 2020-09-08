import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName M3u8ThreadDownLoad
 * @Description 线程下载M3U8格式文件切片，并合并为视频
 * @Author cobight
 * @CreateTime 2020/8/25 16:41
 * @Version 1.0
 **/
public class M3u8ThreadDownLoad {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //自己换
        String url = "https://play.093ch.com/20200126/90/901/901.mp4.m3u8";
        long time = new Date().getTime();
        //System.out.println(new File("").getAbsolutePath());
        File f = new File("download\\" + time);
        if (!f.exists()) {
            System.out.println(f.mkdirs() ? "成功生成目录！" : "文件夹创建失败！");
        }
        Tool_m3u8 t = new Tool_m3u8();//m3u8视频格式的下载工具类
        /* 参数一m3u8网址
         * 参数二保存路径
         * 参数三线程数量
         */
        t.load_m3u8(url, "" + time, 12);
        long stop = System.currentTimeMillis();
        long allsecond=(stop - start)/1000, min=allsecond/60,second=allsecond-60*min;

        System.out.println("下载消耗时长：" + min+"分钟，"+second+"秒");
    }
    public static void down(String url, Integer ThreadNum ) throws Exception {
        long time = new Date().getTime();
        File f = new File("download\\" + time);
        if (!f.exists()) {
            System.out.println(f.mkdirs() ? "成功生成目录！" : "文件夹创建失败！");
        }
        Tool_m3u8 t = new Tool_m3u8();//m3u8视频格式的下载工具类
        t.load_m3u8(url, "" + time, ThreadNum);
    }

    static class Tool_m3u8 {
        private int length = 0;

        /**
         * @Author cobight
         * @Date 2020/8/25
         * @Description 读m3u8网址数据，解析切片路径，读取网络切片到文件
         * @Param [url, file, ThreadNum]
         * @Return void
         **/
        public void load_m3u8(String url, String file, Integer ThreadNum) throws Exception {
            String m = HttpRequest.sendGet(url, "");
//        System.out.println(m);//获取m3u8切片数据
            String uri = url.substring(0, url.lastIndexOf("/") + 1);
            ArrayList<String> list = getM3u8_ItemList(m, uri);
            this.length = list.size();//记录长度，合并用
            ExecutorService executorService = Executors.newFixedThreadPool(ThreadNum);//返回一个执行器的服务类
            for (int i = 0; i < list.size(); i++) {
                DownloadThread dt = new DownloadThread(list.get(i), "download/" + file + "/file.mp4" + i + ".ts");
                executorService.execute(dt);
            }
            executorService.shutdown();//执行完关闭
            while (true) {
                if (executorService.isTerminated()) {
                    TimeUnit.SECONDS.sleep(1);
                    break;
                }
            }
            merge_ts("download/" + file);//切片视频下载完，开始合并视频

//        System.out.println(m);
            //cmd控制台的合并文件语句
//        copy /b file.mp4*.ts  test.mp4
        }

        /**
         * @Author cobight
         * @Date 2020/8/25
         * @Description 下载的m3u8文件解析出所有的切片路径，打包到list返回
         * @Param [info, uri]
         * @Return java.util.ArrayList<java.lang.String>
         **/
        public ArrayList<String> getM3u8_ItemList(String info, String uri) {
            String[] arr = info.split("\n");//分割m3u8里的字符串数据
            ArrayList<String> list = new ArrayList<>();
            for (String s : arr) {
                if (s.contains(".ts")) {//找ts结尾的
                    list.add(uri + s);
                }
            }
            return list;
        }

        /**
         * @Author cobight
         * @Date 2020/8/25
         * @Description 合并文件夹下所有ts切片，合一个，清一个
         * @Param [PATH]
         * @Return void
         **/
        public void merge_ts(String PATH) {
            String outputPATH = PATH + "\\file.mp4";//合并文件位置
            try {
                OutputStream os = new FileOutputStream(outputPATH);
                for (int i = 0; i < this.length; i++) {
                    File f = new File(PATH + "\\file.mp4" + i + ".ts");
                    InputStream is = new FileInputStream(f);//我记得想用list.get(i)来着
                    byte[] temp = new byte[1024];
                    int len;
                    while ((len = is.read(temp)) != -1) {
                        os.write(temp, 0, len);
                        os.flush();
                    }
                    is.close();
                    f.delete();
                }
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class HttpRequest {
        /**
         * 向指定URL发送GET方法的请求，返回字符串
         *
         * @param url   发送请求的URL
         * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return URL 所代表远程资源的响应结果
         */
        public static String sendGet(String url, String param) {
            StringBuilder result = new StringBuilder();
            BufferedReader in = null;
            try {
                String urlNameString = url + "?" + param;
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3756.400 QQBrowser/10.5.4039.400");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line).append("\n");
                }
                return result.toString();
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + url);
//            e.printStackTrace();
                return sendGet(url, param);
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        /**
         * 向指定 URL 发送POST方法的请求
         *
         * @param url   发送请求的 URL
         * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return 所代表远程资源的响应结果
         */
        public static String sendPost(String url, String param) {
            PrintWriter out = null;
            BufferedReader in = null;
            StringBuilder result = new StringBuilder();
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            } catch (Exception e) {
                System.out.println("发送 POST 请求出现异常！" + e);
                e.printStackTrace();
            }
            //使用finally块来关闭输出流、输入流
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result.toString();
        }

        /**
         * 文件下载与保存
         */
        public static void download_file(String url, String filepath) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性头
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
                // 定义 BufferedReader输入流来读取URL的响应
                is = new BufferedInputStream(connection.getInputStream());
                fos = new FileOutputStream(filepath);

                byte[] temp = new byte[1024];
                int len;
                while ((len = is.read(temp)) != -1) {
                    fos.write(temp, 0, len);
                }

            } catch (Exception e) {
                System.out.println("ts切片下载出现异常！开始重新下载：" + url);
//            e.printStackTrace();异常重载
                download_file(url, filepath);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 使用finally块来关闭输入流


        }
    }

    static class DownloadThread implements Runnable {
        private String url;
        private String filepath;

        public DownloadThread(String url, String filepath) {
            this.url = url;//切片地址
            this.filepath = filepath;//保存路径
        }

        @Override
        public void run() {
            System.out.println(this.url);
            HttpRequest.download_file(this.url, this.filepath);
        }
    }
}
