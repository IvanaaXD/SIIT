package vjezbe_2;

public class zadatak9 {

	public static void main(String[] args) {

		int matrica [] [] = new int [7][5];
		
		for (int i = 0; i < matrica.length; i++) {
			int brojac = i;
			for (int j = 0; j < matrica[i].length; j++) {
				matrica [i][j] = brojac;
				brojac += 1;
			}
		}
		
		System.out.println("Matrica je oblika: \n");
		
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica[i][j] + "     ");
			}
			System.out.print("\n");
		}
		
		int suma = 0;
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				if (i == j) {
					suma += matrica[i][j];
				}
			}
		}
		
		System.out.println("\n");
		System.out.println("Suma elemenata na glavnoj dijagonali je: " + suma);
	}
}