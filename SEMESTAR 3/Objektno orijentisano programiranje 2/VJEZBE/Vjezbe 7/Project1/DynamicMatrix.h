#include <iostream>

class DynamicMatrix {
private:
	int rows;
	int cols;
	int** matrix;

public:
    DynamicMatrix();
    DynamicMatrix(int numRows, int numCols);

    DynamicMatrix(const DynamicMatrix& other);

    ~DynamicMatrix();

    void setCols(int num);
    void setRows(int num);
    int getRows() const;
    int getCols() const;

    int getElement(int row, int col) const;
    void setElement(int row, int col, int value);    

    DynamicMatrix& operator=(const DynamicMatrix& other);    
    int* operator[](int rowIndex);
    const int* operator[](int rowIndex) const;

    DynamicMatrix operator+(const DynamicMatrix& other) const;
    DynamicMatrix operator*(const DynamicMatrix& other) const;

    DynamicMatrix transpose() const;

    friend std::ostream& operator<<(std::ostream& os, const DynamicMatrix& matrix);


};