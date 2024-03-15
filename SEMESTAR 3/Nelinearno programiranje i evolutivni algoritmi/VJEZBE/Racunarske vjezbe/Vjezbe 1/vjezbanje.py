import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D


def primjer_1():

    x = np.array([0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5])
    y = np.array([1, 2, 3, 2, 2, 2, 1.5, 2, 2, 2.5])
    p1 = plt.plot(x, y)
    plt.show()


def primjer_2():

    x = np.arange(0, 2 * np.pi, 1)
    y = np.sin(x)
    p1 = plt.plot(x, y)
    plt.show()


def primjer_3():

    x1v = np.arange(-5, 5, 0.01)
    x2v = np.arange(-5, 5, 0.01)
    x1, x2 = np.meshgrid(x1v, x2v)
    f = np.sin(x1) + np.cos(x2)

    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')  # Use add_subplot instead of gca
    p1 = ax.plot_surface(x1, x2, f)
    plt.show()


if __name__ == '__main__':

    # primjer_1()
    # primjer_2()
    primjer_3()
