import numpy as np
import matplotlib.pyplot as plt

# %% Primer 1.a
x = np.array([1, 2, 3, 4, 5])
y = np.array([3, 6, 9, 12, 15])

p = np.polyfit(x, y, 1)
y_aproks = np.polyval(p, x)
plt.plot(x, y)
plt.show()

sila = np.polyval(p, 7)
print(sila)
