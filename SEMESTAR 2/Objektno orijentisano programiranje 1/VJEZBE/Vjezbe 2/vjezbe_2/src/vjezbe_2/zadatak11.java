package vjezbe_2;

public class zadatak11 {

	public static void main(String[] args) {

		int A[] = {0,1,2,3,4};
		int B[] = {5,6,7,8,9};
		int C[] = new int[A.length];
		
		int sabirak1 = 0;
		int sabirak2 = 0;
		
		int j = B.length - 1;
		for (int i = 0; i < A.length; i++) {	
			
			sabirak1 = A[i];
			sabirak2 = B[j];
			
			C[i] = sabirak1 + sabirak2;
			j -= 1;

			continue;
		}
		
		System.out.println("Niz C je oblika: ");
		for (int i = 0; i < C.length; i++) {
			System.out.print(C[i] + ", ");
		}
		
	}

}
