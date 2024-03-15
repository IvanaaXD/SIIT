#include <stdexcept>
#include <iostream>
#include <string> 

using namespace std;

void fibonaci1(int num1, int num2, int& brojac) {

    int num3 = num1 + num2;
    int overflow;

    if ((num1 > 0 && num2 > 0 && num3 < 0) || (num1 < 0 and num2 < 0 and num3 > 0)) {
        overflow = 1;
    }
    else {
        brojac++;
        overflow = 0;
        fibonaci1(num2, num3, brojac);
    }

    if (overflow) {
        throw overflow_error("Overflow kod int-a se desio. Najveci broj je: " + to_string(num2) + " sa indeksom: " + to_string(brojac));
    }
}

void fibonaci2(long num1, long num2, int& brojac) {

    long num3 = num1 + num2;
    int overflow;

    if ((num2 > 0 && num1 > numeric_limits<long>::max() - num2) ||
        (num2 < 0 && num1 < numeric_limits<long>::min() - num2)) {
        overflow = 1;
    }
    else {
        brojac++;
        overflow = 0;
        fibonaci2(num2, num3, brojac); 
    }

    if (overflow) {
        throw overflow_error("Overflow kod long-a se desio. Najveci broj je: " + to_string(num2) + " sa indeksom: " + to_string(brojac));
    }
}


void fibonaci3(long long num1, long long num2, int& brojac) {

    long long num3 = num1 + num2;
    int overflow;

    if ((num2 > 0 && num1 > numeric_limits<long long>::max() - num2) ||
        (num2 < 0 && num1 < numeric_limits<long long>::min() - num2)) {
        overflow = 1;
    }
    else {
        brojac++;
        overflow = 0;
        fibonaci3(num2, num3, brojac);
    }

    if (overflow) {
        throw overflow_error("Overflow kod long long-a se desio. Najveci broj je: " + to_string(num2) + " sa indeksom: " + to_string(brojac));
    }
}

int main() {

    int brojac1 = 1;

	try {
		int num1 = 0;
		int num2 = 1;
		fibonaci1(num1, num2, brojac1);
	}
	catch (const overflow_error& e) {
		std::cerr << "Exception: " << e.what() << std::endl;
	}

    int brojac2 = 1;

	try {
		long num3 = 0;
        long num4 = 1;
		fibonaci2(num3, num4, brojac2);
	}
	catch (const overflow_error& e) {
		cerr << "Exception: " << e.what() << endl;
	}

    int brojac3 = 1;

	try {
		long long num5 = 0;
		long long num6 = 1;
		fibonaci3(num5, num6, brojac3);
	}
	catch (const overflow_error& e) {
		cerr << "Exception: " << e.what() << endl;
	}

	return 0;
}