package main

import (
	"log"
	"os"
)

func changeThirdByte(filePath string) {

	data, err := os.ReadFile(filePath)
	if err != nil {
		log.Fatal(err)
	}

	for i := 2; i < len(data); i += 3 {
		data[i] = 0
	}

	err = os.WriteFile(filePath, data, 0644)
	if err != nil {
		log.Fatal(err)
	}
}
