import numpy as np
from matplotlib import pyplot as plt


def func(x):
    return 2*x[0]**2 - 1.05*x[0]**4 + (x[0]**6)/6 + x[0] * x[1] + x[1]**2


def grad_func(x):
    dx = 4 * x[0] - 4 * 1.05 * x[0]**3 + x[0]**5 + x[1]
    dy = x[0] + 2 * x[1]
    return np.asarray([dx, dy])


def metod_najbrzeg_pada(a, b, x0, grad_f, gamma, epsilon, N):

    x = np.array(x0).reshape(len(x0), 1)

    for i in range(N):

        g = grad_f(x)
        x = x - gamma * g

        if np.linalg.norm(g) < epsilon:
            break

    return x


x = metod_najbrzeg_pada(a=-5, b=5, x0=[-0.5, 0.5], grad_f=grad_func, gamma=0.3, epsilon=0.001, N=1000)
f = func(x)

print(x, f)

x1v = np.arange(-5, 5, 0.1)
x2v = np.arange(-5, 5, 0.1)

x1, x2 = np.meshgrid(x1v, x2v)

y = func([x1, x2])

ax = plt.subplot(111, projection='3d')
p = ax.plot_surface(x1, x2, y)
plt.scatter(*x, f, color='red')
plt.show()

