package zadaci

import "errors"

func NtiBroj(n int) (int, error) {

	if n < 1 {
		return 0, errors.New("number n lower than 1")
	}

	x := 1
	brojac := 0

	for brojac < n {
		x++
		if jesteProst(x) {
			brojac++
		}
	}

	return x, nil
}

func jesteProst(x int) bool {

	lol := 0
	for i := 2; i < x; i++ {
		if x%i == 0 {
			lol++
			break
		}
	}

	if lol == 0 {
		return true
	} else {
		return false
	}
}
