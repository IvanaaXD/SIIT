package vjezbe_1;

public class zadatak7 {

	public static void main(String[] args) {

		double x = 5;
		double y = 4.5;
		double z;
		double maks;
		double min;
		
		maks = java.lang.Math.max(x,y);
		min = java.lang.Math.min(x,y);
		
		if (x < y) {
			z = maks /(1 + java.lang.Math.abs(min)); 
		} else {
			z = maks / (1 + min); 
		}
		
		System.out.println("Broj z je: " + z);
	}

}
