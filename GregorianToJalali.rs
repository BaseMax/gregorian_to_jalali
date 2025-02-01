use std::fmt;

#[derive(Debug)]
struct JalaliDate {
    year: usize,
    month: usize,
    day: usize,
}
impl fmt::Display for JalaliDate {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{:04}-{:02}-{:02}", self.year, self.month, self.day)
    }
}

fn gregorian_to_jalali(year: usize, month: usize, day: usize) -> JalaliDate {
    let mut result = JalaliDate {
        year: 0,
        month: 0,
        day: 0,
    };

    let days_of_greg_month = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];

    let (adjusted_year, initial_year) = if year <= 1600 {
        (year - 621, 0)
    } else {
        (year - 1600, 979)
    };

    result.year = initial_year;

    let temp = if adjusted_year > 2 {
        adjusted_year + 1
    } else {
        adjusted_year
    };

    let mut days = ((temp + 3) / 4) + (365 * adjusted_year) - ((temp + 99) / 100) - 80
        + days_of_greg_month[month - 1]
        + ((temp + 399) / 400)
        + day;

    result.year += 33 * (days / 12053);
    days %= 12053;

    result.year += 4 * (days / 1461);
    days %= 1461;

    if days > 365 {
        result.year += (days - 1) / 365;
        days = (days - 1) % 365;
    }

    result.month = if days < 186 {
        1 + (days / 31)
    } else {
        7 + ((days - 186) / 30)
    };

    result.day = if days < 186 {
        1 + (days % 31)
    } else {
        1 + ((days - 186) % 30)
    };

    result
}

fn main() {
    let date = gregorian_to_jalali(2014, 2, 4);
    println!("{}", date);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_gregorian_to_jalali() {
        let date = gregorian_to_jalali(2025, 3, 20);
        assert_eq!(date.to_string(), "1403-12-30");
    }

    #[test]
    fn test_gregorian_to_jalali_edge_case() {
        let date = gregorian_to_jalali(2025, 3, 21);
        assert_eq!(date.to_string(), "1404-01-01");
    }

    #[test]
    fn test_display_format() {
        let date = gregorian_to_jalali(2014, 2, 4);
        assert_eq!(date.to_string(), "1392-11-15");
    }
}
