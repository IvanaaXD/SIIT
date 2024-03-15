#include <iostream>
#include <set>

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::set<T>& s)
{
    os << "{ ";
    for (const auto& element : s)
    {
        os << element << " ";
    }
    os << "}";
    return os;
}

/*int main()
{
    std::set<int> mySet = { 1, 2, 3, 4, 5 };

    // Ispisivanje set-a pomoæu operatora <<
    std::cout << mySet << std::endl;

    return 0;
}*/
