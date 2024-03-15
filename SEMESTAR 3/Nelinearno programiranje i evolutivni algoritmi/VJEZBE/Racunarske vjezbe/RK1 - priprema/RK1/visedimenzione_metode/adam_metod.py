import numpy as np


def funkcija(x):
    # f(x,y) = x^2 + y^2
    return x[0]**2 + x[1]**2


def gradijent(x):
    x = np.array(x).reshape(np.size(x))
    return np.asarray([[2*x[0]], [2*x[1]]])


def am(grad_f, x0, gamma, epsilon1, epsilon, omega1, omega2, N):

    x = np.array(x0).reshape(len(x0), 1)
    v = 1
    m = 1

    for i in range(N):

        g = grad_f(x)
        m = m * omega1 + (1 - omega1) * g
        v = v * omega2 + (1 - omega2) * np.multiply(g, g)

        hat_v = abs(v / (1 - omega2))
        hat_m = m / (1 - omega1)

        x = x - (gamma * hat_m) / np.sqrt(hat_v + epsilon1)

        if np.linalg.norm(g) < epsilon:
            break

    return x


optimum = am(gradijent, x0=[-1, 1], gamma=0.1,omega1=0.1, omega2=0.9, epsilon1=1e-8, epsilon=1e-4, N=100)
vrednost_funkcije = funkcija(optimum)
print("Optimum funkcije se nalazi u tacki",optimum,",vrednost funkcije u toj tacki iznosi ", vrednost_funkcije)
