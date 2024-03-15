#include "complex2.hpp"
#include <iostream>
#include <string>

using namespace std;

Complex::Complex() : x(0.0), y(0.0) {};

Complex::Complex(double real, double img) : x(real), y(img) {};

Complex::Complex(const Complex& other) : x(other.x), y(other.y) {};

Complex::~Complex() {};

void Complex::setReal(double real) {
	(*this).x = real;
};

void Complex::setImag(double img) {
	(*this).y = img;
};

double Complex::getReal() const{
	return (*this).x;
};

double Complex::getImag() const {
	return (*this).y;
};

std::string Complex::to_string() {
	std::string s;
	s = std::to_string(x) + " + " + std::to_string(y) + "i";
	return s;
};

Complex Complex :: operator+(Complex num) {
	return Complex((*(this)).x + num.getReal(), (*(this)).y + num.getImag());
}

Complex Complex :: operator-(Complex num) {
	return Complex((*(this)).x - num.getReal(), (*(this)).y - num.getImag());
}

Complex Complex :: operator*(Complex num) {
	return Complex((*(this)).x * num.getReal() - (*(this)).y * num.getImag(), (*(this)).x * num.getImag() + (*(this)).y * num.getReal());
}

Complex Complex :: operator~() {
	return Complex((*(this)).x, -(*(this)).y);
}

std::istream& operator>>(std::istream& in, Complex& num) {
	double real, imag;
	in >> real >> imag;
	num = Complex(real, imag);
	return in;
}

std::ostream& operator<<(std::ostream& os,const Complex& num) {
	os << "(" << num.getReal() << ", " << num.getImag() << ")" << endl;
	return os;
}

int main(int argc, char** argv) {
	Complex a(3.0, 4.0);
	Complex b;
	cout << "Enter a complex number :" << endl;
	cin >> b;
	cout << "a (" << a.getReal() << ", " << a.getImag() << "i) " << endl; cout << "b " << b << endl;
	cout << "Complex conjugate b is " << ~b << endl; 
	cout << "a + b is " << a + b << endl;
	cout << "a - b is " << a - b << endl;
	cout << "a * b is " << a * b << endl;
	//cout << "2 * b is " << b * 2 << endl;
	//cout << "b * 2 is " << 2 * b << endl;
	cout << "Done!" << endl;
	return 0;
}
