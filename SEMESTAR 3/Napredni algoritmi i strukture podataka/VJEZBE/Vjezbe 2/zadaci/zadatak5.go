package main

import (
	"fmt"
	"log"
	"os"
)

func changeBytes(filePath string, vrednosti []byte) {

	file, err := os.OpenFile(filePath, os.O_RDWR, 0644)
	if err != nil {
		log.Fatal(err)
	}

	defer file.Close()

	fileInfo, _ := file.Stat()
	sredina := fileInfo.Size() / 2
	_, err = file.Seek(sredina, 0)
	if err != nil {
		log.Fatal(err)
	}

	n, err := file.Write(vrednosti)
	if err != nil || n != len(vrednosti) {
		fmt.Println("Greška pri pisanju novih vrednosti:", err)
		return
	}

}
