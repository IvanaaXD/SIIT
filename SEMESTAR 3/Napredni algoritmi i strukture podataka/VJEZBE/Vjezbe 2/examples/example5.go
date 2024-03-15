package main

import (
	"encoding/gob"
	"fmt"
	"os"
)

type Student struct {
	Name string
	Age  uint8
}

func main() {
	student := Student{"Pera Peric", 22}
	filePath := fmt.Sprintf("files%cfile5.bin", os.PathSeparator)
	file, err := os.OpenFile(filePath, os.O_RDWR|os.O_CREATE, 0644)

	encoder := gob.NewEncoder(file)
	err = encoder.Encode(student)
	if err != nil {
		panic(err)
	}

	student = Student{"Mika Mikic", 21}
	err = encoder.Encode(student)
	if err != nil {
		panic(err)
	}

	decoder := gob.NewDecoder(file)
	var studentRead = new(Student)
	file.Seek(0, 0)
	for {
		err = decoder.Decode(studentRead)
		if err != nil {
			break
		}
		fmt.Println(studentRead)
	}
}
