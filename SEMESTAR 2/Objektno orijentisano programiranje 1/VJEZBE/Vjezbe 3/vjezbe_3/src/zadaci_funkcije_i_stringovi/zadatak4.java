package zadaci_funkcije_i_stringovi;
public class zadatak4 {
	
	static void funkcija(int A[], int B[]) {
		
		int C[] = new int [A.length];
		
		for (int i = 0; i < A.length; i++) {
				
				C[i] = A[i] + B[i];
				
		}
		
		for (int i = 0; i < C.length; i++) {
			System.out.print(C[i] + "   ");
		}
		
	}

	public static void main(String[] args) {

		int A[] = {12, -7, 5, 18, -2};
		int B[] = {5, 18, -17, 3, -9};
				
		funkcija(A, B);
		
	}

}