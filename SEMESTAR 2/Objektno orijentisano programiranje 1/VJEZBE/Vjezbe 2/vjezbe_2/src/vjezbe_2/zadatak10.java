package vjezbe_2;

public class zadatak10 {

	public static void main(String[] args) {
		
		int[][] matrica = new int [5][5];
		
		int brojac = 1;
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				matrica[i][j] = brojac;
				brojac += 1;
			}
		}
		
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica[i][j] + "   ");
			}
			System.out.print("\n");
		}
		
		System.out.println("\n");
		
		int zamjena1 = 0;
		int zamjena2 = 0;
		int k = 0;
		int br = 0;
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				
				
				if (zamjena1 != 0 && zamjena2 != 0) {
					continue;
				}
				else {
					zamjena1 = matrica[i][br];
					k = matrica[i].length - (br + 1);
					zamjena2 = matrica[i][k];
					
					if (k != br) {
						matrica[i][br] = zamjena2;
						matrica[i][k] = zamjena1;
					}
				}
									
			}
						
			zamjena1 = 0;
			zamjena2 = 0;
			
			br += 1;

		}
		
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[i].length; j++) {
				System.out.print(matrica[i][j] + "   ");
			}
			System.out.print("\n");
		}
		
	}

}