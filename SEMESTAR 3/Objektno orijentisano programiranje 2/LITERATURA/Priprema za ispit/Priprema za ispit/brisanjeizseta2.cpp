#include <iostream>
#include <set>

// Funkcija koja filtrira set i uklanja sve neparne brojeve
void filterParni(std::set<int>& s) {
    std::set<int>::iterator it;
    for (it = s.begin(); it != s.end(); it++) {
        if (*it % 2 != 0) {
            it = s.erase(it);
        }
    }
}

/*int main() {
    std::set<int> brojevi = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    std::cout << "Pre filtriranja: ";
    for (int broj : brojevi) {
        std::cout << broj << " ";
    }
    std::cout << std::endl;

    // Pozivamo funkciju za filtriranje parnih brojeva
    filterParni(brojevi);

    std::cout << "Posle filtriranja: ";
    for (int broj : brojevi) {
        std::cout << broj << " ";
    }
    std::cout << std::endl;

    return 0;
}
*/