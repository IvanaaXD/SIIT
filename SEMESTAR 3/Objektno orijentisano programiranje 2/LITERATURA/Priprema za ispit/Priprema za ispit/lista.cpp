#include <iostream>
#include <vector>

std::vector<int> zadrziDruguPolovinu(const std::vector<int>& lista) {
    std::vector<int> rezultat;

    std::size_t sredina = lista.size() / 2;

    std::vector<int>::const_iterator iterator = lista.begin() + sredina;

    while (iterator != lista.end()) {

        rezultat.push_back(*iterator);

        ++iterator;
    }

    return rezultat;
}

/*int main() {
    std::vector<int> lista = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    std::vector<int> rezultat = zadrziDruguPolovinu(lista);

    for (const auto& element : rezultat) {
        std::cout << element << " ";
    }

    return 0;
}
*/