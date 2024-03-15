package vjezbe_3;
import java.util.Scanner;

public class zadatak1 {

	static Scanner scanner = new Scanner(System.in);
	
	static String unesiString() {
		
		do {
			System.out.println("Unesite string: ");
			
			try {
				String slova = scanner.nextLine();
				return slova;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Nije unijet string.");
			}
			
		} while (true);
	}
	
	
	public static void main(String[] args) {

		unesiString();
		
		scanner.close();
		
	}

}
