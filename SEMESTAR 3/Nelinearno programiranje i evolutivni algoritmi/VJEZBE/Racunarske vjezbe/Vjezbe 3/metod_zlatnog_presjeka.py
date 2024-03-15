import matplotlib.pyplot as plt
import math
import numpy as np


def func(x):
    return x**2 - np.sin(2*x)


def mzp(a, b, epsilon):

    c = (3 - math.sqrt(5)) / 2

    x1 = a + c*(b - a)
    x2 = a + b - x1

    iteracija = 1

    # minimum
    while b - a > epsilon:

        iteracija += 1

        if func(x1) <= func(x2):
            b = x2
        else:
            a = x1

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
