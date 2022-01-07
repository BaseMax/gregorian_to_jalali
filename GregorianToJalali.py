# Language: Python
# Date: 2022/01/07
# Name: GregorianToJalali.py
# Repository: https://github.com/BaseMax/gregorian_to_jalali




class GregorianToJalali :


    def __init__(self):
        pass

    
    def  gregorianToJalali(self, year,  month,  day) :
        result =  dict()
        array = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334]
        if (year <= 1600) :
            year -= 621
            result["year"] = 0
        else :
            year -= 1600
            result["year"] = 979
        temp = year + 1 if year > 2 else year
        days = (int((int((temp + 3) / 4)))) + (365 * year) - (int((int((temp + 99) / 100)))) - 80 + array[month - 1] + (int((int((temp + 399) / 400)))) + day
        result["year"] = result.get("year") + 33 * (int((int(days / 12053))))
        days %= 12053
        result["year"] = result.get("year") + 4 * (int((int(days / 1461))))
        days %= 1461
        if (days > 365) :
            result["year"] = result.get("year") + int((int((days - 1) / 365)))
            days = (days - 1) % 365
        result["month"] = 1 + int((int(days / 31))) if (days < 186) else 7 + int((int((days - 186) / 30)))
        result["day"] = 1 + ((days % 31) if (days < 186) else ((days - 186) % 30))
        return result

    def  gregorianToJalaliStr(self, year,  month,  day) :
        result = self.gregorianToJalali(year, month, day)
        if (result.get("month") < 10) : result["month"] = int(String.format("%02d",result.get("month")))
        if (result.get("day") < 10) : result["day"] = int(String.format("%02d",result.get("day")))
        return str(result.get("year")) + "/" + str(result.get("month")) + "/" + str(result.get("day"))

