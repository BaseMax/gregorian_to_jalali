package main

import (
	"fmt"
	"testing"
)

func TestGregorianToJalali(t *testing.T) {
	// TODO: We need to use a function or specially `assert` to easily can add more test cases!
	// TODO: Please add more unit tests.
	result := GregorianToJalali(2022, 1, 22)
	date := fmt.Sprintf("%d/%02d/%02d", result.Year, result.Month, result.Day)
	want := "1400/11/02"
	if date != want {
		t.Errorf("got %s, want %s", date, want)
	}
}
