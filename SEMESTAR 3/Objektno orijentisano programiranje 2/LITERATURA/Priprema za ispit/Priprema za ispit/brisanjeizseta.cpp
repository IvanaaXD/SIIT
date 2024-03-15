#include <iostream>
#include <set>
#include <iterator>

// Dovršena implementacija funkcije
void foo(std::set<int>& inputSet)
{
    auto it = inputSet.begin();

    while (it != inputSet.end())
    {
        if (*it % 2 != 0)
        {
            it = inputSet.erase(it); // Briše neparni element
        }
        else
        {
            ++it;
        }
    }
}

/*int main()
{
    std::set<int> mySet = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    std::cout << "Originalni skup: ";
    for (const auto& element : mySet)
    {
        std::cout << element << " ";
    }
    std::cout << std::endl;

    // Poziv funkcije foo
    foo(mySet);

    std::cout << "Skup nakon poziva funkcije foo: ";
    for (const auto& element : mySet)
    {
        std::cout << element << " ";
    }
    std::cout << std::endl;

    return 0;
}
*/

#include <iostream>
#include <list>

void foo(std::list<int>& inputList)
{
    auto it = inputList.begin();

    while (it != inputList.end())
    {
        if (*it % 2 != 0)
        {
            it = inputList.erase(it); // Briše neparni element
        }
        else
        {
            ++it;
        }
    }
}

int main()
{
    std::list<int> myList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    foo(myList);

    // Ispisujemo rezultat
    for (const auto& element : myList)
    {
        std::cout << element << " ";
    }

    return 0;
}
