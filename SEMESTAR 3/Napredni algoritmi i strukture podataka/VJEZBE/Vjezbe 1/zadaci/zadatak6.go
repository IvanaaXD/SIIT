package zadaci

import (
	"fmt"
	"math"
)

func JesteArmstrongovBroj(broj int) bool {

	brojStr := fmt.Sprint(broj)
	brojDigits := len(brojStr)
	sum := 0

	for _, char := range brojStr {
		digit := int(char - '0') // Pretvorite karakter u ceo broj
		sum += int(math.Pow(float64(digit), float64(brojDigits)))
	}

	return sum == broj
}
