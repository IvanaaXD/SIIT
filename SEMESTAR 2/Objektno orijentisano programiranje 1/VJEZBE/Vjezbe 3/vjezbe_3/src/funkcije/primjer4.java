package funkcije;

public class primjer4 {

	static double hipotenuza(double a, double b) {
		
		double c = 0;
		
		c = Math.sqrt(a*a + b*b);
		return c;
	}
	
	static double hipotenuza(double ab) {
		return hipotenuza(ab, ab);
	}
	
	public static void main(String[] args) {
		
		double a = 3;
		double b = 4;
		double ab = 5;
		
		double c = hipotenuza(a, b);
		System.out.println("Vrijednost hipotenuze je: " + c);
		
		c = hipotenuza(ab);
		System.out.println("Vrijednost hipotenuze je: " + c);

	}

}
