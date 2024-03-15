package vjezbanje

import (
	"fmt"
	"os"
)

func createFile() {
	_, err := os.Create("somefilee")
	if err != nil {
		panic(err)
	}
}

func deleteFile() {
	err := os.Remove("somefile")
	if err != nil {
		panic(err)
	}
}

func getInfo() {
	fileInfo, err := os.Stat("somefile")
	if err != nil {
		panic(err)
	}

	fmt.Println("File name:", fileInfo.Name())
	fmt.Println("Size in bytes:", fileInfo.Size())
	fmt.Println("Is directory:", fileInfo.IsDir())
}

func fileExists() bool {
	if _, err := os.Stat("somefile"); err != nil {
		if os.IsNotExist(err) {
			return false
		}
	}
	return true
}

func renameFile() {
	err := os.Rename("somefile", "somefilenew")
	if err != nil {
		panic(err)
	}
}
