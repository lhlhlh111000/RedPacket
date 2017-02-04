import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by win10 on 2017/1/25.
 */
public class Main {

    private static int index = 0;
    private static List<RedEntity> list;

    private static List<UserInfoEntity> users = new ArrayList<>();

    public static void main(String[] args) {
        users = OAuthUtil.getUserList();
        initLoad();
    }

    private static void initLoad() {
        index = 0;
        list = RedListData.load();
        if(0 == list.size()) {
            return;
        }

        startTask(list.get(0));
    }

    private static void startTask(final RedEntity redEntity) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 抢
                for(int i=0; i<users.size(); i++) {
                    RedPacketUtil.postRed(redEntity.getVrid(), users.get(i));
                }

                if(index >= list.size() - 1) {
                    resetInitLoad();
                    return;
                }
                // 继续下个
                index++;
                startTask(list.get(index));
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, parseDate(redEntity.getStartTime()));
        System.out.print(redEntity.getStartTime() + "后开抢~");
        System.out.print("\n");
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(dateStr);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return new Date(System.currentTimeMillis());
    }

    private static void resetInitLoad() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = year + "-" + month + "-" + day + " 00:01:00";
        System.out.print(time);

        Date date = parseDate(time);
        long timeMills = date.getTime();
        timeMills = timeMills + 1*24*60*60*1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                initLoad();
            }
        }, new Date(timeMills));
    }
}
