#include <iostream>

using std::cout;

struct a
{
    void foo(int i) { 
        cout << "0"; 
    };
};

struct b : a
{
    void foo(int i) { 
        cout << "1"; 
    };
};

struct c : b
{
    void foo(int i) { 
        cout << "2"; 
    };
};

/*int main() {

    // PRVI SLUCAJ
    c x;
    a* y = &x;
    y->foo(1);

    // DRUGI SLUCAJ
    b* z = &x;
    z->foo(2);

    // TRECI SLUCAJ
    a& w = x;
    w.foo(3);

    // CETVRTI SLUCAJ
    b& wb = x;
    wb.foo(4);

    // PETI SLUCAJ
    a* m = new c();
    m->foo(5);

    return 0;
}

*/