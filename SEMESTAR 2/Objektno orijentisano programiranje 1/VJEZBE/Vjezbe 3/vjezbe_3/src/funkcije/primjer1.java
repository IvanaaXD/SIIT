package funkcije;

public class primjer1 {
	
	static void pozdrav() {
		System.out.println("Hello world");
	}

	public static void main(String[] args) {

		pozdrav();
		
		primjer1.pozdrav();
		
		/*Funkcija nije staticka
		primjer2 objekat = new primjer2();
		objekat.pozdrav();
		*/
		
	}

}
