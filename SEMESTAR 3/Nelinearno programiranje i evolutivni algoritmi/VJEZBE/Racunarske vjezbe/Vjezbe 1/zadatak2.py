import numpy as np


def fibonaci(n):

    x = np.array([])

    a = 0
    b = 1

    x = np.append(x, a)
    x = np.append(x, b)

    for i in range(n - 2):
        
        a, b = b, a + b
        x = np.append(x, b)

    return x


if __name__ == '__main__':

    br = int(input("Unesite koliko prvih clanova Fibonacijevog niza zelite: "))

    y = fibonaci(br)
    print(y)
