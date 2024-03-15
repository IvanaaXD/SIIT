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

    plt.title('skup sa nedostajucim vrednostima')
    plt.show()

    # -----------------------------------------------------------

    nan_months = df[df.isna().values]
    print(nan_months)

    first_nan_month = nan_months.index[0]
    nan_months_train_df = df.loc[:first_nan_month].iloc[:-1]

    nan_months_train_df['log10(Passengers)'] = np.log10(nan_months_train_df['Passengers'])
    nan_months_train_df['stationary_data'] = nan_months_train_df['log10(Passengers)'].diff()

    is_stationary, _ = is_data_stationary(nan_months_train_df['stationary_data'].dropna())
    if is_stationary:
        print('postoji stacionarnost')
    else:
        print('ne postoji stacionarnost')

    plot_pacf(nan_months_train_df['stationary_data'].dropna(), lags=15, method='ols')
    plt.show()

    p, d, q = 12, 1, 0
    ar = ARIMA(nan_months_train_df['log10(Passengers)'], order=(p, d, q)).fit()
    nan_months_pred = ar.predict(start=nan_months.index[0], end=nan_months.index[-1])
    nan_months_pred = np.power(10, nan_months_pred)

    plt.plot(df['Passengers'], color='b', linewidth=2, label='train', alpha=0.3)
    plt.plot(nan_months_pred, color='red', linewidth=2, label='ar model', alpha=0.3)
    plt.title("Predicted based on AR model")
    plt.legend()
    plt.show()

    df['Passengers'].loc[nan_months.index] = nan_months_pred

    # -----------------------------------------------------------

    dataset = int(len(df['Passengers']) * 0.8)
    train_df = df[:dataset].copy()
    test_df = df[dataset:].copy()

    train_df['log10(Passengers)'] = np.log10(train_df['Passengers'])
    train_df['stationary_data'] = train_df['log10(Passengers)'].diff().diff()

    is_stationary, _ = is_data_stationary(train_df['stationary_data'].dropna())
    if is_stationary:
        print('postoji stacionarnost')
    else:
        print('ne postoji stacionarnost')

    plot_pacf(train_df['stationary_data'].dropna(), lags=15, method='ols')
    plt.show()

    plot_acf(train_df['stationary_data'].dropna(), lags=15)
    plt.show()

    p, d, q = 11, 2, 1

    arima = ARIMA(train_df['log10(Passengers)'], order=(p, d, q)).fit()
    y_pred = arima.predict(start=test_df.index[0], end=test_df.index[-1])
    y_pred = np.power(10, y_pred)

    plt.plot(train_df['Passengers'], color='b', linewidth=2, label='train', alpha=0.3)
    plt.plot(test_df['Passengers'], color='red', linewidth=2, label='arima model', alpha=0.3)
    plt.plot(y_pred, color='k', label='ARIMA model prediction')
    plt.title("Predicted based on ARIMA model")
    plt.legend()
    plt.show()
    