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
    B* x = new B();
    x->foo();

    // DRUGI SLUCAJ
    A* y = new B();
    y->foo();

    // TRECI SLUCAJ
    // B z;
    // B& k = z;
    // k->foo();

    // CETVRTI SLUCAJ
    // A o;
    // B* p = &o;
    // p->foo();

    // PETI SLUCAJ
    B p;
    A* f = &p;
    f->foo();

    return 0;
}*/
