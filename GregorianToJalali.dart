import 'dart:collection';


/*
 * Language: Dart
 * Date: 2022/01/07
 * Name: GregorianToJalali.dart
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */


class GregorianToJalali {
  static HashMap<String, int> gregorianToJalali(int year, int month, int day) {
    HashMap<String, int> result = new HashMap();

    List<int> array = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];

    if (year <= 1600) {
      year -= 621;
      result["year"] = 0;
    } else {
      year -= 1600;
      result["year"] = 979;
    }

    int temp = ((year > 2) ? (year + 1) : year);

    int days =
        ((((((((temp + 3) ~/ 4) + (365 * year)) - ((temp + 99) ~/ 100)) - 80) +
                    array[month - 1]) +
                ((temp + 399) ~/ 400)) +
            day);

    result["year"] = (result["year"]! + (33 * (days ~/ 12053)));

    days = (days % 12053);

    result["year"] = (result["year"]! + (4 * (days ~/ 1461)));

    days = (days % 1461);

    if (days > 365) {
      result["year"] = (result["year"]! + ((days - 1) ~/ 365));
      days = ((days - 1) % 365);
    }

    result["month"] =
        (days < 186) ? (1 + (days ~/ 31)) : (7 + ((days - 186) ~/ 30));

    result["day"] = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));

    return result;
  }

  static String gregorianToJalaliStr(int year, int month, int day) {
    HashMap<String, int> result = gregorianToJalali(year, month, day);

    if (result["month"]! < 10) {
      result["month"] = int.parse(result["month"].toString().padLeft(1, '0'));
    }
    if (result["day"]! < 10) {
      result["day"] = int.parse(result["day"].toString().padLeft(1, '0'));
    }

    return (result["year"].toString() +
        "/" +
        result["month"].toString() +
        "/" +
        result["day"].toString());
  }
}
