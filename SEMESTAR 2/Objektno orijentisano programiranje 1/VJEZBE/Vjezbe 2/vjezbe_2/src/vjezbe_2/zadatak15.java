package vjezbe_2;

public class zadatak15 {

	public static void main(String[] args) {

		int n = 7;
		String minus = "-";
		String zvijezda = "*";
		String wuhu = "";
		String minus1 = "";
		String minus2 = "";
		String zvijezde = "";
		
		int brojac1 = n;
		int brojac2 = 0;
		for (int i = 0; i < n; i++) {
			
			minus1 = minus.repeat(brojac1);
			minus2 = minus.repeat(brojac1);
			zvijezde = zvijezda.repeat(brojac2) + zvijezda + zvijezda.repeat(brojac2);

			wuhu = minus1 + zvijezde + minus2;
			System.out.println(wuhu);
			
			brojac2 += 1;
			brojac1 -= 1;

		}
		
		brojac1 = n;
		brojac2 = 0;
		for (int i = n; i >= 0; i--) {
			
			minus1 = minus.repeat(brojac2);
			minus2 = minus.repeat(brojac2);
			zvijezde = zvijezda.repeat(brojac1) + zvijezda + zvijezda.repeat(brojac1);

			wuhu = minus1 + zvijezde + minus2;
			System.out.println(wuhu);
			
			brojac2 += 1;
			brojac1 -= 1;

		}
		
	}

}