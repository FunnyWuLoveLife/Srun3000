package com.srun3000.Controller;

import com.srun3000.Model.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by xbc922 on 2016/8/13.
 */
public class Connecter {

    private static boolean isLinked = false;
    private User user = null;
    private static String acid = "1";
    private static String mac_address = "";
    private static String port = "69";

    // 回显信息
    public static String account_name = "";
    public static String used_data = "";
    public static String ip_address = "";
    public static long connectTime = 0;

    /**
     * @param user
     * @return
     * @throws Exception
     */
    public static String Login(User user) {
        user.userEncode();//将用户名和密码进行编码
        String urlencode_username = null;
        String urlencode_pwd = null;
        String urlencode_acid = null;
        String urlencode_mac = null;
        String response = null;//返回字符串
        try {
            urlencode_username = URLEncoder.encode(user.getUsername(), "utf-8");//制将字符串转换为 application/x-www-form-urlencoded 格式
            urlencode_pwd = URLEncoder.encode(user.getPassword(), "utf-8");//同上
            urlencode_acid = URLEncoder.encode(acid, "utf-8");//同上
            urlencode_mac = URLEncoder.encode(mac_address, "utf-8");//同上
        } catch (UnsupportedEncodingException e) {
            response = "登录发生了一点小小的错误-_-" + e.toString();
        }
        //组建URL数据链
        String data =
                "action=login&username="
                        + urlencode_username
                        + "&password="
                        + urlencode_pwd
                        + "&drop=0&pop=1&type=2&n=117&mbytes=0&minutes=0&ac_id="
                        + urlencode_acid
                        + "&mac="
                        + urlencode_mac;


        try {
            response = HttpPost("http://172.16.154.130:" + port + "/cgi-bin/srun_portal", data);//执行post方法
        } catch (Exception e) {
            response = "登录发生了一点小小的错误-_-\n错误信息如下：" + e.toString();
        }

        if (response.contains("login_ok")) {    //验证post方法的返回值
            isLinked = true;
            return "success";
        } else {
            isLinked = false;
            return response;
        }
    }

    public static String HttpPost(String url, String data) throws Exception {
        URL urlObj = new URL(null, url);//构造url对象
        /* 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
        每次调用此 URL 的协议处理程序的 openConnection 方法都打开一个新的连接。*/
        URLConnection urlConnection = urlObj.openConnection();//调用URL对象的openConnection 方法打开一个新的连接
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 添加由键值对指定的一般请求属性。
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;    //将URLConnection对象转换为HttpURLConnection对象
        //设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);//将DoOutput设置为true,这样就可以使用HttpURLConnectiono类的getOutputStream().write()  方法
        byte[] contentBytes = data.getBytes("UTF-8");//得到http类容的字节数组
        httpURLConnection.setFixedLengthStreamingMode(contentBytes.length);//此方法用于在预先已知内容长度时启用没有进行内部缓冲的 HTTP 请求正文的流。
        OutputStream out = httpURLConnection.getOutputStream();//取得HttpURLConnection对象的OutputStream
        try {
            out.write(contentBytes);//向输出流写入类容数据
        } finally {
            out.flush();//刷新此输出流并强制写出所有缓冲的输出字节。
        }
        InputStream inputStream = urlConnection.getInputStream();//返回从此打开的连接读取的输入流
        int contentLength = urlConnection.getContentLength();
        contentLength = contentLength == -1 ? 4096 : contentLength;
        byte[] buffer = new byte[contentLength];
        int offset = 0;
        while (true) {
            int remain = buffer.length - offset;
            if (remain <= 0) {
                int newSize = buffer.length * 2;
                byte[] newBuffer = new byte[newSize];
                System.arraycopy(buffer, 0, newBuffer, 0, offset);
                buffer = newBuffer;
                remain = buffer.length - offset;
            }
            int numRead = inputStream.read(buffer, offset, remain);
            if (numRead == -1) {
                break;
            }
            offset += numRead;
        }
        if (offset < buffer.length) {
            byte[] newBuffer = new byte[offset];
            System.arraycopy(buffer, 0, newBuffer, 0, offset);
            buffer = newBuffer;
        }
        return new String(buffer, "UTF-8");
    }

    public static String logout(User user) {
        user.userEncode();
        String urlencode_usr = null;
        String urlencode_acid = null;
        String urlencode_mac = null;
        String response = null;
        try {
            urlencode_usr = URLEncoder.encode(user.getUsername(), "utf-8");
            urlencode_acid = URLEncoder.encode(acid, "utf-8");
            urlencode_mac = URLEncoder.encode(mac_address, "utf-8");
            response = HttpPost("http://172.16.154.130:" + port + "/cgi-bin/srun_portal", "action=logout&ac_id=" + urlencode_acid + "&username=" + urlencode_usr + "&mac=" + urlencode_mac + "&type=2");
        } catch (UnsupportedEncodingException e) {
            response = "注销发生了一点小小的错误-_-" + e.toString();
        } catch (Exception e) {
            response = "注销发生了一点小小的错误-_-" + e.toString();
        }
        isLinked = false;
        return response;
    }

}
