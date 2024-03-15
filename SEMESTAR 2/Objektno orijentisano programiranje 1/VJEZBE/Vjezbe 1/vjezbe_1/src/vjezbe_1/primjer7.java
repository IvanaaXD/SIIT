package vjezbe_1;

public class primjer7 {

	public static void main(String[] args) {

		double povrsina;
		int r = 2;
		
		povrsina = r * r * Math.PI;
		System.out.println("Povrsina kruga je: " + povrsina);
		
		povrsina = Math.pow(r, 2) * Math.PI;
		System.out.println("Povrsina kruga je: " + povrsina);

	}

}
