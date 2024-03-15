package vjezbe_2;

public class pimjer2 {

	public static void main(String[] args) {

		int suma = 0;
		
		for (int i = 2; i  <= 9; i++) {
			if (i%2==0) {
				suma += i;
			}
		}
		
		System.out.println("Suma je: " + suma);
	}

}
