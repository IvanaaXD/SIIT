package ulaz_izlaz_tastatura;

import java.util.Scanner;

public class primjer2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Unesi recenicu: ");
		String text = sc.nextLine();
		System.out.println("Ispis recenice: " + text);
		
		System.out.print("Unesi broj: ");
		int broj = 0;
		try {
			broj = sc.nextInt();
		} catch(Exception e){
			System.out.println("Niste unijeli broj");
		}
		System.out.println("Ispis broja: " + broj);
		
		System.out.print("Unesi realan broj: ");
		float r_broj = sc.nextFloat();
		System.out.println("Ispis realnog broja: " + r_broj);
		
		sc.close();
		System.out.println("Program izvrsen");
	}

}
