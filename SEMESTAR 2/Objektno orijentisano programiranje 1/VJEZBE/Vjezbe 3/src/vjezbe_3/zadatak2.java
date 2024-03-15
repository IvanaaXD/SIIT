package vjezbe_3;
import java.util.Scanner;

public class zadatak2 {
	
	static Scanner scanner = new Scanner(System.in);
	
	static int unesiBroj() {
		
		do {
			System.out.println("Unesite cijeli broj: ");
			
			try {
				int broj = scanner.nextInt();
				return broj;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Nije unijet cijeli broj.");
				}
				
			} while (true);
		}
		
		
	public static void main(String[] args) {
	
		unesiBroj();
			
		scanner.close();
			
	}
	
}

