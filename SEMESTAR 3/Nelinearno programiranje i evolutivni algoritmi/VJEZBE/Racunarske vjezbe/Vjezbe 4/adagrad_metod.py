import numpy as np


def funkcija(x):
    # f(x,y) = x^2 + y^2
    return x[0]**2 + x[1]**2


def gradijent_func(x):
    x = np.array(x).reshape(np.size(x))
    return np.asarray([[2*x[0]], [2*x[1]]])


def am(gradijent, gamma, x0, epsilon1, epsilon, N):

    x = np.array(x0).reshape(len(x0), 1)
    G = 0

    for i in range(N):

        g = gradijent(x)
        G = G + np.multiply(g, g)
        x = x - (gamma * g)/np.sqrt(G + epsilon1)

        if np.linalg.norm(g) < epsilon:
            break

    return x


optimum = am(gradijent_func, x0=[-1, 1], gamma=0.3, epsilon1=1e-8, epsilon=1e-4, N=100)
vrednost_funkcije = funkcija(optimum)
print("Optimum funkcije se nalazi u tacki",optimum,",vrednost funkcije u toj tacki iznosi ", vrednost_funkcije)

