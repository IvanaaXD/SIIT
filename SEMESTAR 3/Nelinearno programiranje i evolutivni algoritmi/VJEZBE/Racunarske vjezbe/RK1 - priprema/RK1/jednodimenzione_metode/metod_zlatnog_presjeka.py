import math

import numpy as np


def func(x):
    f = x ** 2 * math.e ** x + 2
    return f


def mzp(a, b, epsilon):

    c = (3 - np.sqrt(5)) / 2

    x1 = a + c * (b - a)
    x2 = a + b - x1
    iteracija = 1

    while (b - a) > epsilon:

        if func(x1) <= func(x2):
            b = x2
        else:
            a = x1

        iteracija += 1

        x1 = a + c * (b - a)
        x2 = a + b - x1

    if func(x1) < func(x2):
        x_optimalno = x1
    else:
        x_optimalno = x2

    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija


[x_opt, f_opt, iter] = mzp(a=-1, b=2, epsilon=0.1)
print(x_opt, f_opt, iter)

