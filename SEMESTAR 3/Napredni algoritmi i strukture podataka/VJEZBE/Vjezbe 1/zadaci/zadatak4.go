package zadaci

import "fmt"

func Mapica() {

	mapa := make(map[string]int)

	mapa["Beograd"] = 11000
	mapa["Novi Sad"] = 21000
	mapa["Trebinje"] = 89101
	mapa["Gacko"] = 89247

	for i := range mapa {
		fmt.Println(i)
	}
}
