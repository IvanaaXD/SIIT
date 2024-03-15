package vjezbanje

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func writeFile1() {
	file, err := os.OpenFile("somefile", os.O_WRONLY, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	byteSlice := []byte("Bytes!\n")
	bytesWritten, err := file.Write(byteSlice)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(bytesWritten)
}

func fileWrite2(filePath string) {

	file, err := os.Open(filePath)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	bufferedWriter := bufio.NewWriter(file)
	bytesWritten, err := bufferedWriter.Write([]byte{65, 66, 67})
	bytesWritten, err = bufferedWriter.WriteString("Buffered string\n")
	bufferedWriter.Flush()
	bufferedWriter.Reset(bufferedWriter)
	bufferedWriter = bufio.NewWriterSize(file, 8000)

	fmt.Println(bytesWritten)
}
