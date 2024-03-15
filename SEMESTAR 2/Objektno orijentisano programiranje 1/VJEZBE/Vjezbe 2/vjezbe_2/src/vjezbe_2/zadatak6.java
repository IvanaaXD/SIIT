package vjezbe_2;

public class zadatak6 {

	public static void main(String[] args) {

		int dani_a = 0;
		for (int i = 2000; i <= 2016; i++) {
			dani_a = dani_a + 12*30;
		}
		System.out.println("Izmedju 2000. i 2016. godine proslo je: " + dani_a + " dana.");
		
		int dani_b = 0;
		for (int i = 2000; i <= 2016; i++) {
			for (int j = 1; j <= 12; j++) {
				if (j%2==0) {
					dani_b = dani_b + 12*30;
				}
				else {
					dani_b = dani_b + 12*31;
				}
			}
		}
		System.out.println("Izmedju 2000. i 2016. godine proslo je: " + dani_b + " dana.");
		
		int dani_c = 0;
		for (int i = 2000; i <= 2016; i++) {
			
			if (i ==2016) {
				dani_c = dani_c + 30 + 31 + 10;
				}
			
			else {
				for (int j = 1; j <= 12; j++) {
					if (j%2==0) {
						dani_c = dani_c + 12*30;
					}
					else {
						dani_c = dani_c + 12*31;
					}
				}
			
			}
		}
		System.out.println("Izmedju 2000. i 2016. godine proslo je: " + dani_c + " dana.");
	
	}
}
