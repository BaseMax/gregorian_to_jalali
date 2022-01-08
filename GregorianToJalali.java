/*
 * Language: JAVA
 * Date: 2022/01/07
 * Name: GregorianToJalali.java
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

import java.util.HashMap;

public class GregorianToJalali {

    public static HashMap<String, Integer> gregorianToJalali(int year, int month, int day) {
        HashMap<String, Integer> result = new HashMap<>();
        int array[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        if (year <= 1600) {
            year -= 621;
            result.put("year", 0);
        } else {
            year -= 1600;
            result.put("year", 979);
        }

        int temp = year > 2 ? year + 1 : year;
        int days = ((int) ((temp + 3) / 4)) + (365 * year) - ((int) ((temp + 99) / 100)) - 80 + array[month - 1] + ((int) ((temp + 399) / 400)) + day;
        result.put("year", result.get("year") + 33 * ((int) (days / 12053)));
        days %= 12053;
        result.put("year", result.get("year") + 4 * ((int) (days / 1461)));
        days %= 1461;

        if (days > 365) {
            result.put("year", result.get("year") + (int) ((days - 1) / 365));
            days = (days - 1) % 365;
        }

        result.put("month", (days < 186)
                ? 1 + (int) (days / 31)
                : 7 + (int) ((days - 186) / 30));

        result.put("day", 1 + ((days < 186)
                ? (days % 31)
                : ((days - 186) % 30)));

        return result;
    }

    public static String gregorianToJalaliStr(int year, int month, int day) {
        HashMap<String, Integer> result = gregorianToJalali(year, month, day);

        String monthSt = result.get("month") < 10 ? String.format("%02d", result.get("month")) : result.get("month").toString();
        String daySt = result.get("day") < 10 ? String.format("%02d", result.get("day")) : result.get("day").toString();

        return result.get("year") + "/" + monthSt + "/" + daySt;
    }
}
