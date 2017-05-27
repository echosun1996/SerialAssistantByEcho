package com.echosun.sysmain;

/**
 * Created by echosun.
 * All rights reserved.
 */


public class demo {

    public static void main(String[] args) throws Exception {
        String data = "$GPRMC,104330.00,A,3729.12712,N,12126.30267,E,0.542,,150417,,,A*7E\n" +
                "$GPVTG,,T,,M,0.542,N,1.005,K,A*24\n" +
                "$GPGGA,104330.00,3729.12712,N,12126.30267,E,1,05,4.66,32.8,M,7.0,M,,*58\n" +
                "$GPGSA,A,3,22,03,01,08,11,,,,,,,,7.07,4.66,5.31*0B\n" +
                "$GPGSV,3,1,11,01,81,095,23,03,20,148,34,07,29,201,,08,32,066,39*79\n" +
                "$GPGSV,3,2,11,11,67,044,22,17,23,275,,19,03,264,,22,31,121,";
        for (String temp : data.split("\n")) {
            if (temp.contains("$GPRMC")) {
                System.out.println(temp);
                System.out.println("in");
                String[] temps = temp.split("\\*")[0].split(",");
                System.out.println(temps.length);
                String check = temp.split("\\*")[1];
                System.out.println("check" + check);

                int i = 0;
                if (temps.length == 13)
                    for (String t : temps) {
                        if (t.equals("$GPRMC")) continue;
                        System.out.println(i + "----" + t);
                        i++;
                    }
            }
        }

    }
}

