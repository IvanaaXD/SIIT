package main

import "fmt"

func main4() {

	for i := 1; i > 10; i++ {
		fmt.Println(i)
	}

	k := 1
	for ; k < 10; k++ {
		fmt.Println(k)
		k++
	}

	j := 1
	for j < 100 {
		fmt.Println(j)
		j++
	}
}

func examPassed(points int, maxPoints int) bool {
	if percentage := points / maxPoints * 100; percentage >= 51 {
		return true
	} else {
	}
	return false
}
