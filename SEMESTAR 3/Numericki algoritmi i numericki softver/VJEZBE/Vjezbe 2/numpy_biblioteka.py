import numpy as np

"""niz = np.array([1, 2, 3, 4, 5], dtype=float)
print(niz, type(niz))"""

niz = np.array([[1, 2, 3],
                [4, 5, 6],
                [7, 8, 9]])

nizz = niz + 2

"""print(niz)
print(niz.dtype)"""

print(nizz)

nizzz = np.arange(0, 100, 1)  # 1 je korak
print(nizzz)

niz4 = np.linspace(0, 3.14, 100)  # 100 je koliko brojeva
print(niz4)

niz6 = np.ones((2, 3))
print(niz6)

niz7 = np.zeros((2, 3)).T  # transponovana matrica
print(niz7)

niz8 = np.array([[1, 2, 3],
                 [4, 5, 6],
                 [7, 8, 9]])

print(niz[0:2])
print(niz[0][0])
print(niz[0, 0])

print(niz.T[0])
print(niz[:, 0])  # svi elementi iz nulte kolone
print(niz[:, 0:2])  # svi elementi iz nulte i prve kolone

niz9 = [10, 20, 30]
niz8[[0, 1, 2], [0, 1, 2]] = niz9[2]

print(niz8[[0, 1, 2], [0, 1, 2]])

niz10 = np.copy(niz8)  # deep copy
niz8[0, 0] = -1
print(niz10)

niz11 = np.ones((3, 5))
print(niz8 @ niz11)  # mnozenje matrica, puta ce proci samo ako je ista dimenzija matrice
print(np.dot(niz8, niz11))
print(niz.shape)  # dimenzije matrice
