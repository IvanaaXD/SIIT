package vjezbe_4;

import java.util.Scanner;

public class Krug {

	double x;
	double y;
	double r;
	
	Krug(double x, double y, double r){
		
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	
	public void povrsina(double r) {
		
		double povrsina = r * r * Math.PI;
		System.out.println("Povrsina je: " + povrsina);
		
	}
	
	public void obim(double r) {
		
		double obim = 2 * r * Math.PI;
		System.out.println("Obim je: " + obim);
		
	}
	
	public void provjera(double X, double Y) {
		
		double d = Math.sqrt((x - X)*(x - X) + (y - Y)*(y - Y));
		
		if (d < r) {
			System.out.println("Tacka se nalazi unutar kruga.");
		}
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Unesite koordinate tacke [x,y]");
		String unos = sc.nextLine();
		
		String[] wuhu = unos.split(",");
		double a = Double.parseDouble(wuhu[0]);
		double b = Double.parseDouble(wuhu[1]);
		
		System.out.println("Unesite precnik kruga [r]");
		unos = sc.nextLine();
		
		int precnik = Integer.parseInt(unos);
		
		Krug krug = new Krug(a, b, precnik);
		
		krug.povrsina(precnik);
		krug.obim(precnik);
		
		System.out.println("Unesite koordinate druge tacke [x,y]");
		unos = sc.nextLine();
		
		wuhu = unos.split(",");
		double c = Double.parseDouble(wuhu[0]);
		double d = Double.parseDouble(wuhu[1]);
		
		krug.provjera(c, d);
		
		sc.close();
	}

}
