package funkcije;

public class primjer5 {

	static int faktorijel(int n) {
		
		if (n <= 1) {
			return 1;
		}
		else {
			return n * faktorijel(n-1);
		}
	}
	
	public static void main(String[] args) {

		int n = 10;
		
		int drama = faktorijel(n);
		
		System.out.println("Faktorijel broja " + n + " je " + drama + ".");
		
	}

}
