#include <iostream>
#include <string>

template<class T>
T saberi(T x, T y)
{
    return x + x - y + y;
}

/*int main() {
    // a) int a; int b;
    int result_a = saberi(5, 3);
    std::cout << "Result for int: " << result_a << std::endl;

    // b) std::string a; std::string b;
    // Ovo neæe raditi jer saberi ne podrava tip std::string

    // c) int a; float b;
    float result_c = saberi<float>(5, 3.5f);
    std::cout << "Result for int and float: " << result_c << std::endl;

    // d) float a; double b;
    double result_d = saberi<double>(3.5f, 4.7);
    std::cout << "Result for float and double: " << result_d << std::endl;

    return 0;
}
*/