package funkcije;

public class primjer2 {
	
	static double hipotenuza(double a, double b) {
		
		double c = 0;
		
		c = Math.sqrt(a*a + b*b);
		return c;
		
	}

	public static void main(String[] args) {

		double a = 3;
		double b = 4;
		
		double c = hipotenuza(a, b);
		
		System.out.println("Vrijednost hipotenuze je: " + c);
		
	}

}