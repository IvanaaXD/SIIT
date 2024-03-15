package main

import (
	"bufio"
	"fmt"
	"io"
	"log"
	"os"
	"strings"
)

func readAndPrint(filePath string) {

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

func createFile(sourcePath, destinationPath string) {

	sourceFile, err := os.Create(sourcePath)
	if err != nil {
		log.Fatal(err)
	}
	defer sourceFile.Close()

	writer := bufio.NewWriter(sourceFile)
	_, err = writer.WriteString("Hello from the source file!")
	if err != nil {
		log.Fatal(err)
	}

	if err = writer.Flush(); err != nil {
		log.Fatal(err)
	}

	destinationFile, err := os.Create(destinationPath)
	if err != nil {
		log.Fatal(err)
	}

	_, err = sourceFile.Seek(0, 0)
	if err != nil {
		log.Fatal(err)
	}

	_, err = io.Copy(destinationFile, sourceFile)
	if err != nil {
		log.Fatal(err)
	}

	err = destinationFile.Sync()
	if err != nil {
		log.Fatal(err)
	}

	sourceFile.Close()
	destinationFile.Close()

	err = os.Remove(sourcePath)
	if err != nil {
		log.Fatal(err)
	}
}

func nmByte(n, m int, filePath string) {

	file, err := os.Open(filePath)
	if err != nil {
		log.Fatal(err)
	}

	defer file.Close()

	bufferM := make([]byte, 1)
	_, err = file.ReadAt(bufferM, int64(m-1)) // m-1 jer indeksi kreću od 0
	if err != nil {
		fmt.Println("Greška pri čitanju m-tog bajta:", err)
		return
	}

	bufferN := make([]byte, 1)
	_, err = file.ReadAt(bufferN, int64(n-1)) // n-1 jer indeksi kreću od 0
	if err != nil {
		fmt.Println("Greška pri čitanju n-tog bajta:", err)
		return
	}

	brojM := int(bufferM[0])
	brojN := int(bufferN[0])
	zbir := brojM + brojN

	fmt.Printf("Vrednost m-tog bajta: %d\n", brojM)
	fmt.Printf("Vrednost n-tog bajta: %d\n", brojN)
	fmt.Printf("Zbir m-tog i n-tog bajta: %d\n", zbir)
}

func changeByte(filePath string, vrednosti []byte) {

	file, err := os.OpenFile(filePath, os.O_RDWR, 0644)
	if err != nil {
		log.Fatal(err)
	}

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

	file.Close()
}

func changeEveryThirdByte(filePath string) {

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

func main() {
	sourceFilePath := "source.txt"
	destinationFilePath := "destination.txt"
	createFile(sourceFilePath, destinationFilePath)
	readAndPrint(destinationFilePath)

	n := 5
	m := 7
	nmByte(n, m, destinationFilePath)

	vrednosti := []byte{10, 21, 103, 15}
	changeByte(destinationFilePath, vrednosti)

	changeThirdByte(destinationFilePath)
}
