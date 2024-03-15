package main

import "fmt"

var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}

func main9() {
	a := make([]int, 5, 5)
	fmt.Println(a)
	//printSlice("a", a)

	b := make([]int, 0, 5)
	fmt.Println(b)
	//printSlice("b", b)

	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}
}
