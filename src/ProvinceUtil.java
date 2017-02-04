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
public class ProvinceUtil {

    private static List<ProvinceInfoEntity> list = new ArrayList<>();

    private static void init() {
        String json = readString();
        if(json.isEmpty()) {
            return;
        }

        JSONArray array = JSONArray.parseArray(json);
        for(int i=0; i<array.size(); i++) {
            JSONObject provinceObject = array.getJSONObject(i);
            ProvinceInfoEntity entity = new ProvinceInfoEntity();
            entity.setName(provinceObject.getString("Name"));
            JSONArray cityArray = provinceObject.getJSONArray("Citys");
            if(null != cityArray) {
                ArrayList<String> cityList = new ArrayList<>();
                for(int j=0; j<cityArray.size(); j++) {
                    JSONObject cityObject = cityArray.getJSONObject(j);
                    cityList.add(cityObject.getString("Name"));
                }
                entity.setCitys(cityList);
            }
            list.add(entity);
        }
    }

    public static ProvinceInfoEntity getCityInfo() {
        if (null == list || 0 == list.size()) {
            init();
        }

        if (null != list && 0 != list.size()) {
            return list.get(new Random().nextInt(list.size() - 1));
        }
        return null;
    }

    public static void main(String[] args) {
        ProvinceInfoEntity entity = getCityInfo();
        System.out.print(entity.getName());
    }

    private static String readString() {
        String str = "";
        File file = new File("./allprovinces.json");
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
}