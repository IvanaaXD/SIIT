package main

import "fmt"

var a int
var b, c string = "Hello", "world"
var d, e = 1, false

func main2() {
	var i int
	var j = 5
	k := true

	fmt.Println(a, b, c, d, e)

	fmt.Println(i, j, k)

	fmt.Printf("Type of k is: %T", k)
}
