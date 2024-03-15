package vjezbe_1;

public class zadatak8 {

	public static void main(String[] args) {

		double a = 1;
		double b = -3;
		double c = 2;
		double x1;
		double x2;
		
		double D = Math.sqrt(b*b - 4*a*c);
		
		if (D < 0) {
			System.out.println("Jednacina nema realnih rjesenja.");
		} 
		
		else if (D == 0){
			
			x1 = (b*b + D)/(2*a);
			
			System.out.println("Jednacina ima jedno realno rjesenje i to je: " + x1);
		}
		
		else {
			
			x1 = (b*b + D)/(2*a);
			x2 = (b*b - D)/(2*a);
			
			System.out.println("Jednacina ima realna rjesenja i to su: " + x1 + " ," + x2);
		}
		
	}

}
