package vjezbe_2;

public class zadatak4 {

	public static void main(String[] args) {

		int matrica [] [] = new int [2][5];
		
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				matrica [i][j] = j;
			}
		}
		
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica [i][j] + " ");
			}
			System.out.print("\n");
		}
		
	}

}