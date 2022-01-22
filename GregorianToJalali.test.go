package main

import (
	"fmt"
	"testing"
)

func TestGregorianToJalali(t *testing.T) {
	result := GregorianToJalali(2022, 1, 22)
	date := fmt.Sprintf("%d/%d/%d", result.Year, result.Month, result.Day)
	want := "1400/11/02"
	if date != want {
		t.Errorf("got %s, want %s", date, want)
	}
}
