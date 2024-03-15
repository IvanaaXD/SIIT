package zadaci

import "fmt"

type Student struct {
	Ime         string
	Prezime     string
	BrojIndeksa string
}

func NewStudent(ime, prezime, brIndeksa string) Student {

	return Student{
		Ime:         ime,
		Prezime:     prezime,
		BrojIndeksa: brIndeksa,
	}
}

func (s *Student) ChangeIndex(newBrIndeksa string) {

	s.BrojIndeksa = newBrIndeksa

}

func (s *Student) IntroduceYourself() {

	fmt.Println(s.Ime + " " + s.Prezime + " " + s.BrojIndeksa)
}
