#include "utils.h"

void init_matrix(int *matrix, int m, int n){
	int data_size = m*n;
	for(int i = 0; i < data_size; ++i){
		matrix[i] = i;
	}
}

void print_matrix(int *matrix, int m, int n, char *name){
	cout << "[" << name << "]" << endl; 
	for(int i=0; i < m; i++){
		for(int j=0; j < n; j++){
			cout << matrix[i*n + j] << "\t"; 
		}
		cout << endl; 
	}
}

void matrix_addition_serial(int *A, int *B, int *C, int m, int n){
	for(int i=0; i < m; i++){
		for(int j=0; j < n; j++){
			C[i*n + j] = A[i*n + j] + B[i*n + j];
		}
	}
}

bool check_results(int *matrix1, int *matrix2, int m, int n){
	for(int i=0; i < m; i++){
		for(int j=0; j < n; j++){
			if(matrix1[i*n + j] != matrix2[i*n + j]){
				return false;
			}
		}
	}
	return true;
}

void matrix_multiplication_serial(int* A, int* B, int* C, int M, int N) {

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				C[i * N + j] += A[i * N + k] * B[k*N + j];
			}
		}
	}
}
