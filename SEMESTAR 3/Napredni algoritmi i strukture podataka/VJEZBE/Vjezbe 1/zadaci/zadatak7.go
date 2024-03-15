package zadaci

import (
	"sort"
	"strings"
)

func PronadjiAnagrame(word string, candidates []string) []string {

	anagrami := []string{}

	for _, candidate := range candidates {

		if jesuAnagrami(word, candidate) {
			anagrami = append(anagrami, candidate)
		}
	}
	return anagrami
}

func jesuAnagrami(word, candidate string) bool {

	if len(word) != len(candidate) {
		return false
	}

	word = strings.ToLower(word)
	candidate = strings.ToLower(candidate)

	wordChars := strings.Split(word, "")
	candidateChars := strings.Split(candidate, "")

	sort.Strings(wordChars)
	sort.Strings(candidateChars)

	return strings.Join(wordChars, "") == strings.Join(candidateChars, "")
}
