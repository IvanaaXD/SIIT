package zadaci_funkcije_i_stringovi;
import java.util.Scanner;

public class zadatak5 {
	
	static void funkcija_a(String a){
				
		String drama[] = a.split("\n");
		String nazivi[] = new String[drama.length];
		
		for (int i = 0; i < drama.length; i++) {
			
			String jej = drama[i];
			String wuhu[] = jej.split("\\|");
			
			for (int j = 0; j < wuhu.length; j++) {
				
				nazivi[i] = wuhu[0];
			}
						
		}
		
		for (int i = 0; i < nazivi.length; i++) {
			System.out.println((i + 1) + ". " + nazivi[i]);		}
		
	}
	
	static void funkcija_b(String a){
		
		funkcija_a(a);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Unesite broj artikla: ");
		int broj = sc.nextInt();
		
		String drama[] = a.split("\n");
		
		for (int i = 0; i < drama.length; i++) {
			
			String jej = drama[i];
			String wuhu[] = jej.split("\\|");
			
			if (broj == i + 1) {

				for (int j = 1; j < wuhu.length; j++) {
					
					System.out.print(wuhu[j] + ", ");
				}
			}
						
		}
		
		sc.close();
		
	}
	
	static void funkcija_c(String a){
		
		String drama[] = a.split("\n");
		
		for (int i = 0; i < drama.length; i++) {
			
			String jej = drama[i];
			String wuhu[] = jej.split("\\|");
			int broj = wuhu.length - 1;
						
			if (wuhu[broj].equals("akcija")) {
				
				System.out.println(jej);
			}
						
		}
		
	}

	
	static void funkcija_d(String a){
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Unesite proizvodjaca: ");
		String text = sc.nextLine();
		
		String drama[] = a.split("\n");
		
		for (int i = 0; i < drama.length; i++) {
			
			String jej = drama[i];
			String wuhu[] = jej.split("\\|");
			
			if (text.equals(wuhu[2])) {
					
				System.out.println(jej);
					
			}
						
		}
		
		sc.close();
		
	}

	public static void main(String[] args) {

		String a = "Coko plazma|s01|Bambi|85.30|akcija\nSmoki|s02|Stark|55.00|nije na akciji\nCipsi|s03|Marbo |115.20|nije na akciji\nKrem Bananica|s04|Stark|11.00|akcija\n";
		
		System.out.println("=====Meni=====");
		System.out.println("1. Nazivi svih artikala");
		System.out.println("2. Podaci odredjenog artikla");
		System.out.println("3. Artikli na akciji");
		System.out.println("4. Artikli odredjenog proizvodjaca");
		System.out.println("x. Izlazak iz aplikacije");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Unesite zeljenu opciju: ");
		String text = sc.nextLine();
		
		switch(text) {
		case "1": funkcija_a(a);
				break;
		case "2": funkcija_b(a);
				break;
		case "3": funkcija_c(a);
				break;
		case "4": funkcija_d(a);
				break;
		case "x": System.out.println("Izlazak iz aplikacije");
				break;
		default: System.out.println("Pogresan unos");
				break;
		}
		
		sc.close();
		
	}

}
