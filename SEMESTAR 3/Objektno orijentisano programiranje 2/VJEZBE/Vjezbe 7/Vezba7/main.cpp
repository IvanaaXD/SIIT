#include "DynamicMatrix.h"

int main() {
    // 1. Create matrices
    DynamicMatrix matrix1(2, 3);
    DynamicMatrix matrix2(3, 2);
    DynamicMatrix matrix3(matrix1);

    // 2. Initialize matrix elements
    matrix1[0][0] = 1; matrix1[0][1] = 2; matrix1[0][2] = 3;
    matrix1[1][0] = 4; matrix1[1][1] = 5; matrix1[1][2] = 6;

    matrix2[0][0] = 7; matrix2[0][1] = 8;
    matrix2[1][0] = 9; matrix2[1][1] = 10;
    matrix2[2][0] = 11; matrix2[2][1] = 12;

    // 3. Display matrices
    std::cout << "Matrix 1:\n";
    std::cout << matrix1;

    std::cout << "\nMatrix 2:\n";
    std::cout << matrix2;

    std::cout << "\nMatrix 3:\n";
    std::cout << matrix3;

    // 4. Assignment operator
    DynamicMatrix matrix4 = matrix2;
    std::cout << "\nMatrix 4:\n";
    std::cout << matrix4;

    // 5. Matrix addition - example 1
    DynamicMatrix matrixSumOne = matrix1 + matrix1;
    std::cout << "\nMatrix Sum (Matrix 1 + Matrix 1):\n";
    std::cout << matrixSumOne;

    // 6. Matrix addition - example 2
    DynamicMatrix matrixSumTwo = matrix1 + matrix2;
    std::cout << "\nMatrix Sum (Matrix 1 + Matrix 2):\n";
    std::cout << matrixSumTwo;

    // 7. Matrix multiplication
    DynamicMatrix matrixProduct = matrix1 * matrix2;
    std::cout << "\nMatrix Product (Matrix 1 * Matrix 2):\n";
    std::cout << matrixProduct;

    // 8. Transpose
    DynamicMatrix transposedMatrix = matrix1.transpose();
    std::cout << "\nTransposed Matrix (Transpose of Matrix 1):\n";
    std::cout << transposedMatrix;

    int a;
    std::cin >> a;

    return 0;
}
