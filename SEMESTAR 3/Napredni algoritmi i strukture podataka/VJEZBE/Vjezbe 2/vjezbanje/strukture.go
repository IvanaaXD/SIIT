package vjezbanje

import (
	"vjezbanje/mm"
)

func main() {
	s := mm.NewStudent("Pera", "Peric")
	s.PredstaviSe()
	s.PromijeniIme("Mika")
	s.PredstaviSe()
}

func predstavi(o mm.Osoba) {
	o.PredstaviSe()
}
