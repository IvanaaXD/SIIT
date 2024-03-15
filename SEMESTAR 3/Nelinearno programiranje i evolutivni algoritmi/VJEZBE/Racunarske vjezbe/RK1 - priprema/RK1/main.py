
from jednodimenzione_metode.fibonacijev_metod import *
from jednodimenzione_metode.metod_zlatnog_presjeka import *
from jednodimenzione_metode.njutn_rapsonov_metod import *
from jednodimenzione_metode.metod_sjecice import *

# -------------------- ZADATAK 1 --------------------

[f_opt, x_opt, iter] = fm(a=-1, b=1, epsilon=0.0001)
print(x_opt, f_opt, iter)

# -------------------- ZADATAK 2 --------------------

[x_opt, f_opt, iter] = mzp(a=-1, b=1, epsilon=0.0001)
print(x_opt, f_opt, iter)

# -------------------- ZADATAK 3 --------------------

[x_opt, f_opt, iter, x_niz] = nrm(x0=10, epsilon=0.0001)
print(x_opt, f_opt, iter, x_niz)

# -------------------- ZADATAK 4 --------------------

[x_opt, f_opt, iter, x_niz] = ms(x0=10, x1=20, epsilon=0.0001)
print(x_opt, f_opt, iter, x_niz)
