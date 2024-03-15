import math

import numpy as np


def func(x):
    return 3*math.sin(4*x) + 6*math.cos(x)


def metod_zlatnog_presjeka(a, b, epsilon):

    c = (3 - math.sqrt(5)) / 2

    x1 = a + c * (b - a)
    x2 = a + b - x1

    iteracija = 0

    while (b - a) > epsilon:

        if func(x1) <= func(x2):
            b = x2
        else:
            a = x1

        x1 = a + c * (b - a)
        x2 = a + b - x1

        iteracija += 1

    if func(x1) < func(x2):
        x_optimalno = x1
    else:
        x_optimalno = x2

    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija


[x_optimalno, f_optimalno, i] = metod_zlatnog_presjeka(a=7, b=8, epsilon=0.01)
print(x_optimalno, f_optimalno)

print(i)
