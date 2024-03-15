package vjezbe_1;

public class zadatak2 {

	public static void main(String[] args) {

		double povrsina_kvadrata = 16;
		double osnovica_trougla = 4;
		double krak_trougla = 6;
		
		double stranica_kvadrata = Math.sqrt(povrsina_kvadrata);
		
		double visina_trougla = Math.sqrt(krak_trougla * krak_trougla - osnovica_trougla * osnovica_trougla / 4);
		double povrsina_trougla = visina_trougla * osnovica_trougla / 2;
		
		System.out.println("Stranica kvadrata je: " + stranica_kvadrata);
		System.out.println("Površina jednakokrakog trougla je: " + povrsina_trougla);
	}

}

