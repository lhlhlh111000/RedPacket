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
        System.out.print("用户：" + users.size() + "人");
        System.out.print("\n");
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
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("总金额：" + RedPacketUtil.money + "");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");

                // 抢
                for(int i=0; i<users.size(); i++) {
                    final UserInfoEntity user = users.get(i);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RedPacketUtil.postRed(redEntity.getVrid(), user);
                        }
                    }).start();
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
        Date date = parseDate(redEntity.getStartTime());
        if(date.getTime() < System.currentTimeMillis()) {
            if(index >= list.size() - 1) {
                resetInitLoad();
                return;
            }
            // 继续下个
            index++;
            startTask(list.get(index));
        }else {
            Timer timer = new Timer();
            Date dateStart = parseDate(redEntity.getStartTime());
            timer.schedule(task, dateStart);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.print(formatter.format(dateStart) + "后开抢~");
            System.out.print("\n");
        }
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

    private static void resetInitLoad() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = year + "-" + month + "-" + day + " 00:05:00";
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
