package TSPTW;

import java.security.PublicKey;

public class Time {
    // Test function
//    public static void main(String[] args) {
//        int m1 = 1440*(365*4+30)+127;
//        Time t1 = new Time(m1);
//        System.out.println(t1);
//        System.out.println(t1.getTime());
//        System.out.println(t1.toDateString());
//        System.out.println(t1.toTimeString());
//
//        Time t2 = new Time(2013, 1, 30, 2, 7);
//        System.out.println(t2);
//        System.out.println(t2.getTime());
//
//        Time t3 = new Time(340);
//        System.out.println(t3.toSerString());
//    }

    // Function
    public Time(long p_time) {
        setTime(p_time);
    }

    public Time(final long y, final long mo, final long d, final long h, final long m) {
        setTime(y, mo, d, h, m);
    }

    public Time(final int y, final int mo, final int d, final int h, final int m) {
        setTime(y, mo, d, h, m);
    }

    // Get function
    public long getTime() {
        return time;
    }

    // Set function
    public void setTime(long time) {
        this.time = time;
        timeToFormat();
    }

    public void setTime(final long y, final long mo, final long d, final long h, final long m) {
        year  = y;
        month = mo;
        day   = d;
        hr    = h;
        min   = m;
        formatToTime();
    }

    public void setTime(final int y, final int mo, final int d, final int h, final int m) {
        year  = (long) y;
        month = (long) mo;
        day   = (long) d;
        hr    = (long) h;
        min   = (long) m;
        formatToTime();
    }

    // Translate
    public void formatToTime() {
        // 1. Count the number of days.
        long days = 0;
        long years = year - 2009;
        for ( ; years >= 4; years -= 4) {days += 1461;} // 4 years = 1,461 days
        boolean isLeap = years == 3;
        days += 365 * years;

        for (int m = (int)month - 1; m > 0; --m) {
            if (!isLeap) {
                days += monthDay[m];
            } else {
                days += monthDayLeap[m];
            }
        }
        days += day - 1;

        // 2. Calculate total min.
        time = days * 1440 + hr * 60 + min; // 1 day = 1440 (24 * 60) min
    }

    public void timeToFormat() {
        day = time; // min
        min = day % 60;
        day /= 60;  // min -> hr
        hr = day % 24;
        day /= 24;  // hr -> day
        ++day;

        year = 2009;
        month = 1;
        int isLeap = 0;
        for ( ; day >= 1461; day -= 1461) {year += 4;}       // 4 year = 1,461 days
        for ( ; day >= 366; day -= 365) {++year; ++isLeap;} // 1 year = 365 days
        for ( ; month <= 12; ++month) {
            if (isLeap != 3) { // This year is not leap.
                if (day > monthDay[(int)month]) {
                    day -= monthDay[(int)month];
                } else {
                    break;
                }
            } else {           // This year is leap.
                if (day > monthDayLeap[(int)month]) {
                    day -= monthDayLeap[(int)month];
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return year + "/" + fill(month) + "/" + fill(day) + " " + fill(hr) + ":" + fill(min);
    }

    public String toSerString() {
        long p_hr = time / 60;
        return fill(p_hr) + ":" + fill(min);
    }

    public String  toDateString() {
        return year + "/" + fill(month) + "/" + fill(day);
    }

    public String toTimeString() {
        return fill(hr) + ":" + fill(min);
    }

    private String fill(final long n) {
        if (0 <= n && n <= 9) {
            return "0" + n;
        } else {
            return Long.toString(n);
        }
    }

    static public long DHMtoTime(final int d, final int h, final int m) {
        return d * 1440 + h * 60 + m; // 1 day = 1440 (24 * 60) min
    }

    // Data
    private long time = 0; // min, 0 : 2009/1/1 0:0
    static public int[] monthDay = {0,  31, 28, 31, 30, 31, 30,
                                        31, 31, 30, 31, 30, 31};
    static public int[] monthDayLeap = {0,  31, 29, 31, 30, 31, 30,
                                            31, 31, 30, 31, 30, 31};
    public long year = 2009;
    public long month = 1;
    public long day = 1;
    public long hr = 0;
    public long min = 0;
}
