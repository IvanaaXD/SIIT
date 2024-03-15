import math

import numpy as np


def func(x):
    f = x ** 2 * math.e ** x + 2
    return f


def dfunc(x):
    f = 2 * x * math.e ** x + x ** 2 * math.e ** x
    return f


def ms(x0, x1, epsilon):

    x_pre = x0
    x_novo = x1

    iteracija = 0
    x_niz = [x_pre, x_novo]

    while abs(x_pre - x_novo) > epsilon:

        x_prepre = x_pre
        x_pre = x_novo
        x_novo = x_pre - (x_pre - x_prepre) * dfunc(x_pre) / (dfunc(x_pre) - dfunc(x_prepre))

        iteracija += 1
        x_niz.append(x_novo)

    x_optimalno = x_novo
    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija, x_niz


[x_opt, f_opt, iter, x_niz] = ms(x0=-1, x1=0, epsilon=0.1)
print(x_opt, f_opt, iter, x_niz)