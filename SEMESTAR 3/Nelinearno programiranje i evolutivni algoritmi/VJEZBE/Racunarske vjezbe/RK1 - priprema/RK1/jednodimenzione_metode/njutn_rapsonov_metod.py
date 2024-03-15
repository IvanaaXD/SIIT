import math

import numpy as np


def func(x):
    f = x ** 2 * math.e ** x + 2
    return f


def dfunc(x):
    f = 2 * x * math.e ** x + x ** 2 * math.e ** x
    return f


def ddfunc(x):
    f = 2 * math.e ** x + 4 * x * math.e ** x + x ** 2 * math.e ** x
    return f


def nrm(x0, epsilon):

    x_novo = x0
    x_pre = math.inf

    iteracija = 0
    x_niz = [x_novo]

    while abs(x_novo - x_pre) > epsilon:

        x_pre = x_novo
        x_novo = x_pre - dfunc(x_pre) / ddfunc(x_pre)

        iteracija += 1
        x_niz.append(x_novo)

    x_optimalno = x_novo
    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija, x_niz


[x_opt, f_opt, iter, x_niz] = nrm(x0=-0.5, epsilon=0.1)
print(x_opt, f_opt, iter, x_niz)
