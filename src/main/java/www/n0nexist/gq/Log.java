package www.n0nexist.gq;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static void log(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String timestamp = sdf.format(new Date());
        System.out.println("["+timestamp + "] " + text);
    }


}
