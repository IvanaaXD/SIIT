import matplotlib.pyplot as plt
import math
import numpy as np


def func(x):
    return x**2 - math.sin(2*x)+6


def dfunc(x):
    return 2*x - 2*math.cos(2*x)


def ddfunc(x):
    return 2 + 4*math.cos(2*x)


def nr(x0, epsilon):

    x_novo = x0
    x_prethodno = math.inf

    iteracija = 0

    while abs(x_novo - x_prethodno) > epsilon:

        x_prethodno = x_novo
        x_novo = x_prethodno - dfunc(x_prethodno)/ddfunc(x_prethodno)

        iteracija += 1

    x_optimalno = x_novo
    f_optimalno = func(x_optimalno)

    return x_optimalno, f_optimalno, iteracija

x0 = 0
tol = 0.01
x_optimalno, f_optimalno, iteracija = nr(x0, tol)

print(x_optimalno, f_optimalno, iteracija)

x = np.linspace(-1, 1, 1000)
y = np.linspace(0, 0, len(x))

for i in range(0, len(x)):

    y[i] = func(x[i])

pl = plt.plot(x, y)
plt.scatter(x_optimalno, f_optimalno)
plt.show()
