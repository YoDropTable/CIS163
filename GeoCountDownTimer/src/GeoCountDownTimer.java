import java.io.*;
import java.util.Scanner;

/**********************************************************************
 *
 * GeoCountDownTimer Class
 *  @Author Phillip Garza
 *  @Version 1.0
 *
 *********************************************************************/
public class GeoCountDownTimer {

    /** The number of years of a given date */
    private int years;

    /** The number of months for a given date */
    private int months;

    /** The number of days for a given date */
    private int days;
    
    /** Stores days in each month. starts at 1 for clarity */
    private int[] dayValues = { 0,31, 28, 31, 30, 31, 30, 31,
                                    31, 30, 31, 30, 31 };
    /** Stores Formal Month Names for print values. Starts 1 for ease */
    String monthNames[] = new String[]{"","January", "February",
            "March", "April", "May", "June", "July","August",
            "September","October","November","December"};


    //Private constructor in project doc
    private GeoCountDownTimer() {
        years = 0;
        months = 0;
        years = 0;
    }

    /******************************************************************
     *
     * Constructor for integer values
     * Uses setDate(m,d,y) to validate input
     *
     * @param month numbers of months in a year
     * @param day number of days in a year
     * @param year number of years
     ******************************************************************/
    public GeoCountDownTimer(int month, int day, int year)
    {

        this.setDate(month,day,year);
    }

