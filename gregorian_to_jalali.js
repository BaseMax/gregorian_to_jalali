/*
 * Language: JS
 * Date: 2022/01/7
 * Name: gregorian_to_jalali.js
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 */

const gregorian_to_jalali = (year, month, day) => {

	let result = [],
        array = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334],
        days;

	if(year <= 1600) {
		year -= 621;
		result["year"] = 0;
	} else {
		year -= 1600;
		result["year"] = 979;
	}

	temp = (year > 2) ? (year + 1) : year;
	days = (parseInt((temp + 3) / 4)) + (365 * year) - (parseInt((temp + 99) / 100)) - 80 + array[month - 1] + (parseInt((temp + 399) / 400)) + day;
	result["year"] += 33 * (parseInt(days / 12053));
	days %= 12053;
	result["year"] += 4 * (parseInt(days / 1461));
	days %= 1461;

	if(days > 365){
		result["year"] += parseInt((days - 1) / 365);
		days = (days-1) % 365;
	}

	result["month"] = (days < 186)
							? 1 + parseInt(days / 31)
							: 7 + parseInt((days - 186) / 30);

	result["day"] = 1 + ((days < 186)
							? (days % 31)
							: ((days - 186) % 30));

	return result;

}

const gregorian_to_jalali_str = (year, month, day) => {

	let result = gregorian_to_jalali(year, month, day);

	if(result["month"] < 10)
		result["month"] = "0" + result["month"];
	if(result["day"] < 10)
		result["day"] = "0" + result["day"];

	return result["year"] + "/" + result["month"] + "/" + result["day"];

}