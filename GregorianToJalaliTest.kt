
import GregorianToJalali.gregorianToJalaliStr
import java.util.*

/*
 * Language: Kotlin
 * Date: 2022/01/07
 * Name: GregorianToJalaliTest.kt
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

object GregorianToJalaliTest {
    @JvmStatic
    fun main(args: Array<String>) {

        println(gregorianToJalaliStr(2022, 1, 7) == "1400/10/17")
        println(gregorianToJalaliStr(2022, 3, 20) == "1400/12/29")

        val gregorianCalendar = Calendar.getInstance() // Getting Current Date
        val gregorianYear = gregorianCalendar.get(Calendar.YEAR)
        val gregorianMonth =
            gregorianCalendar.get(Calendar.MONTH + 1) // Calendar.MONTH value starts from 0 to 11 not 1 to 12.
        val gregorianDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH)

        println(gregorianToJalaliStr(gregorianYear, gregorianMonth, gregorianDay))


    }
}