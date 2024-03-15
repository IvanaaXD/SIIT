import matplotlib
import seaborn as sb
import pandas as pd
import matplotlib.pyplot as plt
from utils import *
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.model_selection import train_test_split
import warnings
warnings.filterwarnings("ignore")

matplotlib.rcParams['figure.figsize'] = (8, 4)
sb.set(font_scale=1.)

df = pd.read_csv('data/responses.csv')
df = df.iloc[:,:31] # muzika + filmovi
df = df.dropna() # izbacimo nedostajuce vrednosti

target_label = 'Movies'
x = df.drop(columns=[target_label])
y = df[target_label]

# pretprocesiranje
# posto nema kategorickih atributa, nema potrebe za enkodovanjem
# posto su svi podaci su u opsegu od 1 do 5, nema potrebe da pretprocesiramo podatke za PCA

# podelimo podatke u odnosu 80:20
x_train, x_test, y_train, y_test = train_test_split(x, y, train_size=0.8, random_state=42, shuffle=True)

# enkodovati kategorijalne varijable ako ima potrebe za tim
sc = StandardScaler() # pravimo Standard scaler

# StandardScaler fitujemo na trening podacima i transformišemo trening podatke (funkcija fit_transform radi obe stvari)
x_train = sc.fit_transform(x_train)

# koristimo StandardScaler koji je fitovan na trening podacima da bi samo transformisali testne podatke (ne radimo fit_transform već samo transform)
x_test = sc.transform(x_test)

# pravimo PCA model. Sa 5 komponenti je zadrzano ~50% varijanse.
pca_model = PCA(n_components=5, random_state=42)
principal_components = pca_model.fit_transform(x_train)
print(f'ukupna varijansa: {sum(pca_model.explained_variance_ratio_) * 100:.1f}%')

# pravino regresioni model
model = get_fitted_model(principal_components, y_train)
print(model.summary())
plot_explained_variance(pca_model)

# # prijavijujemo meru koju model ostvaruje
test_principal_components = pca_model.transform(x_test)
test_adj_rsquares = get_rsquared_adj(model, test_principal_components, y_test)
print(f'test adj r^2={test_adj_rsquares:.2f}')

# tumacimo 2 najznacajnija atributa (vidimo iz summary tabele da su to x5 i x3)
# PC5 se odnosi na bajkovite i animirane filmove.
# Osobe koje ne vole da gledaju animirane i bajkovite filmove, generalno ne vole da gledaju filmove
# Ova komponenta najvise utice na to koliko osoba voli da gleda filmove. 
plot_pc_loading(pca_model, 4, columns=x.columns)
plt.show()

# PC3 se odnosi na 'adrenalinske' filmove 1 muziku (rep, tehno, akcione filmove, horor filmov
# Osobe koje vole 'adrenalinske' filmove 1 muziku, generalno vole da gledaju filmove.
plot_pc_loading(pca_model, 2, columns=x.columns)
plt.show()
