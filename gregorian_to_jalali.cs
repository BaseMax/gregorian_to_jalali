/*
 * Language: C#
 * Date: 2022/01/7
 * Name: gregorian_to_jalali.cs
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

using System;

namespace Date {

public class Convertor
{
public struct DateStruct
    {
    public int year;
    public int month;
    public int day;
    };

private DateStruct GregorianToJalali(int year, int month, int day)
    {
        DateStruct result;

        int[] array = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        if (year <= 1600)
        {
            year -= 621;
            result.year = 0;
        }
        else
        {
            year -= 1600;
            result.year = 979;
        }

        int temp = (year > 2) ? (year + 1) : year;
        int days = ((int)((temp + 3) / 4)) + (365 * year) - ((int)((temp + 99) / 100)) - 80
                   + array[month - 1] + ((int)((temp + 399) / 400)) + day;

        result.year += 33 * ((int)(days / 12053));
        days %= 12053;
        result.year += 4 * ((int)(days / 1461));
        days %= 1461;

        if (days > 365)
        {
            result.year += (int)((days - 1) / 365);
            days = (days - 1) % 365;
        }

        result.month = (days < 186) ? 1 + (int)(days / 31) : 7 + (int)((days - 186) / 30);

        result.day = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));

        return result;
    }

public String GregorianToJalaliStr(int year, int month, int day)
    {
        DateStruct result = GregorianToJalali(year, month, day);
        string res = string.Format("{0:d}:{1:d}:{2:d}", result.year, result.month, result.day);
        return res;
    }

}
}

public class Program
{
public static void Main()
    {
        Date.Convertor cv = new Date.Convertor();
        Console.WriteLine(cv.GregorianToJalaliStr(2022, 01, 07));
        Console.WriteLine(cv.GregorianToJalaliStr(2022, 1, 7));
    }
}
