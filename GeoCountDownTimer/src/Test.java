import java.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Phil on 8/31/16.
 */
public class Test {
    public static void main(String... args){
        GeoCountDownTimer g1 = new GeoCountDownTimer(1,20,2018);
        GeoCountDownTimer g2 = new GeoCountDownTimer(12,30,2018);
        GeoCountDownTimer g3 = new GeoCountDownTimer(12,15,2018);
        System.out.println(g2.compareTo(g1));
        System.out.println(g2.compareTo(g1));
        System.out.println(g3.compareTo(g2));
        GeoCountDownTimer s4 = new GeoCountDownTimer (5,10,2015);
        s4.inc(365);
        System.out.println(s4);
        System.out.println(s4.toDateString().equals("5/9/2016"));
        GeoCountDownTimer s = new GeoCountDownTimer("2/10/2018");
        System.out.println("Date: " + s);

        GeoCountDownTimer s1 = new GeoCountDownTimer(2, 20, 2018);
        System.out.println("Date: " + s1.toDateString());

        s1.inc(365);
        System.out.println("Date: " + s1);

        GeoCountDownTimer s2 = new GeoCountDownTimer(4, 10, 2018);
        for (int i = 0; i < (366 + 365+ 365 + 365); i++)
            s2.inc();
        System.out.println("Date: " + s2);
        GeoCountDownTimer j1 = new GeoCountDownTimer (5,10,2015);
        j1.inc(365);
        System.out.println (j1);
        System.out.println (j1.toDateString().equals("5/9/2016"));
        j1.dec(365);
        System.out.println(j1.toDateString().equals("5/10/2015"));

    }
}