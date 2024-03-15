#include <iostream>

using std::cout;

struct A
{
    void foo() {
        cout << "0";
    };
};

struct B : A
{
    void foo() {
        cout << "1";
    };
};

/*int main() {

    // PRVI SLUCAJ
    A x;
    x.foo();

    // DRUGI SLUCAJ
    B y;
    y.foo();

    // TRECI SLUCAJ
    //A* z = new B();
    //z.foo();

    // CETVRTI SLUCAJ
    B k;
    A& l = k;
    l.foo();

    return 0;
}
*/