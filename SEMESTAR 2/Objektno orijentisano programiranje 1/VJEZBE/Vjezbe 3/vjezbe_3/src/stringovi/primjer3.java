package stringovi;

public class primjer3 {

	public static void main(String[] args) {

		String a = "Ovo Je Tekst";
		String b = "ovo je tekst";
		
		if (a.equals(b)) {
			System.out.println("1. Nizovi a i b su slicni.");
		}
		else {
			System.out.println("1. Nizovi a i b nisu slicni.");
		}
		
		if (a.equalsIgnoreCase(b)) {
			System.out.println("2. Nizovi a i b su slicni.");
		}
		else {
			System.out.println("2. Nizovi a i b nisu slicni.");
		}
		
		a = a.toLowerCase();
		System.out.println(a);

		b = b.substring(0, 5);
		
		if (a.startsWith(b)) {
			System.out.println("Zapocinje");
		}
		else {
			System.out.println("Ne zapocinje");
		}
		
		if (a.contains("tekst")) {
			System.out.println("Sadrzi rijec");
		}
		else {
			System.out.println("Ne sadrzi rijec");
		}
		
		if ("B".compareTo("A") > 0) {
			System.out.println("poslije");
		} 
		else if ("B".compareTo("A") < 0) {
			System.out.println("prije");
		}
		else {
			System.out.println("isti");
		}
		
	}

}
