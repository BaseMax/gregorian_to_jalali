/*
 * Language: C++/Qt
 * Date: 2022/01/7
 * Name: gregorian_to_jalali.qt.cpp
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */


#include <QCoreApplication>
#include <QObject>
#include <QVarLengthArray>
#include <QString>
#include <QTextStream>

namespace Date {

struct DateStruct
{
    int year;
    int month;
    int day;
};

class Convertor
{
public:
    [[maybe_unused]] DateStruct gregorianToJalali(int year, int month, int day) const noexcept
    {
        DateStruct result{};

        QVarLengthArray<int, 12> array{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        if (year <= 1600) {
            year -= 621;
            result.year = 0;
        } else {
            year -= 1600;
            result.year = 979;
        }

        int temp = (year > 2) ? (year + 1) : year;
        int days = ((int) ((temp + 3) / 4)) + (365 * year) - ((int) ((temp + 99) / 100)) - 80
                   + array[month - 1] + ((int) ((temp + 399) / 400)) + day;

        result.year += 33 * ((int) (days / 12053));
        days %= 12053;
        result.year += 4 * ((int) (days / 1461));
        days %= 1461;

        if (days > 365) {
            result.year += (int) ((days - 1) / 365);
            days = (days - 1) % 365;
        }

        result.month = (days < 186) ? 1 + (int) (days / 31) : 7 + (int) ((days - 186) / 30);

        result.day = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));

        return result;
    }

    [[maybe_unused]] QString gregorianToJalaliStr(int year, int month, int day) const noexcept
    {
        DateStruct result = gregorianToJalali(year, month, day);

        QString qs;

        QTextStream out(&qs);

        out << result.year << "/";

        if (result.month < 10) {
            out << 0 << result.month << "/";
        } else {
            out << result.month << "/";
        }

        if (result.day < 10) {
            out << 0 << result.day;
        } else {
            out << result.day;
        }

        return out.readAll();
    }
};

}

using namespace Date;

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Convertor conv;
    qDebug() << conv.gregorianToJalaliStr(2022, 01, 07);
    qDebug() << conv.gregorianToJalaliStr(2022, 1, 7);
    return 0;
}
