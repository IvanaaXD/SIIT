import numpy as np


def funkcija(x):
    # f(x,y) = x^2 + y^2
    return x[0] ** 2 + x[1] ** 2


def gradijent_func(x):
    x = np.array(x).reshape(np.size(x))
    return np.asarray([[2 * x[0]], [2 * x[1]]])


def mnpsm(gradijent, x0, gamma, epsilon, N, omega):

    x = np.array(x0).reshape(len(x0), 1)
    v = 0

    for i in range(N):

        g = gradijent(x)
        v = omega * v + gamma * g
        x = x - v

        if np.linalg.norm(g) < epsilon:
            break

    return x


optimum = mnpsm(gradijent_func, x0=[-2, 2], gamma=0.15, epsilon=1e-4, omega=0.1, N=100)
vrednost_funkcije = funkcija(optimum)
print("Optimum funkcije se nalazi u tacki", optimum, ",vrednost funkcije u toj tacki iznosi ", vrednost_funkcije)
