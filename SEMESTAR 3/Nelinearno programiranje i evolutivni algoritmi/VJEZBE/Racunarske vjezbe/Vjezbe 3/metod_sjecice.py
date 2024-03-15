import matplotlib.pyplot as plt
import math
import numpy as np


def func(x):
    return x**2 - math.sin(2*x)+6


def dfunc(x):
    return 2*x - 2*math.cos(2*x)


def ms(x0, x1, epsilon):

    x_prethodno = x0
    x_novo = x1

    x_niz = [x_prethodno, x_novo]

    iteracija = 0

    while abs(x_novo - x_prethodno) > epsilon:

        x_preprethodno = x_prethodno
        x_prethodno = x_novo
        x_novo = x_prethodno - dfunc(x_prethodno) / (dfunc(x_prethodno) - dfunc(x_preprethodno)) * (
                    x_prethodno - x_preprethodno)

        x_niz.append(x_novo)
        iteracija += 1

    x_optimalno = x_novo
    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija, x_niz


[x_opt, f_opt, iter, x_niz] = ms(x0=-1, x1=0, epsilon=0.1)
print(x_opt, f_opt, iter, x_niz)
