#ifndef COMPLEX_HPP
#define COMPLEX_HPP

#include <string>

class Complex {
private:
    double x;
    double y;

public:
    Complex();
    Complex(double real, double img);
    Complex(const Complex& other);
    ~Complex();

    void setReal(double real);
    void setImag(double img);
    double getReal() const;
    double getImag() const;

    Complex add(Complex num);
    Complex sub(Complex num);
    Complex mul(Complex num);
    Complex conj();

    Complex operator+(Complex num);
    Complex operator-(Complex num);
    Complex operator*(Complex num);
    Complex operator~();

    friend std::istream& operator>>(std::istream& in, Complex& num);
    friend std::ostream& operator<<(std::ostream& os, const Complex& num);

    std::string to_string();
};

#endif