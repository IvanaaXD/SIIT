#include <iostream>

int zad2() {
    int n;
    std::cout << "Unesite velicinu niza: ";
    std::cin >> n;

    int* niz = new int[n];

    std::cout << "Unesite elemente niza: " << std::endl;
    for (int i = 0; i < n; i++) {
        std::cin >> niz[i];
    }

    std::cout << "Elementi parnog indeksa niza: " << std::endl;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            std::cout << niz[i] << " ";
        }
    } 

    std::cout << std::endl << "Elementi neparnog indeksa niza: " << std::endl;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 1) {
            std::cout << niz[i] << " ";
        }
    }

    delete[] niz;

    return 0;
}