package digest;

import java.util.Base64;

public class ParityWord {
	
	public static void main(String[] args) {
		String ulaz = "NEki ulaz";
		byte[] razdeljenUlaz = ulaz.getBytes();
		
		byte rezultat = razdeljenUlaz[0];
		for(int i = 1; i<razdeljenUlaz.length; i++) {
			rezultat = (byte) (rezultat ^ razdeljenUlaz[i]);
		}
		
		byte[] rezultatUNizu = new byte[1];
		rezultatUNizu[0] = rezultat;
		
		String zaIspis = Base64.getEncoder().encodeToString(rezultatUNizu);
		System.out.println(zaIspis);
		
		
	}

	
	
}
