#include "complex.hpp"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

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

std::istream& operator>>(std::istream& in, Complex& num) {

    double real;
    double imag;
    char sign;
    char i;

    in >> real;
    in >> sign;
    in >> imag;
    in >> i;

    if (sign == '-') {
        imag = -imag;
    }

    num.setReal(real);
    num.setImag(imag);

    return in;
}


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


int main() {
    Complex c,a, b;
    std::string operacija, file;

    std::cout << "Unesite ime fajla: " << std::endl;
    std::cin >> file;

    std::string outputFileName = "o_" + file;

    std::ifstream inputFile(file);
    std::ofstream outputFile(outputFileName);

    if (!inputFile.is_open()) {
        std::cerr << "Error opening file." << std::endl;
    }

    if (!outputFile.is_open()) {
        std::cerr << "Error opening file." << std::endl;
    }

    std::string line;

    while (getline(inputFile, line)) {
        std::istringstream iss(line);

        if (!(iss >> a >> operacija >> b)) {
            std::cerr << "Error reading line from the file. Check file content. Line content: " << line << std::endl;
            continue;
        }

        std::cout << "Read: " << a.to_string() << " " << operacija << " " << b.to_string() << std::endl;

        // Validate operacija
        if (operacija == "ADD") {
            c = a + b;
        }
        else if (operacija == "MUL") {
            c = a * b;
        }
        else if (operacija == "SUB") {
            c = a - b;
        }
        else {
            std::cerr << "Error: Unknown operation '" << operacija << "'." << std::endl;
            continue;  // Skip to the next iteration
        }

        // Output the result to the output file
        outputFile << "= " << c.to_string() << std::endl;
    }

    inputFile.close();
    outputFile.close();

    std::cout << "Done!" << std::endl;
    return 0;
}

