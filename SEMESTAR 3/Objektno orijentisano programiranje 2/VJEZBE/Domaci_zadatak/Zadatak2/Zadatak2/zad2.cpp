#include <iostream>
#include <string>
#include <regex>
#include <sstream>

using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::regex;
using std::stringstream;

bool isHexadecimal(const string& str) {
    static const regex hexPattern("^[0-9A-Fa-f]+$");
    return regex_match(str, hexPattern);
}

int hexCharToInt(char c) {
    if ('0' <= c && c <= '9') {
        return c - '0';
    }
    else if ('a' <= c && c <= 'f') {
        return c - 'a' + 10;
    }
    else if ('A' <= c && c <= 'F') {
        return c - 'A' + 10;
    }
    return -1;
}

int hexToInt(const string& hex) {
    int num = 0;
    for (char c : hex) {
        int digitValue = hexCharToInt(c);
        if (digitValue == -1) {

            throw std::invalid_argument("Greska pri unosu.");
        }
        num = num * 16 + digitValue;
    }
    return num;
}

string intToHex(unsigned int num) {
    stringstream ss;
    ss << std::hex << num;
    return ss.str();
}

string function1(int num) {

    try {
        if (cin.fail()) {
            cout << "hello";
            throw std::runtime_error("Greska prilikom unosa");
        }
        else if (num > 4294967295 || num < 0) {
            throw std::runtime_error("Greska prilikom unosa");
        }
        else {
            return intToHex(num);
        }
    }
    catch (const std::exception& ex) {
        std::cerr << "Exception: " << ex.what() << endl;
        return "";
    }
}

string function2(string hex1, string hex2) {
    string hex;
    try {
        if (isHexadecimal(hex1) && isHexadecimal(hex2)) {
            int num1 = hexToInt(hex1);
            int num2 = hexToInt(hex2);
            int suma = num1 + num2;
            hex = function1(suma);
        }
        else {
            throw std::runtime_error("Greska prilikom unosa");
        }
    }
    catch (const std::exception& ex) {
        std::cerr << "Exception: " << ex.what() << endl;
    }
    return hex;
}

int function3(string hex) {
    try {
        if (!isHexadecimal(hex)) {
            throw std::runtime_error("Greska prilikom unosa");
        }
        else {
            return hexToInt(hex);
        }
    }
    catch (const std::exception& ex) {
        std::cerr << "Exception: " << ex.what() << endl;
        return -1; 
    }
}

int main() {
    // function 1
    int num = 43;
    string s1 = function1(num);
    if (s1 != "") {
        cout << "Broj " << num << " u heksadecimalnom zapisu glasi: " << s1 << endl;
    }

    cout << "===================================================================" << endl;

    // function 2
    string hex1 = "fff";
    string hex2 = "3f";
    string hex = function2(hex1, hex2);
    if (hex != "") {
        cout << "Zbir brojeva " << hex1 << " i " << hex2 << " je: " << hex << endl;
    }

    hex1 = "-6";
    hex2 = "ok";
    hex = function2(hex1, hex2);
    if (hex != "") {
        cout << "Zbir brojeva " << hex1 << " i " << hex2 << " je: " << hex << endl;
    }

    hex1 = "";
    hex2 = "";
    hex = function2(hex1, hex2);
    if (hex != "") {
        cout << "Zbir brojeva " << hex1 << " i " << hex2 << " je: " << hex << endl;
    }

    cout << "===================================================================" << endl;

    // function 3
    string hexx = "ff23";
    int numm = function3(hexx);
    if (numm != -1) {
        cout << "Broj " << hexx << " u cjelobrojnom zapisu glasi: " << numm << endl;
    }

    hexx = "lke";
    numm = function3(hexx);
    if (numm != -1) {
        cout << "Broj " << hexx << " u cjelobrojnom zapisu glasi: " << numm << endl;
    }

    hexx = "";
    numm = function3(hexx);
    if (numm != -1) {
        cout << "Broj " << hexx << " u cjelobrojnom zapisu glasi: " << numm << endl;
    }

    return 0;
}
