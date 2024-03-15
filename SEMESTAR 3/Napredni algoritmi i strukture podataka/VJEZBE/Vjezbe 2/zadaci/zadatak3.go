package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

func ReadAndPrint(filePath string) {

	file, err := os.Open(filePath)
	if err != nil {
		log.Fatal(err)
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanWords)

	words := make([]string, 0)
	for scanner.Scan() {
		word := scanner.Text()
		words = append(words, word)
		if len(words) >= 3 {
			break
		}
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	fmt.Printf("First three words from %s: %s\n", filePath, strings.Join(words, " "))
}
