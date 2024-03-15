import os
from datetime import datetime

from rezultati import ucitaj_rezultate_iz_fajla, kreiraj_rezultat, sacuvaj_rezultat

menu_opcije = {
    1: "Dodavanje rezultata",
    2: "Prikaz rezultata",
    3: "Nazad (pritisnite x ili X)",
}


def clear():
    os.system('cls')


def print_menu():
    for kljuc in menu_opcije.keys():
        print(kljuc, "--", menu_opcije[kljuc])


def jej():
    povratak = input("Pritisnite bilo koji taster za dalje: ")

    if povratak:
        menu()

    if povratak == "":
        menu()


def opcija1():
    clear()

    print("-" * 100)
    wuhu = "UPIS REZULTATA"
    print(f"{wuhu:^100}")
    print("-" * 100)

    ime_domacina = input("Unesite ime domaćina: ")
    bodovi_d = input("Unesite broj bodova domaćina: ")
    ime_gosta = input("Unesite ime gosta: ")
    bodovi_g = input("Unesite broj bodova gosta: ")
    datum = input("Unesite datum održavanja utakmice: ")
    datum = datetime.strptime(datum, '%d.%m.%Y')
    datum = datum.date()

    try:
        rezultati = ucitaj_rezultate_iz_fajla("rezultati.csv", "|")
        svi_rezultati = kreiraj_rezultat(rezultati, ime_domacina, int(bodovi_d), ime_gosta, int(bodovi_g), datum)
        sacuvaj_rezultat("rezultati.csv", "|", svi_rezultati)

        print("-" * 100)
        print("Rezultat uspješno dodat!")
        menu()

    except Exception as greska:
        print("-" * 100)
        print(greska)
        print("Pokušajte ponovo!")
        opcija1()


def opcija2():
    rezultati = ucitaj_rezultate_iz_fajla("rezultati.csv", "|")
    drama(rezultati)
    jej()


def drama(rezultati: iter):
    svi_rezultati = ucitaj_rezultate_iz_fajla("rezultati.csv", "|")

    clear()
    print('-' * 100)
    we = "Lista rezultata"
    print(f"{we:^100}")
    print('-' * 100)

    if type(rezultati) == dict:
        rezultati = list(rezultati.values())

    for rezultat in rezultati:
        domacin = rezultat['ime_domacina']
        gost = rezultat['ime_gosta']

        bodovi_domacin = str(rezultat['bodovi_d'])
        bodovi_gost = str(rezultat['bodovi_g'])

        print(f"{domacin:>20}"f"{bodovi_domacin:^3}"f"{bodovi_gost:^3}"f"{gost:<20}\n")

    print('-' * 100)


def menu():
    clear()

    print("-" * 100)
    dramica = "Dobro došli!"
    print(f"{dramica:^100}")
    print("-" * 100)

    print_menu()
    print("-" * 100)
    opcija = input("Unesite broj željene opcije: ")

    if opcija == "1":
        opcija1()
    elif opcija == "2":
        opcija2()
    elif opcija == "x" or opcija == "X":
        print("-" * 100)
        print("Hvala što ste koristili našu aplikaciju!")
        print("-" * 100)
        exit()
    else:
        print("Opcija ne postoji. Molim Vas unesite broj između 1 i 2 ili X/x.")
        menu()


if __name__ == '__main__':
    menu()
