/*
 * Language: QML
 * Date: 2022/01/7
 * Name: gregorian_to_jalali.qml
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

import QtQuick
import QtQuick.Window

Window {
    id: appRoot
    width: 640
    height: 480
    visible: true

    QtObject {
        id: objects
        property var result : [];
        property var array  : [];
        property int temp;
        property int days ;
    }

     function gregorian_to_jalali(year, month, day) {

        objects.array = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];
        objects.days = 0;

        if(year <= 1600) {
            year -= 621;
            objects.result["year"] = 0;
        } else {
            year -= 1600;
            objects.result["year"] = 979;
        }

        objects.temp = (year > 2) ? (year + 1) : year;
        objects.days = (parseInt((objects.temp + 3) / 4)) + (365 * year) - (parseInt((objects.temp + 99) / 100)) - 80 + objects.array[month - 1] + (parseInt((objects.temp + 399) / 400)) + day;
        objects.result["year"] += 33 * (parseInt(objects.days / 12053));
        objects.days %= 12053;
        objects.result["year"] += 4 * (parseInt(objects.days / 1461));
        objects.days %= 1461;

        if(objects.days > 365){
            objects.result["year"] += parseInt((objects.days - 1) / 365);
            objects.days = (objects.days-1) % 365;
        }

        objects.result["month"] = (objects.days < 186) ? 1 + parseInt(objects.days / 31) : 7 + parseInt((objects.days - 186) / 30);

        objects.result["day"] = 1 + ((objects.days < 186) ? (objects.days % 31) : ((objects.days - 186) % 30));
        return objects.result;

    }

    function gregorian_to_jalali_str (year, month, day) {

        let result = gregorian_to_jalali(year, month, day);

        if(objects.result["month"] < 10)
            objects.result["month"] = "0" + objects.result["month"];
        if(objects.result["day"] < 10)
            objects.result["day"] = "0" + objects.result["day"];

        return objects.result["year"] + "/" + objects.result["month"] + "/" + objects.result["day"];

    }

    Component.onCompleted: {
        var date = gregorian_to_jalali_str(2022, 01, 07);
        console.log(gregorian_to_jalali_str(2022, 01, 07));
        console.log(gregorian_to_jalali_str(2022, 1, 7));
        appRoot.title = date;
    }

}
