package main

import "fmt"

func main8() {

	var a [2]string
	a[0] = "Hello"
	a[1] = "world"

	fmt.Println(a[0], a[1])
	fmt.Println(a)

	nums := [6]int{1, 2, 3, 4}
	fmt.Println(nums)
}
