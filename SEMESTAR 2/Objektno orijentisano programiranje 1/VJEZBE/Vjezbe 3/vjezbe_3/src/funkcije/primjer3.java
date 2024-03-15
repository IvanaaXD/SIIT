package funkcije;

public class primjer3 {
	
	static void promjena_vrijednosti(int a) {
		
		a = 5;
		System.out.println("Promjena vrijednosti a unutar funkcije je: " + a);
		
	}

	public static void main(String[] args) {

		int a = 3;
		
		System.out.println("Inicijalna vrijednost a je: " + a);
		
		promjena_vrijednosti(a);
		
		System.out.println("Vrijednost a po povratku iz funkcije je: " + a);
		
	}

}
