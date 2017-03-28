import com.alibaba.fastjson.JSON;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win10 on 2017/2/16.
 */
public class JsoupUtil {

    private static List<String> users = new ArrayList<>();


    public static void main(String[] args) {
//        for(int i=0; i<800; i++) {
//            try {
//                Document document = Jsoup.connect(String.format("http://www.wangmingdaquan.cc/nansheng/list_20_%d.html", (i+ 1))).get();
//                Elements elements = document.getElementsByTag("p");
//                for(Element element : elements) {
//                    String value = element.toString();
//                    if(!value.isEmpty() && !value.contains("img")) {
//                        value = value.replace("<p>", "").replace("</p>", "");
//                        value = value.replace("&#xfffd;", "");
//                        users.add(value);
//                    }
//                }
//                System.out.print("Page: " + (i + 1) + "\n");
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        String userStr = JSON.toJSONString(users);
//        try {
//            File file = new File("./temp.json");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(userStr);
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}