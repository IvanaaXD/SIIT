package vjezbe_1;

public class primjer8 {

	public static void main(String[] args) {

		boolean a = true, b = false, c = true;
		int d = 2, e = 5;
		
		System.out.println("Vrijednost konjukcije a i b je: " + (a && b));
		System.out.println("Vrijednost konjukcije a i c je: " + (a && c));
		System.out.println("Vrijednost dosjunkcije a ili b je: " + (a || b));
		System.out.println("Vrijednost slozenog izraza (a i b) ili c je: " + ((a && b) || c) );

		System.out.println("Vrijednost relacije d vece od e je: " + (d > e));
		System.out.println("Vrijednost relacije d manje i jednako od e je: " + (d <= e));
		System.out.println("Vrijednost relacije d jednako e je: " + (d == e));
		System.out.println("Vrijednost relacije d razlicito od e je: " + (d != e));

		boolean rezultat;
		rezultat = (d > e) && a;
		System.out.println("Vrijednost rezultata konjukcije relacije " + "(d vece od e) i promjenljive a je: " + rezultat);
		
	}

}
