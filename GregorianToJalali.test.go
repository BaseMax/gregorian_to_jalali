package main

import (
	"testing"
)

func assertEqual(t *testing.T, expected, actual interface{}) {
	if expected != actual {
		t.Errorf("Expected %v, but got %v", expected, actual)
	}
}

func TestGregorianToJalali(t *testing.T) {
	assertEqual(t, Date{Year: 1400, Month: 11, Day: 02}, GregorianToJalali(2022, 1, 22))
	assertEqual(t, Date{Year: 1401, Month: 01, Day: 15}, GregorianToJalali(2022, 4, 4))
	assertEqual(t, Date{Year: 1402, Month: 07, Day: 17}, GregorianToJalali(2023, 10, 9))
	assertEqual(t, Date{Year: 1402, Month: 10, Day: 05}, GregorianToJalali(2023, 12, 26))
}
