import matplotlib.pyplot as plt
import math
import numpy as np


def func(x):
    return x**2 - math.sin(2*x)+6


def fibonacijev_broj(n):

    if n == 1 or n == 2:
        return 1

    f = 1
    ff = 1

    for i in range(3, n+1):

        f, ff = ff, f+ff

    return ff


def fm(a, b, epsilon):

    n = 1

    while (b - a)/epsilon > fibonacijev_broj(n):
        n += 1

    x1 = a + fibonacijev_broj(n - 2) * (b - a) / fibonacijev_broj(n)
    x2 = a + b - x1

    for i in range(2, n+1):

        # minimum, maksimum ide obrnute strelice samo, ostalo isto
        if func(x1) <= func(x2):
            b = x2
        else:
            a = x1

        x1 = a + fibonacijev_broj(n-2)/fibonacijev_broj(n)*(b-a)
        x2 = a + b - x1

    if func(x1) < func(x2):
        x_optimalno = x1
    else:
        x_optimalno = x2

    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, n


[x_opt, f_opt, iter] = fm(a=-1, b=2, epsilon=0.1)
print(x_opt, f_opt, iter, fibonacijev_broj(iter))
