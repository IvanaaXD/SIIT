# TODO 4: implementirati primenu jednostavne linearne regresije
# nad podacima iz datoteke "data/skincancer.csv", koristeći scikit-learn bibiloteku.

import pandas as pd
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

file_path = '../data/skincancer.csv'
data = pd.read_csv(file_path)

X = data['Lat'].values.reshape(-1, 1)
Y = data['Mort']

model = LinearRegression()
model.fit(X, Y)

slope = model.coef_[0]
intercept = model.intercept_

y_pred = model.predict(X)

plt.scatter(X, Y, label='Actual Data')
plt.plot(X, y_pred, color='blue', label='Regression Line')
plt.title(f'slope: {slope:.2f}, intercept: {intercept:.2f}')
plt.xlabel('Latitude')
plt.ylabel('Mortality')
plt.legend()
plt.show()
