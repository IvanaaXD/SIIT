n = int(input("Unesite broj koji se ponavlja: "))
a = int(input("Unesite koliko puta se ponavlja taj broj: "))

lista = []


def ponovi(n, a):
    if a != 0:
        lista.append(n)
        a -= 1
        ponovi(n, a)
    return lista


niz = ponovi(n, a)
print(niz)
