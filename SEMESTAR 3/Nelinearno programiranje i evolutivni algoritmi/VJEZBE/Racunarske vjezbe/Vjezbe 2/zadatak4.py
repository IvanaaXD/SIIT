import math

import numpy as np


def zadatak4(x, y):

    if len(x) != len(y):
        raise ValueError("Vektori nisu iste duzine")

    p = []
    red = 0

    greska = math.inf

    while greska > 0.1:
        red += 1
        p = np.polyfit(x, y, red)

        yp = np.polyval(p, x)
        greska = max(abs((y - yp) / y) * 100)

    return p


x = input('Unesite x: ')
y = input('Unesite y: ')

x_splited = x.split(",")
y_splited = y.split(",")

x_array = []
y_array = []
for i in range(len(x_splited)):
    x_array.append(int(x_splited[i]))
    y_array.append(int(y_splited[i]))

p = zadatak4(x_array, y_array)
print(p)
