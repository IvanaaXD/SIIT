#include <iostream>
#include <list>

void keepFirstHalf(std::list<int>& lst) {
    size_t halfSize = lst.size() / 2;

    if (lst.size() % 2 != 0) {
        halfSize += 1;
    }

    auto it = lst.begin();
    for (int i = 0; i < halfSize; ++i) {
        ++it;
    }
    lst.erase(it, lst.end());

}

/*int main() {
    std::list<int> myList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    std::cout << "Original list: ";
    for (const auto& elem : myList) {
        std::cout << elem << " ";
    }
    std::cout << std::endl;

    keepFirstHalf(myList);

    std::cout << "List after keeping first half: ";
    for (const auto& elem : myList) {
        std::cout << elem << " ";
    }
    std::cout << std::endl;

    return 0;
}
*/