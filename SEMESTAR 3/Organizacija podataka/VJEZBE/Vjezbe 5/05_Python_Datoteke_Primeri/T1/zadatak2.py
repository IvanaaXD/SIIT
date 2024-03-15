import math

file = "auto.txt"

with open(file, 'r') as f:

    text = []
    while True:
        t = f.readline()

        if not t:
            break

        text.append(t)

    trazena_kubikaza = input("Unesite trazenu kubikazu vozila: ")

    nasla = []
    for t in text:
        niz = t.split(" ")

        if niz[1] <= trazena_kubikaza:
            nasla.append(niz)

    sorted_nasla = sorted(nasla, key=lambda x: int(x[2]), reverse=True)

    if sorted_nasla:
        highest_year_car = sorted_nasla[0]
        print("Najnovije vozilo sa trazenom kubikazom:", highest_year_car)
    else:
        print("Nema vozila sa trazenom kubikazom.")
