import numpy as np

x = np.array([0.32, 0.4, 0.2, 0.6, 0.52, 0.17])
y = np.array([0.68, 0.7, 0.43, 1.1, 0.98, 0.35])

p = np.polyfit(x, y, 1)
print(p)
