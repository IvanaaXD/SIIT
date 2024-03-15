#include "complex.hpp"
#include <iostream>
#include <string>

using namespace std;

Complex::Complex() : x(0.0), y(0.0) {};

Complex::Complex(double real, double img) : x(real), y(img) {};

Complex::Complex(const Complex& other) : x(other.x), y(other.y) {};

void Complex::setReal(double real) {
	(*this).x = real;
};

void Complex::setImag(double img) {
	(*this).y = img;
};

double Complex::getReal() {
	return (*this).x;
};

double Complex::getImag() {
	return (*this).y;
};

std::string Complex::to_string() {
	std::string s;
	s = std::to_string(x) + " + " + std::to_string(y) + "i";
	return s;
};

Complex Complex::add(Complex num) {

	double real = num.getReal() + (*(this)).x;
	double img = num.getImag() + (*(this)).y;
	return Complex(real, img);
}

Complex Complex::sub(Complex num) {

	double real = num.getReal() - (*(this)).x;
	double img = num.getImag() - (*(this)).y;
	return Complex(real, img);
}

Complex Complex::mul(Complex num) {

	double jedan = (*(this)).x * num.getReal();
	double dva = (*(this)).x * num.getImag();
	double tri = (*(this)).y * num.getImag();
	double cetiri = (*(this)).y * num.getReal();

	double real = jedan - tri;
	double img = dva + cetiri;
	return Complex(real, img);
}

Complex Complex :: conj() {

	return Complex(this->x, -this->y);
};

/*int main(int argc, char** argv) {
	Complex a(3.0, 4.0); // initialize to (3, 4i)
	Complex b(5.0, 7.0); // initialize to (5, 7i)
	Complex c;

	cout << "a (" << a.getReal() << ", " << a.getImag() << "i) " << endl; cout << "b (" << b.getReal() << ", " << b.getImag() << "i) " << endl; c = a.add(b);
	cout << "a + b = (" << c.getReal() << ", " << c.getImag() << "i) " << endl; c = a.sub(b);
	cout << "a - b = (" << c.getReal() << ", " << c.getImag() << "i) " << endl; c = a.mul(b);
	cout << "a * b = (" << c.getReal() << ", " << c.getImag() << "i) " << endl; c = a.conj();
	cout << "~a = (" << c.getReal() << ", " << c.getImag() << "i) " << endl;
	cout << "Done!" << endl;
	return 0;
}*/