package vjezbanje

import "os"

func openFile() {
	file, err := os.Open("somefile")

	if err != nil {
		panic(err)
	}

	file.Close()

	file, err = os.OpenFile("somefile", os.O_RDWR|os.O_CREATE, 0666)
}
