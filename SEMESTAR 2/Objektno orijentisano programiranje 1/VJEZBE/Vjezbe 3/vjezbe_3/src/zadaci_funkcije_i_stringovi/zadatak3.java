package zadaci_funkcije_i_stringovi;

public class zadatak3 {
	
	static double funkcija_a(double A[]) {
		
		double max = 0;
		
		for (int i = 0; i < A.length; i++) {
			
			if (A[i] > max) {
				max = A[i];
			}
		}
		
		return max;
		
	}
	
	static double funkcija_b(double A[]) {
		
		double min = A[0];
		
		for (int i = 1; i < A.length; i++) {
			
			if (A[i] < min) {
				min = A[i];
			}
		}
		
		return min;
		
	}
	
	static double funkcija_c(double A[]) {
		
		double srednja_vrijednost;
		
		double suma = 0;
		double brojac = 0;
		for (int i = 0; i < A.length; i++) {
			
			suma += A[i];
			brojac += 1;
			
		}
		
		srednja_vrijednost = suma/brojac;
		
		return srednja_vrijednost;
		
	}
	
	static void funkcija_d(double A[]) {
		
		double novi_niz[] = new double [A.length];
		double srednja_vrijednost = funkcija_c(A);
		
		for (int i = 0; i < A.length; i++) {
			
			if (A[i] < 0) {
				novi_niz[i] = A[i] + srednja_vrijednost;
			}
			else {
				novi_niz[i] = A[i] - srednja_vrijednost;
			}
			
		}
				
		for (int i = 0; i < novi_niz.length; i++) {
			System.out.print(novi_niz[i] + "   ");
		}
		
	}

	public static void main(String[] args) {

		double[] A = {-15, 1, 3, 24, -8, 7};
		
		double max = funkcija_a(A);
		System.out.println("Najveci element niza A je: " + max);
		
		double min = funkcija_b(A);
		System.out.println("Najmanji element niza A je: " + min);

		double srednja_vrijednost = funkcija_c(A);
		System.out.println("Srednja vrijednost elemenata niza A je: " + srednja_vrijednost);

		funkcija_d(A);
		
	}

}