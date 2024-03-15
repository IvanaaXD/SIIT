package funkcije;

public class primjer6 {
	
	static void promjena_vrijednosti(int [] a) {
		
		a[0] = 100;
		
	}

	public static void main(String[] args) {

		int niz[] = {1,2,3,5};
		promjena_vrijednosti(niz);
		
		for (int el:niz) {
			
			System.out.println("Element niza a je " + el);
			
		}
		
	}

}
