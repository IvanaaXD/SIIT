package vjezbe_1;

public class zadatak6 {

	public static void main(String[] args) {

		double r = 6;
		double h = 4;
		
		double s = Math.sqrt(h*h + r*r/4);
		
		double povrsina = (r/2)*Math.PI*(s + r/2);
		
		System.out.println("Povrsina kupe je: " + povrsina);
		
	}

}
