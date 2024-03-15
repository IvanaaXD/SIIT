import numpy as np
import matplotlib as plt
import pandas as pd


def zadatak1(x, y):

    if len(x) != len(y):
        raise ValueError('Vektori nisu istih duzina')
    p = np.polyfit(x, y, 1)
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

p = zadatak1(x_array, y_array)
print(p)
