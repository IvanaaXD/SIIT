#include "DynamicMatrix.h"

DynamicMatrix::DynamicMatrix() : rows(0), cols(0), matrix(nullptr) {
}

DynamicMatrix::DynamicMatrix(int x, int y) : rows(x), cols(y) {

    if (rows > 0 && cols > 0) {
        matrix = new int* [rows];
        for (int i = 0; i < rows; ++i) {
            matrix[i] = new int[cols];
        }
    }
};

DynamicMatrix::~DynamicMatrix() {
    if (matrix != nullptr) {
        for (int i = 0; i < rows; ++i) {
            delete[] matrix[i];
        }

        delete[] matrix;
    }
}

DynamicMatrix::DynamicMatrix(const DynamicMatrix& other) {

    rows = other.rows;
    cols = other.cols;

    matrix = new int* [rows];

    for (int i = 0; i < rows; ++i) {
        matrix[i] = new int[cols];
        for (int j = 0; j < cols; ++j) {
            matrix[i][j] = other.matrix[i][j];
        }
    }
}

void DynamicMatrix::setRows(int num) {
	rows = num;
};

void DynamicMatrix::setCols(int num) {
	cols = num;
};

int DynamicMatrix::getRows() const {
	return rows;
};

int DynamicMatrix::getCols() const {
	return cols;
};

int DynamicMatrix::getElement(int row, int col) const {
    return matrix[row][col];

}
void DynamicMatrix::setElement(int row, int col, int value) {

    for (int i = 0; i < rows; ++i) {
        if (rows = row) {
            for (int j = 0; j < cols; ++j) {
                if (cols = col) {
                    matrix[i][j] = value;
                    break;
                }
            }
        }
    }
}

DynamicMatrix& DynamicMatrix::operator=(const DynamicMatrix& other) {
    if (this != &other) {
        for (int i = 0; i < rows; ++i) {
            delete[] matrix[i];
        }
        delete[] matrix;

        rows = other.rows;
        cols = other.cols;

        matrix = new int* [rows];
        for (int i = 0; i < rows; ++i) {
            matrix[i] = new int[cols];
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = other.matrix[i][j];
            }
        }
    }

    return *this;
}


int* DynamicMatrix::operator[](int rowIndex) {
    return matrix[rowIndex];
}

const int* DynamicMatrix::operator[](int rowIndex) const {
    return matrix[rowIndex];
}

DynamicMatrix DynamicMatrix::operator+(const DynamicMatrix& other) const {

    DynamicMatrix result(rows, cols);

    if (rows != other.rows && cols != other.cols) {
        std::cout << "Matrix dimensions do not match for addition." << std::endl;
    }
    else {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                result.matrix[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }

    }

    return result;

}

DynamicMatrix DynamicMatrix::operator*(const DynamicMatrix& other) const {
    DynamicMatrix result(rows, other.cols);

    if (cols == other.rows) {

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < other.cols; ++j) {
                result.matrix[i][j] = 0;
                for (int k = 0; k < cols; ++k) {
                    result.matrix[i][j] += matrix[i][k] * other.matrix[k][j];
                }
            }
        }
    }
    else {
        std::cout << "Matrix dimensions do not match for multiplication." << std::endl;
    }

    return result;
}

DynamicMatrix DynamicMatrix::transpose() const {
    DynamicMatrix result(cols, rows);

    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            result.matrix[j][i] = matrix[i][j];
        }
    }
    return result;
}

std::ostream& operator<<(std::ostream& os, const DynamicMatrix& matrix) {
    for (int i = 0; i < matrix.getRows(); ++i) {
        for (int j = 0; j < matrix.getCols(); ++j) {
            os << matrix.getElement(i, j) << " ";
        }
        os << std::endl;
    }
    return os;
}