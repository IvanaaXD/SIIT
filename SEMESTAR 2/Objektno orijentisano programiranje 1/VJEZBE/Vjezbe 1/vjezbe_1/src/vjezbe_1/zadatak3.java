package vjezbe_1;

public class zadatak3 {

	public static void main(String[] args) {

		double godina = 2020;
		
		if (godina >= 1538 && godina <= 10000) {
				
			if (godina%4==0) {
				System.out.println("Unijeta godina je prestupna.");
			}
			else if (godina%400==0) {
				System.out.println("Unijeta godina je prestupna.");
			}
			else {
				System.out.println("Unijeta godina nije prestupna.");
			}
			
		} else
			System.out.println("Pogrešan unos!");			
	
	}
}