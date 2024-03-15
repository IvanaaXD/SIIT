# TODO 3: implementirati primenu jednostavne linearne regresije
# nad podacima iz datoteke "data/skincancer.csv".
from linreg_simple import *
import pandas as pd

file_path = '../data/skincancer.csv'
data = pd.read_csv(file_path)

X = data['Lat']
Y = data['Mort']

slope, intercept = fit(X, Y)

y_pred = make_predictions(X, slope, intercept)

plt.plot(X, Y, '.')
plt.plot(X, y_pred, 'b')
plt.title(f'slope: {slope:.2f}, intercept: {intercept:.2f}')
plt.title(f'slope: {slope:.2f}, intercept: {intercept:.2f}')
plt.xlabel('Latitude')
plt.ylabel('Mortality')
plt.show()
