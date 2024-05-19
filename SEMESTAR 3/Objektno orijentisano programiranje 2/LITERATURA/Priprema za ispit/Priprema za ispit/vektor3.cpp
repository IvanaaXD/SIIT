#include <iostream>
#include <vector>

std::ostream& operator<<(std::ostream& os, const std::vector<int>& s) {
    for (const int& elem : s) {
        os << elem << " ";
    }
    return os;
}

std::vector<int>operator+(const std::vector<int>& lhs, const std::vector<int>& rhs) {
    std::vector<int> result = lhs;
    for (const int& elem : rhs) {
        result.push_back(elem);
    }
    return result;
}

/*int main() {
    std::vector<int> x{1, 2, 3};
    std::vector<int> y{3, 2, 1};
    std::vector<int> z{2, 1, 3};

    std::cout << x + y + z << std::endl;

    return 0;
}
*/