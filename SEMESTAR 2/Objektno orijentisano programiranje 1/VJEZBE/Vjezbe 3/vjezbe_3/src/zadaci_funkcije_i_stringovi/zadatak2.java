package zadaci_funkcije_i_stringovi;

public class zadatak2 {
	
	static void funkcija(String a) {
		
		String redovi[] = a.split(";");
		String red;
		
		for (int i = 0; i < redovi.length; i++) {
			
			red = redovi[i].toString();
							
			String kolone[] = red.split(","); 
			
			for (int j = 0; j < kolone.length; j++) {
				
				System.out.print(kolone[j] + "   ");

			}
			System.out.println();
				
		}
		
	}

	public static void main(String[] args) {
		
		String a = "4,3,2,1;0,1,0;1,2,3,4";
		
		funkcija(a);
		
	}

}