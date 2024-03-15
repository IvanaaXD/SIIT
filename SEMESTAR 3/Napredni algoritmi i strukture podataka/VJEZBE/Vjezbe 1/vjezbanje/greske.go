package main

import (
	"fmt"
	"strconv"
)

func main5() {
	i, err := strconv.Atoi("42")
	if err != nil {
		fmt.Printf("couldn't convert number: %v\n\n", err)
		return
	}

	fmt.Println("Converted integer: ", i)
}
