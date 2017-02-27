import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 李华 on 2017/2/5.
 */
public class Test {

    public static void main(String[] args) {
        money();
    }

    private static void money() {
        String key = "&key=alihb_123456";
        StringBuilder sb = new StringBuilder();
        sb.append("alipay=").append("15695916483")
                .append("&appid=").append("alihb")
                .append("&device_id=").append("352203064446739")
                .append("&money=").append("10000")
                .append("&package_name=com.martian.alihb")
                .append("&phone=").append("15695916483")
                .append("&realname=").append("二师兄")
                .append("&t=").append(System.currentTimeMillis())
                .append("&token=").append("909a69e7-70a5-4b40-9e11-4e7f5232ef43")
                .append("&uid=").append("922326")
                .append("&version_code=49")
                .append("&version_name=3.2.3")
                .append("&wx_appid=wxa8fb55d05684d979");
        String params = sb.toString();
        String sign = "";
        try {
            sign = RedPacketUtil.md5(params.concat(key));
//            System.out.print(sign);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        String url = "http://api.itaoxiaoshuo.com/rpaccount//auth/withdraw_money_alipay.do".concat("?").concat(params).concat("&sign=").concat(sign);
        String result = RedPacketUtil.sendPost(url, "");
        System.out.print(result);
    }
}
