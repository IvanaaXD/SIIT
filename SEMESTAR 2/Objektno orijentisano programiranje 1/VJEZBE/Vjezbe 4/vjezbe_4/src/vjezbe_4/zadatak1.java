package vjezbe_4;

import java.util.Scanner;

public class zadatak1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Unesite koordinate tacki A i B [Xa,Ya,Xb,Yb]");
		String unos = sc.nextLine();
		
		String[] wuhu = unos.split(",");
		double a1 = Double.parseDouble(wuhu[0]);
		double a2 = Double.parseDouble(wuhu[1]);
		double b1 = Double.parseDouble(wuhu[2]);
		double b2 = Double.parseDouble(wuhu[3]);
		
		Pravougaonik pravougaonik = new Pravougaonik(a1,a2,b1,b2);
		
		double stranicaA = Math.abs(a1 - b1);
		double stranicaB = Math.abs(a2 - b2);
		//Pravougaonik stranice = new Pravougaonik(stranicaA, stranicaB);
		
		pravougaonik.povrsina(stranicaA, stranicaB);
		pravougaonik.obim(stranicaA, stranicaB);
		
		System.out.println("Unesite koordinate tacke [x,y]");
		unos = sc.nextLine();
		
		wuhu = unos.split(",");
		double c = Double.parseDouble(wuhu[0]);
		double d = Double.parseDouble(wuhu[1]);
		
		pravougaonik.pripadnost(a1, a2, b1, b2, c, d);
		
		sc.close();
		
	}

}

class Pravougaonik {
	
	double Xa;
	double Ya;
	double Xb;
	double Yb;
	
	Pravougaonik(double Xa, double Ya, double Xb, double Yb){
		
		this.Xa = Xa;
		this.Ya = Ya;
		this.Xb = Xb;
		this.Yb = Yb;
		
	}
		
	double stranicaA;
	double stranicaB;
	
	Pravougaonik(double stranicaA, double stranicaB){
		
		this.stranicaA = stranicaA;
		this.stranicaB = stranicaB;
		
	}
	
	public void povrsina(double stranicaA, double stranicaB) {
		
		double p = stranicaA * stranicaB;
		
		System.out.println("Povrsina pravougaonika je: " + p);
		
	}
	
	public void obim(double stranicaA, double stranicaB) {
		
		double o = 2*(stranicaA + stranicaB);
		
		System.out.println("Obim pravougaonika je: " + o);
		
	}
	
	public void pripadnost(double Xa, double Ya, double Xb, double Yb, double X, double Y) {
		
		int jestex = 0;
		int jestey = 0;
		
		if (Xa < Xb) {
			if (X > Xa && X < Xb) {
				jestex = 1;
			}
		} else if(Xa > Xb){
			if (X > Xb && X < Xa) {
				jestex = 1;
			}
		} else {
			jestex = 0;
		}
		
		if (Ya < Yb) {
			if (Y > Ya && Y < Yb) {
				jestey = 1;
			}
		} else if(Ya > Yb){
			if (Y > Yb && Y < Ya) {
				jestey = 1;
			}
		} else {
			jestey = 0;
		}
		
		if (jestex == 1 && jestey == 1) {
			System.out.println("Tacka se nalazi unutar pravougaonika.");
		} else {
			System.out.println("Tacka se ne nalazi unutar pravougaonika.");
		}
		
	}
	
}
