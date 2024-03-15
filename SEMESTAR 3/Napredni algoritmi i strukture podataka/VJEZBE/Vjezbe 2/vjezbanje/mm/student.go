package mm

import "fmt"

type Student struct {
	ime     string
	Prezime string
}

func NewStudent(ime, prezime string) Student {
	return Student{
		ime:     ime,
		Prezime: prezime,
	}
}

func (s Student) PredstaviSe() {
	fmt.Println(s.ime + " " + s.Prezime)
}

func (s *Student) PromijeniIme(nime string) {
	s.ime = nime
}

type Osoba interface {
	PredstaviSe()
}
