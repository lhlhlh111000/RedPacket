import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 李华 on 2017/2/5.
 */
public class Test {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String time = year + "-" + month + "-" + day + " 00:01:00";
        System.out.print(time);

        Date date = parseDate(time);
        long timeMills = date.getTime();
        timeMills = timeMills + 1*24*60*60*1000;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(formatter.format(new Date(timeMills)));
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
