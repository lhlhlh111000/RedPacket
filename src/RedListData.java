import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by win10 on 2017/2/3.
 */
public class RedListData {

    public static List<RedEntity> load() {
        List<RedEntity> list = new ArrayList<>();
        // common red
        String result = RedPacketUtil.sendGet("http://api.itaoxiaoshuo.com/rpaccount/get_virtual_redpapers.do?appid=alihb&device_id=352203064446739&ostype=0&package_name=com.martian.alihb&qq_appid=1104935104&t=1486087998910&version_code=49&version_name=3.2.3&vrtype=0&wx_appid=wxa8fb55d05684d979&sign=F88114E602521A7FBA206C2AE94446A2");
        if(result.isEmpty()) {
            return list;
        }

        JSONObject object = JSONObject.parseObject(result);
        JSONObject objectData = null;
        if(null != object) {
            objectData = object.getJSONObject("data");
        }
        JSONArray array = null;
        if(null != objectData) {
            array = objectData.getJSONArray("redpapers");
        }

        if(null != array) {
            for(int i=0; i<array.size(); i++) {
                JSONObject item = array.getJSONObject(i);
                String vird = item.getString("vrid");
                String time = item.getString("startTime");
                list.add(new RedEntity(vird, time));
            }
        }

        // vip red
        String result2 = RedPacketUtil.sendGet("http://api.itaoxiaoshuo.com/rpaccount/get_virtual_redpapers.do?appid=alihb&device_id=352203064446739&ostype=0&package_name=com.martian.alihb&qq_appid=1104935104&t=1486303432650&version_code=49&version_name=3.2.3&vrtype=1&wx_appid=wxa8fb55d05684d979&sign=42BA7BD94806E6644D6E708ADDCDD0BC");
        if(result2.isEmpty()) {
            return list;
        }

        JSONObject object2 = JSONObject.parseObject(result2);
        JSONObject objectData2 = null;
        if(null != object2) {
            objectData2 = object2.getJSONObject("data");
        }
        JSONArray array2 = null;
        if(null != objectData2) {
            array2 = objectData2.getJSONArray("redpapers");
        }

        if(null != array2) {
            for(int i=0; i<array2.size(); i++) {
                JSONObject item = array2.getJSONObject(i);
                String vird = item.getString("vrid");
                String time = item.getString("startTime");
                list.add(new RedEntity(vird, time));
            }
        }


        // pro red
        String result3 = RedPacketUtil.sendGet("http://api.itaoxiaoshuo.com/rpaccount/get_virtual_redpapers.do?appid=alihb&device_id=352203064446739&ostype=0&package_name=com.martian.alihb&qq_appid=1104935104&t=1486568148546&version_code=49&version_name=3.2.3&vrtype=2&wx_appid=wxa8fb55d05684d979&sign=07C4743B6D8D9694179FE818A635B2A3");
        if(result3.isEmpty()) {
            return list;
        }

        JSONObject object3 = JSONObject.parseObject(result3);
        JSONObject objectData3 = null;
        if(null != object3) {
            objectData3 = object3.getJSONObject("data");
        }
        JSONArray array3 = null;
        if(null != objectData3) {
            array3 = objectData3.getJSONArray("redpapers");
        }

        if(null != array3) {
            for(int i=0; i<array3.size(); i++) {
                JSONObject item = array3.getJSONObject(i);
                String vird = item.getString("vrid");
                String time = item.getString("startTime");
                list.add(new RedEntity(vird, time));
            }
        }


        // guess red
        String result4 = RedPacketUtil.sendGet("http://api.itaoxiaoshuo.com/rpaccount/get_virtual_redpapers.do?appid=alihb&device_id=352203064446739&ostype=0&package_name=com.martian.alihb&qq_appid=1104935104&t=1486568555363&version_code=49&version_name=3.2.3&vrtype=5&wx_appid=wxa8fb55d05684d979&sign=20CDC847160ED510D239CABD663608E3");
        if(result4.isEmpty()) {
            return list;
        }

        JSONObject object4 = JSONObject.parseObject(result4);
        JSONObject objectData4 = null;
        if(null != object4) {
            objectData4 = object4.getJSONObject("data");
        }
        JSONArray array4 = null;
        if(null != objectData4) {
            array4 = objectData4.getJSONArray("redpapers");
        }

        if(null != array4) {
            for(int i=0; i<array4.size(); i++) {
                JSONObject item = array4.getJSONObject(i);
                String vird = item.getString("vrid");
                String time = item.getString("startTime");
                list.add(new RedEntity(vird, time));
            }
        }


        // guess red
        String result5 = RedPacketUtil.sendGet("http://api.itaoxiaoshuo.com/rpaccount/get_virtual_redpapers.do?appid=alihb&device_id=352203064446739&ostype=0&package_name=com.martian.alihb&qq_appid=1104935104&t=1486568641189&version_code=49&version_name=3.2.3&vrtype=3&wx_appid=wxa8fb55d05684d979&sign=9E1846701AE3FC74CBC1F39BE6574183");
        if(result5.isEmpty()) {
            return list;
        }

        JSONObject object5 = JSONObject.parseObject(result5);
        JSONObject objectData5 = null;
        if(null != object5) {
            objectData5 = object5.getJSONObject("data");
        }
        JSONArray array5 = null;
        if(null != objectData5) {
            array5 = objectData5.getJSONArray("redpapers");
        }

        if(null != array5) {
            for(int i=0; i<array5.size(); i++) {
                JSONObject item = array5.getJSONObject(i);
                String vird = item.getString("vrid");
                String time = item.getString("startTime");
                list.add(new RedEntity(vird, time));
            }
        }

        // sort
        list.sort(new Comparator<RedEntity>() {
            @Override
            public int compare(RedEntity o1, RedEntity o2) {
                return parseDate(o1.getStartTime()).getTime() > parseDate(o2.getStartTime()).getTime() ? 1 : -1;
            }
        });
        for(RedEntity entity : list) {
            String time = entity.getStartTime();
            System.out.print(time);
            System.out.print("\n");
        }
        System.out.print("共：" + list.size() + "个红包~");
        System.out.print("\n");
        return list;
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(dateStr);
            return new Date(date.getTime() + 100);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return new Date(System.currentTimeMillis());
    }
}
