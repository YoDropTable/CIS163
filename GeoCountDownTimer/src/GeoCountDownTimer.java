/**
 * Created by Phil on 8/31/16.
 */
public class GeoCountDownTimer {

    private int years;
    private int months;
    private int days;
    private int[] dayValues = { 0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    String monthNames[] = new String[]{"","January", "February", "March", "April", "May", "June",
            "July","August","September","October","November","December"};



    private GeoCountDownTimer() {
        years = 0;
        months = 0;
        years = 0;
    }

    public GeoCountDownTimer(int month, int day, int year)
    {
        this.setDate(month,day,year);

    }

    public GeoCountDownTimer(String geoDate)
    {
        GeoCountDownTimer temp = new GeoCountDownTimer();

        String[] s = geoDate.split("/");
        if(s.length==3)
            this.setDate(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
        else
            throw new IllegalArgumentException();
    }

    public GeoCountDownTimer(GeoCountDownTimer geoDate)
    {
        this.setDate(geoDate.getMonths(),geoDate.getDays(),geoDate.getYears());
    }

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

        throw new IllegalArgumentException();
    }

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
        else
            return 0;
    }

    public int getYears()
    {
        return this.years;
    }
    public int getMonths() {
        return this.months;
    }
    public int getDays()
    {
        return this.days;
    }
    public void setDays(int days){ this.days = days;    }
    public void setMonths(int months) {
        this.months = months;
    }
    public void setYears(int years) {
        this.years = years;
    }

    public void dec(int days)
    {
        for(int i=0;i<days;i++)
            this.dec();
    }
    public void setDate(int m,int d, int y)
    {
        if(isDayValid(m,d,y))
        {
            setMonths(m);
            setYears(y);
            setDays(d);
        }
        else
            throw new IllegalArgumentException();
    }
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
    public void setLeapYear()
    {
        if(isLeapYear(this.years))
            dayValues[2] = 29;
        else
            dayValues[2] = 28;
    }

    public boolean isDayValid(int pMonth, int pDay, int pYear)
    {
        int[] tempDayValues = dayValues;
        if (isLeapYear(pYear))
            tempDayValues[2] = 29;
        else
            tempDayValues[2] = 28;
        if(isMonthValid(pMonth) && isYearValid(pYear))
            if (pDay >= 0 && pDay <= tempDayValues[pMonth])
                return true;
        return false;
    }
    public boolean isMonthValid(int pMonth)
    {
        if(pMonth >= 1 && pMonth <= 12)
        {
            return true;
        }
        return false;
    }

    public boolean isYearValid(int pYear)
    {
        if(pYear > 2014)
            return true;
        return false;
    }

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

    public void inc( int days)
    {
        for(int i=0;i<days;i++)
            this.inc();
    }

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

    public String toString()
    {
        return monthNames[this.months] + " " + this.days + "," + this.years;
    }
    public String toDateString()
    {

        return this.months + "/" + this.days + "/" + this.years;
    }
}
