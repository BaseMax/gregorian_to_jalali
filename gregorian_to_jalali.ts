/*
 * Language: TS
 * Date: 2024/2/2
 * Name: gregorian_to_jalali.ts
 * Repository: https://github.com/BaseMax/gregorian_to_jalali
 * Developer: Sameep Shah (https://github.com/sameep-git)
 */

const gregorian_to_jalali_ts = (year: number, month: number, day: number): { year: number; month: number; day: number } => {
	let result : { year: number; month: number; day: number } = { year: 0, month: 0, day: 0},
        array: number[] = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334],
        days: number;

	if(year <= 1600) {
		year -= 621;
		result["year"] = 0;
	} else {
		year -= 1600;
		result["year"] = 979;
	}

	const temp: number = (year > 2) ? (year + 1) : year;
	days = (Math.floor((temp + 3) / 4)) + (365 * year) - (Math.floor((temp + 99) / 100)) - 80 + array[month - 1] + (Math.floor((temp + 399) / 400)) + day;
	result["year"] += 33 * (Math.floor(days / 12053));
	days %= 12053;
	result["year"] += 4 * (Math.floor(days / 1461));
	days %= 1461;

	if(days > 365){
		result["year"] += Math.floor((days - 1) / 365);
		days = (days-1) % 365;
	}

	result["month"] = (days < 186)
							? 1 + Math.floor(days / 31)
							: 7 + Math.floor((days -186) /30);

	result["day"] =  1 + ((days <186)
							? (days %31)
							: ((days -186) %30));

	return result;
}

const gregorian_to_jalali_str_ts = (year: number, month: number, day: number): string => {
	let result = gregorian_to_jalali_ts(year, month, day);
	let answer : {year: string, month: string, day: string} = {year: "", month: "", day: ""};
	if(result["month"] < 10) {
		answer["month"] = "0" + result["month"].toString();
	} else {
		answer["month"] = result["month"].toString()
	}
	if(result["day"] < 10) answer["day"] = "0" + result["day"].toString();
	else answer["day"] = result["day"].toString()

	return result["year"].toString() + "/" + answer["month"] + "/" + answer["day"];
}