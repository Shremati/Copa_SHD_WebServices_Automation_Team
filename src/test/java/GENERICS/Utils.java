package GENERICS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils
{

    public static String getDate_ddMMYYYY(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

        return dateformat.format(date);
    }
    public static String getDate_YYYYMMdd(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(date);
    }
    public static String getDate_ddMMYYYYThhmmss(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss");
        return dateformat.format(date);
    }
    public static String getDate_YYYYMMddThhmmss(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        return dateformat.format(date);
    }

}
