import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win10 on 2017/2/3.
 */
public class RedListData {

    public static List<RedEntity> load() {
        List<RedEntity> list = new ArrayList<>();
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
        return list;
    }
}
