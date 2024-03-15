n = int(input("Unesite broj prirodnih brojeva: "))

lista = []
for i in range(n+1):
    lista.append(i)


def suma(lista):
    zbir = 0
    if len(lista) == 1:
        return lista[0]
    if len(lista) != 1:
        zbir += suma(lista[1:])
        return lista[0] + zbir


drama = suma(lista)
print("Trazeni zbir je: ", drama)
