import math

import numpy as np


def func(x):
    f = x ** 2 * math.e ** x + 2
    return f


def fivonacijev_broj(n):

    if n == 1 or n == 2:
        return 1
    else:
        p = 1
        pp = 1
        for i in range(3, n + 1):
            p, pp = pp, pp + p

    return pp


def fm(epsilon, a, b):

    n = 1

    while (b - a) / epsilon > fivonacijev_broj(n):
        n += 1

    x1 = a + fivonacijev_broj(n - 2) / fivonacijev_broj(n) * (b - a)
    x2 = a + b - x1

    for i in range(n):

        if func(x1) <= func(x2):
            b = x2
        else:
            a = x1

        x1 = a + fivonacijev_broj(n - 2) / fivonacijev_broj(n) * (b - a)
        x2 = a + b - x1

    if func(x1) < func(x2):
        x_optimalno = x1
    else:
        x_optimalno = x2

    f_optimalno = func(x_optimalno)

    return f_optimalno, x_optimalno, n



