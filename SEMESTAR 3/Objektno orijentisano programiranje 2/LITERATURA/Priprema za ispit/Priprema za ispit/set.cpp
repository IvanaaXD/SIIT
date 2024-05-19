#include <iostream>
#include <set>

std::ostream& operator<<(std::ostream& os, const std::set<int>& s)
{
    os << "{ ";
    std::set<int>::const_iterator it;
    for (it = s.begin(); it != s.end(); ++it)
    {
        os << *it << " ";
    }
    os << "}";
    return os;
}

/**int main()
{
    std::set<int> mySet = { 1, 2, 3, 4, 5 };

    // Printing the set using the operator <<
    std::cout << mySet << std::endl;

    return 0;
}
*/