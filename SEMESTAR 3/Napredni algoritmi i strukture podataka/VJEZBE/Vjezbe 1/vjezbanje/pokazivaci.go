package main

import "fmt"

func main7() {

	i := 5
	var p *int = &i

	fmt.Println(p)
	fmt.Println(*p)

	*p += 5
	fmt.Println(*p, i)
}
