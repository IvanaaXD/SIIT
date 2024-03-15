package vjezbe_1;

public class primjer6 {

	public static void main(String[] args) {

		int a = 1;
		a += 2;
		
		System.out.println("Vrijednost uvecane promjenljive " + "a=1 za 2 je: " + a);
		
		final int B = 5;
		a -= B;
		
		System.out.println("Vrijednost umanjene promjenljive " + "a=3 za B=5 je: " + a);

		float c = 1.3F;
		a = a + (int)c;
		
		System.out.println("Uvecanje promjenljive " + "a=-2 za c=1.3 je: " + a);
	}

}
