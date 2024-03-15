import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import numpy as np
import statsmodels.api as sm
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima.model import ARIMA
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

from utils import is_data_stationary

import warnings

warnings.filterwarnings("ignore")

matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

if __name__ == '__main__':

    df = pd.read_csv("data/lowcost-airline-passengers.csv", sep=',')
    df['Month'] = pd.to_datetime(df['Month'])
    df = df.set_index('Month')
    df = df.sort_index()

    df.plot()
    plt.title("Nedostajuce vrijednosti")
    plt.show()

    nan_months = df[df.isna().values]
    print(nan_months)
    first_nan_month = nan_months.index[0]
    nan_months_train_df = df.loc[:first_nan_month].iloc[:-1]

    nan_months_train_df['log10(Passengers)'] = np.log10(nan_months_train_df['Passengers'])
    nan_months_train_df['stationary_data'] = nan_months_train_df['log10(Passengers)'].diff()

    is_stationary, _ = is_data_stationary(nan_months_train_df['stationary_data'].dropna())
    if is_stationary:
        print('postoji i stacionarnost')
    else:
        print('ne postoji stacionarnost')

    plot_pacf(nan_months_train_df['stationary_data'].dropna(), lags=15, method='ols')
    plt.show()

    p, d, q = 12, 1, 0
    nan_months_ar_model = ARIMA(nan_months_train_df['log10(Passengers)'], order=(p, d, q)).fit()
    nan_months_pred = nan_months_ar_model.predict(start=nan_months.index[0], end=nan_months.index[-1])
    nan_months_pred = np.power(10, nan_months_pred)

    plt.plot(df['Passengers'], color='b', linewidth=4, alpha=0.3, label='train')
    plt.plot(nan_months_pred, color='darkorange', label='AR nan months model prediction')
    plt.title('predikcije za nedostajuce vrednosti')
    plt.legend()
    plt.show()

    # nedomestimo nedostajute vrednosti
    df['Passengers'].loc[nan_months.index] = nan_months_pred

    dataset_split = int(len(df['Passengers']) * 0.8)
    train_df = df[:dataset_split].copy()
    test_df = df[dataset_split:].copy()

    # logaritmovanje i 2x diferenciranje nas dovodio do stacionarnosti
    train_df['log10(Passengers)'] = np.log10(train_df['Passengers'])
    train_df['stationary_data'] = train_df['log10(Passengers)'].diff().diff()

    is_stationary, _ = is_data_stationary(train_df['stationary_data'].dropna())
    if is_stationary:
        print('postoji stacionarnost')
    else:
        print('ne postoji stacionarnost')

    # gledamo grafik da nadjemo odgovarjuci red modela (p i q vrednost)
    plot_pacf(train_df['stationary_data'].dropna(), lags=15, method='ols')
    plt.show()
    plot_acf(train_df['stationary_data'].dropna(), lags=15)
    plt.show()

    # # treniramo model
    p, d, q = 11, 2, 1
    arima_model = ARIMA(train_df['log10(Passengers)'], order=(p, d, q)).fit()
    y_pred_arima = arima_model.predict(start=test_df.index[0], end=test_df.index[-1])
    y_pred_arima = np.power(10, y_pred_arima)

    # evaluiramo
    actual = test_df['Passengers']
    mse = mean_squared_error(actual, y_pred_arima)
    print(f'{mse=:.2f}')

    # crtamo predikcije da se uverimo da je sve ok
    plt.plot(train_df['Passengers'], color='b', linewidth=4, alpha=0.3, label='train')
    plt.plot(test_df['Passengers'], color='mediumblue', linewidth=4, alpha=0.3, label='test')
    plt.plot(y_pred_arima, color='k', label='ARIMA model prediction')
    plt.title('predikcije za Passengers')
    plt.legend()
    plt.show()
