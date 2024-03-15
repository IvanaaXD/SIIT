#include "kompleksnibrojevi.hpp"
#include <iostream>
#include <string>

using namespace std;


KompleksniBroj::KompleksniBroj() : x(0.0), y(0.0) {};

KompleksniBroj::KompleksniBroj(double real, double img) : x(real), y(img) {};

KompleksniBroj::KompleksniBroj(const KompleksniBroj& other) : x(other.x), y(other.y) {};

void KompleksniBroj::setReal(double real) {
	(*this).x = real;
};

void KompleksniBroj::setImg(double img) {
	(*this).y = img;
};

double KompleksniBroj::getReal() {
	return (*this).x;
};

double KompleksniBroj :: getImg() {
		return (*this).y;
};

std :: string KompleksniBroj :: to_string() {
		std :: string s;
		s = std::to_string(x) + " + " + std::to_string(y) + "i";
		return s;
};

KompleksniBroj konjugovano(KompleksniBroj broj) {

	KompleksniBroj brojK;

	brojK.setReal(broj.getReal());
	brojK.setImg(-broj.getImg());

	return brojK;
};

KompleksniBroj oduzmi(KompleksniBroj broj1, KompleksniBroj broj2) {
	KompleksniBroj broj3;
	
	broj3.setReal(broj1.getReal() - broj2.getReal());
	broj3.setImg(broj1.getImg() - broj2.getImg());
	return broj3;
}

KompleksniBroj saberi(KompleksniBroj broj1, KompleksniBroj broj2) {
	KompleksniBroj broj3;

	broj3.setReal(broj1.getReal() + broj2.getReal());
	broj3.setImg(broj1.getImg() + broj2.getImg());
	return broj3;
}

KompleksniBroj pomnozi(KompleksniBroj broj1, KompleksniBroj broj2) {
	KompleksniBroj broj3;

	double jedan = broj1.getReal() * broj2.getReal();
	double dva = broj1.getReal() * broj2.getImg();
	double tri = broj1.getImg() * broj2.getImg();
	double cetiri = broj1.getImg() * broj2.getReal();
	broj3.setReal(jedan - tri);
	broj3.setImg(dva + cetiri);
	return broj3;
}

KompleksniBroj podijeli(KompleksniBroj broj1, KompleksniBroj broj2) {
	KompleksniBroj broj3;

	KompleksniBroj broj4 = konjugovano(broj2);

	KompleksniBroj brojilac = pomnozi(broj1, broj4);
	KompleksniBroj imenilac = pomnozi(broj2, broj4);

	if (imenilac.getReal() == 0) {
		throw std::runtime_error("Division by zero is not allowed");
	}

	broj3.setReal(brojilac.getReal()/imenilac.getReal());
	broj3.setImg(brojilac.getImg()/imenilac.getReal());
	return broj3;
}


int main() {
	KompleksniBroj broj1(3.0, 4.0);
	KompleksniBroj broj2(2.0, 1.0);

	/*cout << sizeof(KompleksniBroj) << endl;
	cout << sizeof(Broj) << endl;
	cout << sizeof(Broj1) << endl;
	cout << sizeof(Slovo) << endl;
	cout << sizeof(Tekst) << endl;*/

	KompleksniBroj broj3 = podijeli(broj1, broj2);
	cout << "Complex number: " << broj3.to_string() << endl;

	return 0;
}