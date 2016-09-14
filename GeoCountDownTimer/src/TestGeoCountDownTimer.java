import org.junit.Test;
import static org.junit.Assert.*;

public class TestGeoCountDownTimer {


    //Test Integer Constructor
    @Test
    public void PAGtestConstructor1() {
        GeoCountDownTimer s = new GeoCountDownTimer (2,28,2017);
        assertEquals(s.toDateString(),"2/28/2017");
    }

    //Tests String Constructor
    @Test
    public void PAGtestConstructor2() {
        GeoCountDownTimer s = new GeoCountDownTimer ("5/10/2017");
        assertTrue(s.toDateString().equals("5/10/2017"));
    }

    //Tests Invalid Date Leap Year
    @Test  (expected = IllegalArgumentException.class)
    public void PAGtestInvalidConstructor() {
        GeoCountDownTimer s = new GeoCountDownTimer ("2/29/2017");
    }

    //Tests invalid date 0
    @Test  (expected = IllegalArgumentException.class)
    public void PAGtestInvalidConstructor2() {
        GeoCountDownTimer s = new GeoCountDownTimer ("2/0/2017");
    }

    //Tests non integer input
    @Test (expected = IllegalArgumentException.class)
    public void PAGtestInvalidConstructor3(){
        GeoCountDownTimer s = new GeoCountDownTimer("aa/bb/cc");
    }

    //Tests Negative Integers
    @Test (expected = IllegalArgumentException.class)
    public void PAGtestInvalidConstructor4() {
        new GeoCountDownTimer(2,-3,-3);
    }

    //Tests Negative Integers in String Constructor
    @Test (expected = IllegalArgumentException.class)
    public void PAGtestInvalidConstructor5() {
        new GeoCountDownTimer("2,-3/-3");
    }

    //Date will not set until Check date is valid
    //This checks isDayValid
    @Test
    public void PAGisDayValid() {
        GeoCountDownTimer s = new GeoCountDownTimer("1/12/3000");
        assertTrue( s.isDayValid(2,28,2016) );
        assertTrue( s.isDayValid(2,29,2816) );
        assertTrue( s.isDayValid(9,30,3018) );
        assertTrue( s.isDayValid(7,18,5025) );
        assertTrue( s.isDayValid(3,25,3028) );
        assertTrue( s.isDayValid(8,31,5438) );
        assertFalse( s.isDayValid(13,1,2815) );
        assertFalse( s.isDayValid(22,19,2011) );
        assertFalse( s.isDayValid(0,0,2815) );
        assertFalse( s.isDayValid(2,0,2815) );
        assertFalse( s.isDayValid(2,29,2815) );
        assertFalse( s.isDayValid(2,29,3023) );
    }

    //Tests Inc and Dec
    @Test
    public void PAGtestAddMethod () {
        GeoCountDownTimer s1 = new GeoCountDownTimer (5,10,2019);
        GeoCountDownTimer s2 = new GeoCountDownTimer(5,9,2020);
        s1.inc(365);
        System.out.println (s1.toDateString().equals("5/9/2020"));
        assertTrue (s1.toDateString().equals("5/9/2020"));
        s1.dec(365);
        assertTrue( s1.toDateString().equals("5/10/2019"));

        s1 = new GeoCountDownTimer (5,10,2016);
        s2 = new GeoCountDownTimer (5,10,2017);

        for (int i = 0; i < 365; i++) {
            s1.inc();
            s2.dec();
        }

        System.out.println (s1);
        assertTrue (s1.toDateString().equals("5/10/2017"));
        assertTrue (s2.toDateString().equals("5/10/2016"));

        s1 = new GeoCountDownTimer (5,10,2016);
        s2 = new GeoCountDownTimer (1,20,2103);
        for (int i = 0; i < 31665; i++) {
            s1.inc();
            s2.dec();
        }
        assertTrue(s1.toDateString().equals("1/20/2103"));
        assertTrue(s2.toDateString().equals("5/10/2016"));

        s1.dec(31665);
        s2.inc(31665);
        assertTrue(s2.toDateString().equals("1/20/2103"));
        assertTrue(s1.toDateString().equals("5/10/2016"));

    }

    //Tests Equals Method
    @Test
    public void PAGtestEqual () {
        GeoCountDownTimer s1 = new GeoCountDownTimer (5,9,3000);
        GeoCountDownTimer s2 = new GeoCountDownTimer (6,1,2016);
        GeoCountDownTimer s4 = new GeoCountDownTimer (5,9,3000);

        assertFalse(s1.equals(s2));
        assertTrue (s1.equals(s4));

    }

    //Tests Compare To Method
    @Test
    public void PAGtestCompareTo () {
        GeoCountDownTimer s1 = new GeoCountDownTimer (5,9,2016);
        GeoCountDownTimer s2 = new GeoCountDownTimer (6,01,2016);
        GeoCountDownTimer s3 = new GeoCountDownTimer (5,8,2016);
        GeoCountDownTimer s4 = new GeoCountDownTimer (5,9,2016);

        assertTrue (s2.compareTo(s1) > 0);
        assertTrue (s3.compareTo(s1) < 0);
        assertTrue (s1.compareTo(s4) == 0);

    }

    //Test Load ans Save Method
    @Test
    public void PAGtestLoadSave () {
        GeoCountDownTimer s1 = new GeoCountDownTimer (5,9,2017);
        GeoCountDownTimer s2 = new GeoCountDownTimer (5,9,2017);

        s1.save("file1");
        s1 = new GeoCountDownTimer (1,1,2016);  // resets to zero
        s1.load("file1");
        assertTrue (s2.equals(s1));

    }

    //Test daysToGo Method
    @Test
    public void PAGtestDaysToGo () {
        GeoCountDownTimer s1 = new GeoCountDownTimer (2,9,2016);
        assertTrue (s1.daysToGo("2/1/2016") == 8);
        assertTrue (s1.daysToGo("2/8/2016") == 1);
        assertTrue (s1.daysToGo("2/9/2016") == 0);

        s1 = new GeoCountDownTimer (9,7,5012);
        assertEquals(1094477,s1.daysToGo("2/9/2016"));
    }


}