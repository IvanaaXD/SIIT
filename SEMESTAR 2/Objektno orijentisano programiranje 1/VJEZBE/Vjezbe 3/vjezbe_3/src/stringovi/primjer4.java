package stringovi;

public class primjer4 {

	public static void main(String[] args) {

		String a = "Tekst koji se sastoji od 7 rijeci";
		System.out.println(a);
		
		String rijeci [] = a.split(" ");
		System.out.println("String se sastoji od " + rijeci.length + " rijeci.");
		
		for (int i = 0; i < rijeci.length; i++) {
			System.out.println((i + 1) + " rijec u stringu a je: " + rijeci[i]);
		}
		
		String stringBrojeva = "14;2;-3;5";
		String brojevi [] = stringBrojeva.split(";");
		
		System.out.println("String a se sastoji od " + brojevi.length + " brojeva.");
		
		for (int i = 0; i < brojevi.length; i++) {
			System.out.println((i + 1) + " broj u stringu stringBrojeva je: " + brojevi[i]);
		}
		
	}

}
