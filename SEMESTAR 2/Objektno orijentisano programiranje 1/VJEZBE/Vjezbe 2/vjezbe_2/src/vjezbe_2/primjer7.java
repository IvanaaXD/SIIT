package vjezbe_2;

public class primjer7 {

	public static void main(String[] args) {

		int matricaA[][] = new int [4][7];
		
		for (int i = 0; i < matricaA.length; i++) {
			for (int j = 0; j < matricaA[i].length; j++) {
				matricaA[i][j] = j;
			}
		}
		
		char nizB[][] = {{'a', 'b', 'c'}, {48}, {'1', '2', '3'}};
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.println("a[" + i + "][" + j + "] clan niza je " + matricaA[i][j]);
			}
		}
		
	}

}
