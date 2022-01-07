#include <iostream>

using namespace Date;

int main()
{
    Convertor conv;
    std::cout << conv.gregorianToJalaliStr(2022, 1, 7) << std::endl;
    std::cout << conv.gregorianToJalaliStr(2022, 01, 07) << std::endl;
}

