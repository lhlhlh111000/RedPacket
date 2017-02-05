import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by win10 on 2017/1/25.
 */
public class RedPacketUtil {

    public static void postRed(String vrid, UserInfoEntity user) {
        String key = "&key=alihb_123456";
        String params = "appid=alihb&device_id=352203064446739&header=http://wx.qlogo.cn/mmopen/AXdMoPDpJuawgn3SuQiaBC5QgdXq6jsHd1TFOqoot8GT0sSjY9YWiaS7wLQ3xian8zycbDe7FmGqJvK9I7sT4icM4sl1jX9a4TAJ/0&nickname=知以饱&package_name=com.martian.alihb&t=1485252009962&token=909a69e7-70a5-4b40-9e11-4e7f5232ef43&uid=922326&version_code=49&version_name=3.2.3&vrid=" + vrid + "&wx_appid=wxa8fb55d05684d979";
        StringBuilder sb = new StringBuilder();
        sb.append("appid=").append("alihb")
                .append("&device_id=").append(user.getDeviceID())
                .append("&header=").append(user.getHeader())
                .append("&nickname=").append(user.getNickName())
                .append("&package_name=com.martian.alihb")
                .append("&t=").append(System.currentTimeMillis())
                .append("&token=").append(user.getToken())
                .append("&uid=").append(user.getUid())
                .append("&version_code=49")
                .append("&version_name=3.2.3")
                .append("&vrid=").append(vrid)
                .append("&wx_appid=wxa8fb55d05684d979");
        params = sb.toString();
        String sign = "";
        try {
            sign = md5(params.concat(key));
            System.out.print(sign);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        String url = "http://api.itaoxiaoshuo.com/rpaccount//auth/grab_virtual_redpaper.do".concat("?").concat(params).concat("&sign=").concat(sign);

//        System.out.print("\n");
//        System.out.print(url);

        String result = sendPost(url, "");

        System.out.print("\n");
        System.out.print(result);
        System.out.print("\n");
    }

    public static String md5(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // a b c d e f 也可以改成大写的 A B C D E F
        char[] feedArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        MessageDigest msgDigest = MessageDigest.getInstance("MD5");
        msgDigest.update(plainText.getBytes("UTF-8"));
        byte[] bytes = msgDigest.digest();
        char[] out = new char[16 * 2];
        for (int i = 0, j = 0; i < 16; i++) {
            out[j++] = feedArray[bytes[i] >>> 4 & 0xf];
            out[j++] = feedArray[bytes[i] & 0xf];
        }
        String md5Str = new String(out);
        return md5Str;
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
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
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
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
        return result;
    }
}
