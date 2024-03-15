import math


def hash_modul(id, B):
    return id % B


def central_digits(id, B, p, v=10):
    # mora uvek 4 cifre u broju a to se postize dodavanjem p^2 - duzina broja nula ispred
    id_sqr_str = '0' * (p * 2 - len(str(id ** 2))) + str(id ** 2)
    n = math.ceil(math.log(B, v))  # uvek isto racunanje
    t = math.floor(p - n/2)
    T_str = ""
    for i in range(n):
        T_str += id_sqr_str[t + i]

    return 1 + math.floor(B / (v ** n) * int(T_str))


def digit_overlap(id, B, p, v=10):
    id_str = str(id)[::-1]  # obrcemo string
    n = math.ceil(math.log(B, v))  # uvek isto racunanje
    q = math.floor(p / n*2)
    T = 0

    for k in range(0, q + 1):
        for i in range(n):
            r = 2 * k * n + i

            if r < p:
                T += int(id_str[r]) * (v ** i)

    for k in range(1, q + 1):
        for i in range(n):
            s = 2 * k * n - i - 1

            if s < p:
                T += int(id_str[s]) * (v ** i)

    T %= v ** n

    return 1 + math.floor(B / (v ** n) * T)


if __name__ == "__main__":
    input_nums_central = [34, 7, 3, 15, 19, 29, 64, 43, 23]
    output_nums_central = [4, 1, 1, 5, 8, 17, 2, 17, 11]

    input_nums_overlap = [341201, 237896, 465210, 542812, 191378, 296532, 641000, 430025, 231258]
    output_nums_overlap = [12, 2, 17, 10, 6, 4, 14, 14, 1]

    for input, correct in zip(input_nums_central, output_nums_central):
        if central_digits(input, 20, 2) != correct:
            print("Central digits wrong")

    for input, correct in zip(input_nums_overlap, output_nums_overlap):
        if digit_overlap(input, 20, 6) != correct:
            print("Overlap wrong")

    # p - broj cifara id-ja
    # B - broj baketa
    # v - osnova za brojni sistem, uvek 10
