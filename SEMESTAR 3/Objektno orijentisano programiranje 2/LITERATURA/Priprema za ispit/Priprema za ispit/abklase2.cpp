#include <iostream>
using namespace std;

class A {
public:
	virtual void foo() { cout << "A virtual void foo()" << endl; }
};

class B : public A {
public: void foo() { cout << "B void foo()" << endl; }
};

/*/int main() {

	B* x = new B(); // PRVO -> radi
	x->foo();


	A  y;           // DRUGO -> ne radi jer ne mozemo da dodelimo pokazivacu na tip izvedene klase adresu objekta roditeljske klase
	B* x = &y;
	x->foo();


	B  y;
	B& x = y;
	x->foo();       // TRECE -> radilo bi sa x.foo();


	A* x = new B(); // CETVRTO -> radi
	x->foo();


	return 0;
}
*/