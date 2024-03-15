using namespace std;
#include <iostream>

namespace prvo {

	void foo() { cout << "1" << endl; };
	void bar() { cout << "5" << endl; };
}

namespace drugo {

	void foo() { cout << "2," << endl; };
}

// Ovde dodati kod
using drugo::foo;
using prvo::bar;

//

/*int main() {

	foo();
	bar();
}*/