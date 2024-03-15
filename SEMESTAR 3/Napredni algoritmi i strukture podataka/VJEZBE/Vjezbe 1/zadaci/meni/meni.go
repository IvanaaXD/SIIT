package main

import (
	"fmt"
	"zadaci"
)

func main() {

	var v string

	v = zadaci.Provjera1(5)
	fmt.Println(v)

	v = zadaci.Provjera2(-7)
	fmt.Println(v)

	f, err := zadaci.NtiBroj(4)
	if err != nil {
		fmt.Println("Error")
	} else {
		fmt.Printf("%d-ti prost broj je: %d\n", 4, f)
	}

	zadaci.Mapica()

	s := zadaci.NewStudent("Ivana", "Radovanovic", "SV 25/2022")
	s.IntroduceYourself()

	s.ChangeIndex("SV 23/2022")
	s.IntroduceYourself()

	number := 153
	if zadaci.JesteArmstrongovBroj(number) {
		fmt.Printf("%d je Armstrongov broj.\n", number)
	} else {
		fmt.Printf("%d nije Armstrongov broj.\n", number)
	}

	word := "listen"
	candidates := []string{"enlists", "google", "inlets", "banana"}

	anagrams := zadaci.PronadjiAnagrame(word, candidates)

	for anagram := range anagrams {
		fmt.Println(anagrams[anagram])
	}
}
