
lista = [1, 2, 3, 4, 5, 6, 7, 8, 9]


def suma(lista):
    zbir = 0
    if len(lista) == 1:
        return lista[0]
    if len(lista) != 1:
        zbir += suma(lista[1:])
        return lista[0] + zbir


print(suma(lista))
