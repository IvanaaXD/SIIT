package stringovi;

public class primjer1 {

	public static void main(String[] args) {

		String a = "Ovo je tekst ";
		System.out.println(a);
		
		String b = a + 5 + " zadataka";
		System.out.println(b);
		
		System.out.println("Broj elemenata Stringa a je: " + a.length());
		
		System.out.println("Prvi karakter: " + a.charAt(0));
		System.out.println("Drugi karakter: " + a.charAt(1));
		
		System.out.println("Posljednji element Stringa a je: " + a.charAt(a.length() - 1));
		
	}

}