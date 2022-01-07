import java.util.HashMap

/*
 * Language: Kotlin
 * Date: 2022/01/07
 * Name: GregorianToJalali.kt
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

object GregorianToJalali {
    fun gregorianToJalali(year: Int, month: Int, day: Int): HashMap<String, Int> {
        var year = year
        val result = HashMap<String, Int>()
        val array = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
        if (year <= 1600) {
            year -= 621
            result["year"] = 0
        } else {
            year -= 1600
            result["year"] = 979
        }
        val temp = if (year > 2) year + 1 else year
        var days =
            ((temp + 3) / 4) + 365 * year - ((temp + 99) / 100) - 80 + array[month - 1] + ((temp + 399) / 400) + day
        result["year"] = result["year"]!! + 33 * (days / 12053)
        days %= 12053
        result["year"] = result["year"]!! + 4 * (days / 1461)
        days %= 1461
        if (days > 365) {
            result["year"] = result["year"]!! + ((days - 1) / 365)
            days = (days - 1) % 365
        }
        result["month"] = if (days < 186) 1 + (days / 31) else 7 + ((days - 186) / 30)
        result["day"] = 1 + if (days < 186) days % 31 else (days - 186) % 30
        return result
    }

    fun gregorianToJalaliStr(year: Int, month: Int, day: Int): String {
        val result = gregorianToJalali(year, month, day)
        if (result["month"]!! < 10) result["month"] =
            String.format("%03d", result["month"]).toInt()
        if (result["day"]!! < 10) result["day"] =
            String.format("%03d", result["day"]).toInt()
        return result["year"].toString() + "/" + result["month"] + "/" + result["day"]
    }
}
 
