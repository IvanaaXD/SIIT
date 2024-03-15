import numpy as np
import statistics as stat


def zadatak2(x, y):

    p1 = np.polyfit(x, y, 3)
    p2 = np.polyfit(x, y, 4)

    y1 = np.polyval(p1, x)
    y2 = np.polyval(p2, x)

    abs1 = stat.mean(abs(y - y1))
    abs2 = stat.mean(abs(y - y2))

    if abs1 < abs2:
        p = p1
    else:
        p = p2
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

p = zadatak2(x_array, y_array)
print(p)
