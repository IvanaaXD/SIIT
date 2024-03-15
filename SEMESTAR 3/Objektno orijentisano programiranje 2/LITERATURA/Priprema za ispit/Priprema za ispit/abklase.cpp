#include <iostream>

class A {
public:
    virtual void foo() {
        std::cout << "Metoda foo() iz klase A" << std::endl;
    }
};

class B : public A {
public:
    void foo() override {
        std::cout << "Metoda foo() iz klase B" << std::endl;
    }
};

/*int main() {

    // PRVI SLUCAJ
    // B* x = new A();
    // x->foo();

    // DRUGI SLUCAJ
    B y; 
    B* x = &y; 
    x->foo(); 

    // TRECI SLUCAJ
    B z;
    A* v = &z;
    v->foo();

    // CETVRTI SLUCAJ
    A* k = new B();
    k->foo();


    return 0;
}
*/