package zadaci_funkcije_i_stringovi;

public class zadatak1 {
	
	static char[] funkcija_a(String a) {
		
		char[] meh = a.toCharArray();
		char wuhu [] = new char [meh.length];
		
		int brojac = 0;
		for (int i = meh.length - 1; i >= 0; i--) {
			wuhu[brojac] = meh[i];
			brojac += 1;
			
		}
		
		return wuhu;
	}
	
	static String[] funkcija_b(String a) {
		
		String[] b = a.split(" ");		
		
		for (int i = b.length - 1; i >= 0; i--) {

			System.out.println(b[i]);
			
		}
		return b;
	}

	public static void main(String[] args) {

		String a = ("Norvežanin Kjetil Jansrud osvojio je zlatnu medalju u superveleslalomu na Zimskim\r\n"
				+ "olimpijskim igrama u Sočiju pošto je za 30 stotih delova sekunde bio brži od\r\n"
				+ "drugoplasiranog Amerikanca Endrjua Vajbrehta.");
		
		char[] wuhu = funkcija_a(a);
		
		for (int i = 0; i < wuhu.length; i++) {
			
			System.out.print(wuhu[i]);
			
		}
		
		System.out.println("\n");
		
		funkcija_b(a);
		
	}

}
