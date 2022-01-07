/*
 * Language: Dart
 * Date: 2022/01/07
 * Name: GregorianToJalaliTest.dart
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

import 'GregorianToJalali.dart';

void main() {
  print(GregorianToJalali.gregorianToJalaliStr(2022, 1, 7) == "1400/10/17");
  print(GregorianToJalali.gregorianToJalaliStr(2022, 3, 20) == "1400/12/29");
}
