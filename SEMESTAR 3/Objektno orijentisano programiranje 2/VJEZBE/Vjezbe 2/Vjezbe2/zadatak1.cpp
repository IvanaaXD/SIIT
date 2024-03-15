#include <iostream>

int zad1() {
    int n;
    std::cout << "Unesite velicinu niza: ";
    std::cin >> n;

    int* niz = new int[n];

    std::cout << "Unesite elemente niza: " << std::endl;
    for (int i = 0; i < n; i++) {
        std:: cin >> niz[i];
    }

    std::cout << "Elementi niza u obrnutom redoslijedu: " << std::endl;
    for (int i = n - 1; i >= 0; i--) {
        std::cout << niz[i] << " ";
    }

    delete[] niz; 

    return 0;
}

