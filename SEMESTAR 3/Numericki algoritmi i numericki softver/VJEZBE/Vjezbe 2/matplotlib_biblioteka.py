import matplotlib.pyplot as plt
import numpy as np

# y = np.ones(2) * 5
# x = np.linspace(0, 10, 2)

# plt.plot(x, y, "o/x")  # tacka, - moze za liniju

x = np.linspace(0, 10, 100)

y = np.sin(x)
yy = np.cos(x)

plt.plot(x, yy)

plt.plot(x, y)  # linija
plt.show()