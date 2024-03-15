package zadaci

func Provjera1(a float32) string {

	s := ""
	if a < 0 {
		s = "Broj je manji od 0."
	} else if a > 0 {
		s = "Broj je veci od 0."
	} else {
		s = "Broj je jednak 0."
	}

	return s
}

func Provjera2(a float32) string {

	s := ""
	switch {
	case a < 0:
		s = "Broj je manji od 0."
	case a > 0:
		s = "Broj je veci od 0."
	default:
		s = "Broj je jednak 0."
	}

	return s
}
