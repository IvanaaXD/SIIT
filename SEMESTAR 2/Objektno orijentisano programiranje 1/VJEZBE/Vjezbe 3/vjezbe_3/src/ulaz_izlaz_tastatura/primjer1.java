package ulaz_izlaz_tastatura;

public class primjer1 {

	public static void main(String[] args) {

		System.out.println("Koriscjenje izlaznog toka stampanjna na ekran: ");
		System.out.printf("Boolean ispis, vrijednost %b je ispisana\n", true);
		System.out.printf("Slovo ispis, vrijednost %c je ispisana\n", 'A');
		System.out.printf("Integer ispis, vrijednost %d je ispisana\n", 15);
		System.out.printf("Heksadecimalni ispis, vrijednost %H je ispisana\n", 15);
		System.out.printf("Real ispis, vrijednost %f je ispisana\n", 10.5);
		System.out.printf("Real ispis, vrijednost %e je ispisana\n", 12300000000.123);
		System.out.println("Formatizovani ispis u obliku %[sirina][.preciznost]tip");
		System.out.printf("Real ispis, vrijednost %.2f je ispisana\n", 100.454643);
		
	}

}
