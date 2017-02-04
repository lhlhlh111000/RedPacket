import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by win10 on 2017/2/4.
 */
public class OAuthUtil {

    public static void main(String[] args) {
        List<UserInfoEntity> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            UserInfoEntity user = initUser();
            if(null != user) {
                list.add(user);
            }
        }

        String json = JSONArray.toJSONString(list);
        System.out.print(json);
    }

    public static List<UserInfoEntity> getUserList() {
        String json = readString();
        if(json.isEmpty()) {
            return null;
        }

        JSONArray array = JSONArray.parseArray(json);
        List<UserInfoEntity> list = new ArrayList<>();
        for(int i=0; i<array.size(); i++) {
            JSONObject userObject = array.getJSONObject(i);

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setCity(userObject.getString("city"));
            userInfoEntity.setDeviceID(userObject.getString("deviceID"));
            userInfoEntity.setGender(userObject.getString("gender"));
            userInfoEntity.setHeader(userObject.getString("header"));
            userInfoEntity.setNickName(userObject.getString("nickName"));
            userInfoEntity.setOsType(userObject.getString("osType"));
            userInfoEntity.setProvince(userObject.getString("province"));
            userInfoEntity.setToken(userObject.getString("token"));
            userInfoEntity.setUid(userObject.getString("uid"));
            list.add(userInfoEntity);
        }
        return list;
    }

    private static String readString() {
        String str = "";
        File file = new File("./user.json");
        try {
            FileInputStream in = new FileInputStream(file);
            // size  为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer, "UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    public static UserInfoEntity initUser() {
        String appid = "alihb";
        ProvinceInfoEntity cityEntity = ProvinceUtil.getCityInfo();
        String province = "";
        String city = "";
        if(null != cityEntity) {
            province = cityEntity.getName();
            if(null != cityEntity.getCitys() && 0 != cityEntity.getCitys().size()) {
                city = cityEntity.getCitys().get(new Random().nextInt(cityEntity.getCitys().size()));
            }else {
                city = province;
            }
        }
        String gender = "M";
        String header = "http://q.qlogo.cn/qqapp/" + getRandomNum() + "/C6E1783968BF13EE7A8432A50FE941EA/100";
        String osType = "0";
        String nickName = getNickName();
        String deviceId = getDeviceID();
        String packageName = "com.martian.alihb";
        String qqToken = getQQToken();
        String qqAppID = "1104935104";
        String qqOpenID = "C6E1783968BF13EE7A8432A50FE941EA";
        String qqPlatform = "desktop_m_qq-10000144-android-2002-";
        String t = System.currentTimeMillis() + "";
        String versionCode = "49";
        String versionName = "3.2.3";
        String wxAppID = "wxa8fb55d05684d979";

        String url = "http://api.itaoxiaoshuo.com/rpaccount/qq_user_register.do";
//        String parmas = "appid=alihb&city=%E9%BE%99%E5%B2%A9&device_id=352203064446739&gender=M&header=http%3A%2F%2Fq.qlogo.cn%2Fqqapp%2F1104935104%2FC6E1783968BF13EE7A8432A50FE941EA%2F100&nickname=1113292419&ostype=0&package_name=com.martian.alihb&province=%E7%A6%8F%E5%BB%BA&qq_access_token=F5E92F6108E61D2BBB53EA6AAD783F6C&qq_appid=1104935104&qq_openid=C6E1783968BF13EE7A8432A50FE941EA&qq_pf=desktop_m_qq-10000144-android-2002-&t=1486177085273&version_code=49&version_name=3.2.3&wx_appid=wxa8fb55d05684d979";
        StringBuilder sb = new StringBuilder();
        sb.append("appid=").append(appid)
                .append("&city=").append(city)
                .append("&device_id=").append(deviceId)
                .append("&gender=").append(gender)
                .append("&header=").append(header)
                .append("&nickname=").append(nickName)
                .append("&ostype=").append(osType)
                .append("&package_name=").append(packageName)
                .append("&province=").append(province)
                .append("&qq_access_token=").append(qqToken)
                .append("&qq_appid=").append(qqAppID)
                .append("&qq_openid=").append(qqOpenID)
                .append("&qq_pf=").append(qqPlatform)
                .append("&t=").append(t)
                .append("&version_code=").append(versionCode)
                .append("&version_name=").append(versionName)
                .append("&wx_appid=").append(wxAppID);
        String parmas = sb.toString();

        String key = "&key=alihb_123456";
        String sign = "";
        try {
            sign = RedPacketUtil.md5(parmas.concat(key));
            System.out.print(sign);
        }catch (Exception  e) {
            e.printStackTrace();
        }

        url = url.concat("?").concat(parmas).concat("&sign=").concat(sign);
        String result = RedPacketUtil.sendPost(url, "");
        System.out.print(result);
        JSONObject resultObject = JSONObject.parseObject(result);
        JSONObject userObject = resultObject.getJSONObject("data");
        if(null != userObject) {
            String uid = userObject.getString("uid");
            String token = userObject.getString("token");

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setCity(city);
            userInfoEntity.setDeviceID(deviceId);
            userInfoEntity.setGender(gender);
            userInfoEntity.setHeader(header);
            userInfoEntity.setNickName(nickName);
            userInfoEntity.setOsType(osType);
            userInfoEntity.setProvince(province);
            userInfoEntity.setToken(token);
            userInfoEntity.setUid(uid);

            return userInfoEntity;
        }

        return null;
    }

    /**
     * 352203064446739
     * @return
     */
    private static String getDeviceID () {
        String s = "0123456789";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<15; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    private static String getNickName() {
        String s = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    /**
     * F5E92F6108E61D2BBB53EA6AAD783F6C
     * @return
     */
    private static String getQQToken() {
        String s = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<32; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    /**
     * 1104935104
     * @return
     */
    private static String getRandomNum () {
        String s = "0123456789";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }
}
