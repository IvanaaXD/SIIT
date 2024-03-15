import matplotlib
import matplotlib.pyplot as plt
import seaborn as sb
import pandas as pd
import numpy as np
import statsmodels.api as sm
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.arima.model import ARIMA
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split

from utils import *

import warnings

warnings.filterwarnings("ignore")

matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

if __name__ == '__main__':

    df = pd.read_csv("data/responses.csv", sep=',')
    df = df.iloc[:, 0:31]
    df = df.dropna()

    # cols = df.select_dtypes(exclude=['int', 'float']).columns
    # print(cols)

    # df[<osobina>] = df[<osobina>].map({<najmanja logicna vrednost>: 0, <sledeca>: 1, ... <najveca>: max})
    # df = pd.get_dummies(df, columns=[<osobina1, osobina2...>], drop_first=True).astype(int)

    x = df.drop(columns=['Movies'])
    y = df['Movies']

    x_train, x_test, y_train, y_test = train_test_split(x, y, train_size=0.8, shuffle=True, random_state=42)

    sc = StandardScaler()
    x_train = sc.fit_transform(x_train)
    x_test = sc.transform(x_test)

    pca = PCA(n_components=5, random_state=42)
    principal_components = pca.fit_transform(x_train)

    plot_explained_variance(pca)
    plt.show()
    varijansa = sum(pca.explained_variance_ratio_) * 100
    print(varijansa)

    model = get_fitted_model(principal_components, y_train)
    print(model.summary())

    # sa grafika vidimo da x3 raste kako raste gledanost akcionih filmova
    # trilera itd, a iz modela vidimo da mu je koeficijent pozitivan, pa
    # to znaci da osobe koje vole da gledaju filmove vole da gledaju ove
    # vrste filmova
    plot_pc_loading(pca, 2, x.columns)

    # sa grafika vidimo da x5 zavisi od animiranih i bajkovitih filmova,
    # osobe koje ne vole da gledaju ove vrste filmova generalno ne vole
    # da gledaju filmove
    # ova komponenta najvise utice na to koliko osoba voli da gleda filmove
    plot_pc_loading(pca, 4, x.columns)

    test_principal_components = pca.transform(x_test)
    r = get_rsquared_adj(model, test_principal_components, y_test)
    print(r)
