import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 李华 on 2017/2/6.
 */
public class MoneySaveUtil {

    public static void saveMoney(long money) {
        try {
            File file = new File("./", "money.txt");
            try {
                if(file.exists()) {
                    file.delete();
                }

                file.createNewFile(); // 创建文件
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // 向文件写入内容(输出流)
            byte bt[] = new byte[1024];
            bt = (money + "").getBytes();
            try {
                FileOutputStream in = new FileOutputStream(file);
                try {
                    in.write(bt, 0, bt.length);
                    in.close();
                    // boolean success=true;
                    // System.out.println("写入文件成功");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }catch (Exception e) {

        }
    }
}
