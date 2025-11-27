package crypto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestCaesar {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(System.in)); 
		
		System.out.println("Unesite originalnu poruku: ");		
		String plainText = reader.readLine();		
		plainText = plainText.replaceAll(" ","");
		
		System.out.println("Unesite kljuc(0-25): ");
		int key = input.nextInt();
		
		// --- sifrovanje ---
		
		String chiperText = CaesarExample.encrypt(plainText, key);
		System.out.println("Sifrovana poruka je: " + chiperText);
		
		// --- desifrovanje ---
		
		System.out.println("Unesite poruku koju treba desifrovati: ");
		chiperText = input.next();
		
		String result = CaesarExample.decrypt(chiperText, key);
		System.out.println("Desifrovana poruka je: " + result);
		
		input.close();

	}

}
