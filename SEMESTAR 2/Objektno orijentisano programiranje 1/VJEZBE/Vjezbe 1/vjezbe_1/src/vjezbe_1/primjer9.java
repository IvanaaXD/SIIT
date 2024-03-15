package vjezbe_1;

public class primjer9 {

	public static void main(String[] args) {

		int godina = 2014;
		
		if (godina == 2014) {
			System.out.println("Godina jeste tekuca.");
		}
		
		int a = 3;
		
		if (a > -10 && a < 10) {
			System.out.println("Broj je jednocifren.");
		}
		else {
			System.out.println("Broj je dvocifren.");
		}
		
		double kurs = 108.9;
		
		if (kurs < 110) {
			System.out.println("Evro pada.");
		}
		else if (kurs >= 110 && kurs <= 115){
			System.out.println("Evro stagnira.");
		}
		else {
			System.out.println("Evro raste.");
		}
		
	}

}
