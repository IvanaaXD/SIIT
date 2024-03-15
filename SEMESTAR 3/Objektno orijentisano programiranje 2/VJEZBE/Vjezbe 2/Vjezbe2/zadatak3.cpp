#include <iostream>

int zad3() {
    int n;
    std::cout << "Unesite velicinu niza: ";
    std::cin >> n;

    int* niz = new int[n];

    std::cout << "Unesite elemente niza: " << std::endl;
    for (int i = 0; i < n; i++) {
        std::cin >> niz[i];
    }

    int suma_parnih = 0;
    int suma_neparnih = 0;
    for (int i = 0; i < n; i++) {
        if (niz[i] % 2 == 0) {
            suma_parnih += niz[i];
        }
        else {
            suma_neparnih += niz[i];
        }
    }

    std::cout << "Suma parnih elemenata niza je: " << suma_parnih << std::endl;
    std::cout << "Suma neparnih elemenata niza je: " << suma_neparnih << std::endl;

    delete[] niz;

    return 0;
}