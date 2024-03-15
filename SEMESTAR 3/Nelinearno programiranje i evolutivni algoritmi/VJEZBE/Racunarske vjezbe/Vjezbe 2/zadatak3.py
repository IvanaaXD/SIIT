import numpy as np


def zadatak3(x, y):

    if len(x) != len(y):
        raise ValueError("Vektori nisu istih duzina")

    p1 = np.polyfit(x, y, 5)
    p2 = np.polyfit(x, y, 6)

    return p1, p2


x = input('Unesite x: ')
y = input('Unesite y: ')

x_splited = x.split(",")
y_splited = y.split(",")

x_array = []
y_array = []
for i in range(len(x_splited)):
    x_array.append(int(x_splited[i]))
    y_array.append(int(y_splited[i]))

[p1, p2] = zadatak3(x_array, y_array)
print(p1, p2)
