package vjezbe_3;

import java.util.Scanner;

public class zadatak3 {
	
	static Scanner scanner = new Scanner(System.in);
	
	static float unesiBroj() {
		
		do {
			System.out.println("Unesite broj: ");
			
			try {
				float broj = scanner.nextFloat();
				return broj;
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println("Nije unijet broj.");
				}
				
			} while (true);
		}
		
		
	public static void main(String[] args) {
	
		unesiBroj();
			
		scanner.close();
			
	}
	
}