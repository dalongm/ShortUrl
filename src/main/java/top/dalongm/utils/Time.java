package top.dalongm.utils;

import java.util.Date;

public class Time {

    public static Double getDateDiff(Date d1, Date d2){
        long d = d2.getTime()-d1.getTime();
        return d/(double)(60*60*24*1000);
    }
}