    /******************************************************************
     *
     * Constructer from a string in format mm/dd/yyyy
     * uses setDate(m,d,y) to validate input
     *
     * @param geoDate date given in mm/dd/yyyy form
     *
     * @throw IllegalArgumentException when not a valid string
     ******************************************************************/
    public GeoCountDownTimer(String geoDate)
    {
        GeoCountDownTimer temp = new GeoCountDownTimer();

        String[] s = geoDate.split("/");
        if(s.length==3)
            this.setDate(Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2]));
        else
            throw new IllegalArgumentException("Use Format mm/dd/yyyy");
    }

    /******************************************************************
     *
     * Construcotr from another GeoCountDownTimer
     *
     * @param geoDate GeoCountDownTimer to copy values from
     ******************************************************************/
    public GeoCountDownTimer(GeoCountDownTimer geoDate)
    {
        this.setDate(geoDate.getMonths(),geoDate.getDays(),
                geoDate.getYears());
    }

    /******************************************************************
     *
     * Equals method GeoCountDownTimer
     * only compares other GeoCountDownTimers
     *
     * @param other object to compare to
     * @return boolean states weather its equal
     *
     * @throw IllegalArgmentException if not null or another
     * GeoCountdownTimer
     ******************************************************************/
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (other instanceof GeoCountDownTimer)
        {
            GeoCountDownTimer otherCast = (GeoCountDownTimer) other;
            if( (this.years == otherCast.getYears()) &&
                    (this.months == otherCast.getMonths())
                    && (this.days == otherCast.getDays()))
                return true;
            else
                return false;
        }

        throw new IllegalArgumentException("Not a valid type");
    }

    /******************************************************************
     *
     * Compares another GeoCountDownTimer to this object
     *
     * @param other value to compare too
     * @return retuns 1 for great
     *                0 for equal
     *               -1 for less than
     ******************************************************************/
    public int compareTo(GeoCountDownTimer other)
    {
        if( other.getYears() > this.years )
            return -1;
        else if( other.getYears() < this.years )
            return 1;
        if (other.getMonths() > this.months)
            return -1;
        else if(other.getMonths() < this.months)
            return 1;
        if (other.getDays() > this.days)
            return -1;
        else if(other.getDays() < this.days)
            return 1;
        return 0;
    }

    /******************************************************************
     *
     * Get Years
     * @return Years
     ******************************************************************/
    public int getYears()
    {
        return this.years;
    }

    /******************************************************************
     *
     * Get Months
     * @return months
     ******************************************************************/
    public int getMonths() {
        return this.months;
    }

    /******************************************************************
     * Get Days
     * @return days
     ******************************************************************/
    public int getDays()
    {
        return this.days;
    }

    /******************************************************************
     * Set Days
     * @param days
     ******************************************************************/
    public void setDays(int days){ this.days = days;    }

    /******************************************************************
     * Set Months
     * @param months
     *
     * @throws IllegalArgumentException depending on value of month
     ******************************************************************/
    public void setMonths(int months) {
        if(isMonthValid(months))
            this.months = months;
        else
            throw new IllegalArgumentException(months +
                    " is not a Valid Month");
    }

    /******************************************************************
     * Set Years
     * @param years
     *
     * @throws IllegalArgumentException depnding on months
     ******************************************************************/
    public void setYears(int years) {
        if(isYearValid(years))
            this.years = years;
        else
            throw new IllegalArgumentException(years +
                    " is not a valid Year");
    }

    /******************************************************************
     * Sets complete days with month / days/ year.. checks if its a
     * valid day before setting or throws illegal Argument Exception
     *
     * @param m months in year
     * @param d days in a month
     * @param y years
     *
     * @throws IllegalArgumentException if its not a valid day
     ******************************************************************/
    public void setDate(int m,int d, int y)
    {
        if(isDayValid(m,d,y))
        {
            setMonths(m);
            setYears(y);
            setDays(d);
        }
        else
            throw new IllegalArgumentException(m + "/" + d + "/" + y +
                    " is not a valid date");
    }

    /******************************************************************
     * Set Date from another GeoCountDownTimer
     * @param temp other date to set
     ******************************************************************/
    public void setDate(GeoCountDownTimer temp)
    {
        this.setDate( temp.getMonths(),
                temp.getDays(),
                temp.getYears() );
    }

    /******************************************************************
     * Checks weather given year is a leap year
     * @param y year to check
     * @return true or false depending on leap year
     ******************************************************************/
    public boolean isLeapYear(int y)
    {
        if(y % 4 == 0)
        {
            if(y % 100 == 0)
            {
                if (y % 400 == 0)
                    return true;
                return false;
            }
            return true;
        }
        return false;
    }

    /******************************************************************
     * used in increment and decrement. sets the value for february
     * depending if its a leap year
     ******************************************************************/
    public void setLeapYear()
    {
        if(isLeapYear(this.years))
            dayValues[2] = 29;
        else
            dayValues[2] = 28;
    }

    /******************************************************************
     * Checks weather day is valid
     * @param pMonth month in years
     * @param pDay days in years
     * @param pYear years
     * @return Checks weather it is a valid day.
     ******************************************************************/
    public boolean isDayValid(int pMonth, int pDay, int pYear)
    {
        int[] tempDayValues = dayValues;
        if (isLeapYear(pYear))
            tempDayValues[2] = 29;
        else
            tempDayValues[2] = 28;
        if(isMonthValid(pMonth) && isYearValid(pYear))
            if (pDay > 0 && pDay <= tempDayValues[pMonth])
                return true;
        return false;
    }

    /******************************************************************
     * Checks if month is valid
     * @param pMonth Month to check
     * @return true or false depending on value of month
     ******************************************************************/
    public boolean isMonthValid(int pMonth)
    {
        if(pMonth >= 1 && pMonth <= 12)
        {
            return true;
        }
        return false;
    }

    /******************************************************************
     * Checks if year is valid
     * @param pYear year to be checked
     * @return true of false
     ******************************************************************/
    public boolean isYearValid(int pYear)
    {
        if(pYear > 2015)
            return true;
        return false;
    }

    /******************************************************************
     * Decrements GeoCountdownTimer by 1 day
     *
     ******************************************************************/
    public void dec()
    {
        setLeapYear();
        if( this.days == 1)
        {
            if(this.months==1)
            {
                this.setYears(this.years - 1);
                this.setMonths(12);
            }
            else
                setMonths(this.months-1);
            this.setDays(dayValues[this.months]);
        }
        else
            this.setDays(this.days - 1);
    }

    /******************************************************************
     * Decrements GeoCountdownTimer by a value given
     * @param days days to decrement
     ******************************************************************/
    public void dec(int days)
    {
        for(int i=0;i<days;i++)
            this.dec();
    }

    /******************************************************************
     * Increments GeoCountdownTimer by 1 day
     ******************************************************************/
    public void inc()
    {
        setLeapYear();
        if( this.days == dayValues[this.months])
        {
            this.setDays(1);
            if( this.months == 12)
            {
                this.setMonths(1);
                this.setYears(this.years + 1);
            }
            else
                setMonths(this.months + 1);
        }
        else
            this.setDays(this.days + 1);
    }

    /******************************************************************
     * Increments GeoCountdownTimer by a number of days
     * @param days days to increment GeoCountdownTimer
     ******************************************************************/
    public void inc( int days)
    {
        for(int i=0;i<days;i++)
            this.inc();
    }

    /******************************************************************
     * when given a date is given. counts the number of days to the
     * given date. Date has to be in the past
     * @param fromDate date in the past to count the days to
     * @return number of dates between the dates
     ******************************************************************/
    public int daysToGo(String fromDate)
    {
        GeoCountDownTimer past = new GeoCountDownTimer(fromDate);
        if(this.compareTo(past)==-1)
            throw new IllegalArgumentException("Date is in the future");
        else {
            GeoCountDownTimer now = new GeoCountDownTimer(this);
            int counter = 0;
            while (now.compareTo(past) > 0) {
                now.dec();
                counter++;
            }
            return counter;
        }
    }

    /******************************************************************
     * Function that gives a string value to the date in the format
     * "[FROMAL MONTH NAME] [DAYS], [YEAR]
     * @return a string value of GeoCountdownTimer
     ******************************************************************/
    public String toString()
    {
        return monthNames[this.months] + " " + this.days + "," +
                this.years;
    }

    /******************************************************************
     * Retruns the date in the format m/d/yyyy
     * @return a string int he above format
     ******************************************************************/
    public String toDateString()
    {

        return this.months + "/" + this.days + "/" + this.years;
    }

    /******************************************************************
     * Loads the dates from a given file
     * @param filename filename to read dates from
     ******************************************************************/
    public void load(String filename)
    {
        try{
            Scanner fileReader = new Scanner(new File(filename));
            String logline = fileReader.nextLine();
            System.out.println(logline);
            GeoCountDownTimer temp = new GeoCountDownTimer(logline);
            this.setDate(temp);
        }
        catch (Exception error){
            System.out.println("File not found..");
        }
    }

    /******************************************************************
     * Save dates to a given file
     * @param filename filename to write to
     ******************************************************************/
    public void save(String filename)
    {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.println(this.toDateString());
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
