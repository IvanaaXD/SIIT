package main

import (
	"fmt"
	"log"
	"os"
)

func sizeOfFile(filePath string) {

	fileInfo, err := os.Stat(filePath)
	if os.IsNotExist(err) {

		file, err := os.Create(filePath)
		if err != nil {
			log.Fatal(err)
		}
		defer file.Close()

		fmt.Println("File created:", filePath)

	} else if err != nil {

		log.Fatal(err)

	} else {

		fmt.Printf("File Size: %d bytes\n", fileInfo.Size())
		fmt.Printf("Last Modified: %v\n", fileInfo.ModTime())

	}

}

/*func main() {
	filePath := "example.txt"
	sizeOfFile(filePath)
}*/
