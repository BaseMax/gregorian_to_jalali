/*
 * Language: C
 * Date: 2021/12/30
 * Name: gregorian_to_jalali.test.c
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

#include <stdio.h>
#include <assert.h>
#include <string.h>

int main()
{
    // printf("%s\n", gregorian_to_jalali_str(2021, 12, 30));
    assert(
        strcmp(gregorian_to_jalali_str(2021, 12, 30), "1400/10/09") == 0
    );

    return 0;
}
