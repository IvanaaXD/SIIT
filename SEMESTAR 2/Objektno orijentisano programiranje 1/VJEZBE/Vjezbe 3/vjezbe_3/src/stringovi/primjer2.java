package stringovi;

public class primjer2 {

	public static void main(String[] args) {

		String a = " Ovo je tekst 6. zadatka ";
		System.out.println(a);
		
		a = a.trim();
		System.out.println(a);
		
		String podstring;
		podstring = a.substring(4);
		System.out.println("Podstring 1: " + podstring);
		
		podstring = a.substring(4, 9);
		System.out.println("Podstirng 2: " + podstring);
		
	}

}
