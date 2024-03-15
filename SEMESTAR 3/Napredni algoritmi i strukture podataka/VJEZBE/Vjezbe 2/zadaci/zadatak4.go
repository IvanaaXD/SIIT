package main

import (
	"fmt"
	"log"
	"os"
)

func nmBytes(n, m int, filePath string) {

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
