#include <iostream>
#include <list>

std::list<char> concatenateLists(const std::list<char>& l1, const std::list<char>& l2) {
    std::list<char> result;
    // Append elements of l1 to result
    for (auto it = l1.begin(); it != l1.end(); ++it) {
        result.push_back(*it);
    }
    // Append elements of l2 to result
    for (auto it = l2.begin(); it != l2.end(); ++it) {
        result.push_back(*it);
    }
    return result;
}

std::ostream& operator<<(std::ostream& os, const std::list<char>& lst) {
    for (char c : lst) {
        os << c;
    }
    return os;
}

/*int main() {
    std::list<char> l1 = { 'Z', 'V', 'E' };
    std::list<char> l2 = { 'Z', 'D', 'A' };

    std::list<char> concatenated = concatenateLists(l1, l2);
    std::cout << concatenated << std::endl;

    return 0;
}
*/