lista = [10, 23, 3, 24, 5, 36, 17, 38, 9]
max = lista[0]


def trazi(lista):
    global max
    if len(lista) == 1:
        return lista[0]
    if len(lista[1:]) != 0:
        if lista[1] > max:
            max = lista[1]
        trazi(lista[1:])
        """if lista[1] <= max:
            trazi(lista[1:])"""
        return max


maksimum = trazi(lista)
print(maksimum)
