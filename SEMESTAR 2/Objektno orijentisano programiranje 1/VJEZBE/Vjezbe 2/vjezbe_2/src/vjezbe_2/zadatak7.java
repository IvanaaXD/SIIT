package vjezbe_2;

public class zadatak7 {

	public static void main(String[] args) {

		int niz [] = {-10,3,16,1,4,-2};
		
		int min = 0;
		int max = 0;
		double suma = 0;
		double brojac = 0;
		double srednja_vrijednost = 0;

		
		for (int i = 0; i <= niz.length-1; i++) {
			
			if (niz[i] > max) {
				max = niz[i];
			}
			
			if (niz[i] < min) {
				min = niz[i];
			}
			
			suma += niz[i];
			brojac += 1;
			
		}
		
		srednja_vrijednost = suma/brojac;
		
		System.out.println("Srednja vrijednost niza je: " + srednja_vrijednost);
		System.out.println("Minimum ovog niza je: " + min + " a maksimum je: " + max);
		System.out.println("Clanovi niza koji su manji od njegove srednje vrijednosti su: ");
		
		for (int i = 0; i <= niz.length-1; i++) {
			
			if (niz[i] > 0 && niz[i] < srednja_vrijednost) {
				System.out.println(niz[i] + " ");
			}
		}
	}

}
