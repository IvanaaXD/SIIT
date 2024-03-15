from datetime import date, datetime

sifra = 0


def kreiraj_rezultat(svi_rezultati: dict, ime_domacina: str, bodovi_d: int, ime_gosta: str, bodovi_g: int, datum: date):

    global sifra

    if not isinstance(ime_domacina, str) or ime_domacina == "":
        raise Exception("Ime domaćina nije dobro unijeto!")

    if not isinstance(ime_gosta, str) or ime_gosta == "":
        raise Exception("Ime gosta nije dobro unijeto!")

    if bodovi_d <= 0 or bodovi_g <= 0:
        raise Exception("Bodovi gosta ili domaćina nisu dobro unijeti!")

    rezultat = {}

    rezultat['id'] = sifra
    rezultat['ime_domacina'] = ime_domacina
    rezultat['bodovi_d'] = bodovi_d
    rezultat['ime_gosta'] = ime_gosta
    rezultat['bodovi_g'] = bodovi_g
    rezultat['datum'] = datum

    svi_rezultati[sifra] = rezultat

    return svi_rezultati


def sacuvaj_rezultat(putanja: str, separator: str, svi_rezultati: dict):
    with open(putanja, "w") as f:
        for sifra in svi_rezultati:
            rezultat = svi_rezultati[sifra]
            wuhu = datetime.strftime(rezultat['datum'], '%d.%m.%Y')
            string = "id:" + str(rezultat['id']) + separator + "ime_domacina:" + rezultat['ime_domacina']
            string += separator + "bodovi_d:" + str(rezultat['bodovi_d']) + separator + "ime_gosta:" + rezultat['ime_gosta'] + separator
            string += "bodovi_g:" + str(rezultat['bodovi_g']) + separator + "datum:" + wuhu + separator
            f.write(f"{string}\n")

    return None


def ucitaj_rezultate_iz_fajla(putanja: str, separator: str) -> dict:
    global sifra
    svi_rezultati = {}
    with open(putanja, "r") as f:
        for linija in f:
            rezultat = {}
            parametri = linija.split(separator)[:-1]
            for parametar in parametri:
                dio = parametar.split(":")
                if dio[0] == 'datum':
                    rezultat[dio[0]] = datetime.strptime(dio[1], '%d.%m.%Y')
                elif dio[0] == 'bodovi_d' or dio[0] == 'bodovi_g':
                    rezultat[dio[0]] = int(dio[1])
                else:
                    rezultat[dio[0]] = dio[1]
                svi_rezultati[rezultat["id"]] = rezultat
            sifra += 1

    return svi_rezultati
