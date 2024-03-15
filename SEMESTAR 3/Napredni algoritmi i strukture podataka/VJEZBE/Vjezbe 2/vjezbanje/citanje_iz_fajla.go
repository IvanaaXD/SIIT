package vjezbanje

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"os"
)

func readFile(filePath string) {

	file, err := os.Open(filePath)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	//prvi nacin
	bytes := make([]byte, 2)
	_, err = file.Read(bytes)
	if err != nil {
		panic(err)
	}

	//drugi nacin
	bytes = make([]byte, 2)
	_, err = io.ReadFull(file, bytes)
	if err != nil {
		panic(err)
	}

	//treci nacin
	bytes = make([]byte, 2)
	_, err = io.ReadAtLeast(file, bytes, 1)
	if err != nil {
		panic(err)
	}

	//cetvrti nacin
	bytes, err = ioutil.ReadAll(file)
	if err != nil {
		panic(err)
	}

	//peti nacin
	bytes, err = ioutil.ReadFile("folder/somefile")
	if err != nil {
		panic(err)
	}

	//sesti nacin
	bufferedReader := bufio.NewReader(file)
	byteSlice := make([]byte, 5)
	byteSlice, err = bufferedReader.Peek(5)
	_, err = bufferedReader.Read(byteSlice)

	//sedmi nacin
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanWords)
	success := scanner.Scan()
	if success == false {
		err = scanner.Err()
		if err == nil {
			log.Println("Scan completed and reached EOF")
		} else {
			log.Fatal(err)
		}
	}
	fmt.Println("First word found:", scanner.Text())

}
