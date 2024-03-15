
import pandas as pd
from sklearn.model_selection import train_test_split
from utils_nans1 import *

alpha = 0.05

# ------------------------------**Zadatak 1.**------------------------------

df = pd.read_csv("data/train.csv")
df = df.dropna()

x = df.drop(columns=['plata'])
y = df['plata']

model = get_fitted_model(x, y)

df_test = pd.read_csv("data/test.csv")
x_test = df_test.drop(columns=['plata'])
y_test = df_test['plata']

rmse = get_rmse(model, x_test, y_test)
print("Mjera modela je: ", f'{rmse:.2f}')

# ------------------------------**Zadatak 2.**------------------------------

min_plata, max_plata = get_conf_interval(model, "zvanje", alpha)
print("Minimalno povecanje plate je: ", f'{min_plata:.2f}', "a maksimalno povecanje plate je: ", f'{max_plata:.2f}')

h, k = independence_of_errors_assumption(model, sm.add_constant(x), y, plot=False)

if h is None:
    print("Vrijednosti nisu zavisne")
else:
    print("Vrijednosti su zavisne")

# ------------------------------**Zadatak 3.**------------------------------

df = pd.read_csv("data/train.csv")
df['zvanje'] = df['zvanje'].interpolate(method='spline', order=3, limit_direction='both')
df['godina_doktor'] = df['godina_doktor'].interpolate(method='linear', limit_direction='both')

# df['godina_doktor'] = df['godina_doktor'].interpolate(method='spline', order=3, limit_direction='both')
# df['zvanje'] = df['zvanje'].interpolate(method='linear', limit_direction='both')

df = df.drop(columns=['pol Muski', 'pol Zenski'])

x = df.drop(columns=['plata'])
y = df['plata']
x_train, x_val, y_train, y_val = train_test_split(x, y, train_size=0.8, shuffle=True, random_state=42)

model = get_fitted_model(x_train, y_train)
print(are_assumptions_satisfied(model, x_train, y_train))

val_rmse = get_rmse(model, x_val, y_val)
print("Val rmse: ", f'{val_rmse:.2f}')

df_test = pd.read_csv("data/test.csv")
x_test = df_test.drop(columns=['plata', 'pol Muski', 'pol Zenski'])
y_test = df_test['plata']

test_rmse = get_rmse(model, x_test, y_test)
print("Test rmse: ", f'{test_rmse:.2f}')

# ------------------------------**Zadatak 4.**------------------------------

print(model.summary())

# kako je Prob (F-statistic) = 2.52e-30, to je jako mali broj, manji od 5%,
# pa sigurno postoji promjenljiva koja zavisi od y

# ------------------------------**Zadatak 5.**------------------------------

# Koeficijent determinacije - r^2 - je način da izmerimo kvalitet modela.
# Uzima vrednosti u opsegu [0, 1]. Što je r^2 bliže 1, to je model bolji;
# što je dalje od 1, to je model gori.
# ssr/sst
