package vjezbe_2;

public class zadatak5 {

	public static void main(String[] args) {

		int proizvod = 1;
		for (int i = 10; i <= 20; i++) {
			if (i%2==0) {
				proizvod = proizvod * i;
			}
		}
		System.out.println("Proizvod je: " + proizvod);
	}

}
