package passwords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordStorage {

	// metoda koja proverava da li unesena lozinka odgovara snimljenoj
	public static boolean authenticate(String attemptedPassword, byte[] storedPassword, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// unesena lozinka se hashuje sa odgovarajucim salt-om
		byte[] hashedAttemptedPassword = hashPassword(attemptedPassword, salt);
		// hash-evi se porede
		return Arrays.equals(storedPassword, hashedAttemptedPassword);
	}

	public static byte[] hashPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		// kao hashing algoritam se koristi Password Based Key Derivation Function 2
		// sa hash message authentication kodom i u osnovi koristi SHA1 algoritam
		// ovo je ujedno i FIPS preporuka za cuvanje lozinki
		String algorithm = "PBKDF2WithHmacSHA1";
		// SHA1 hash je duzine 160 bita
		int derivedKeyLength = 160;
		// broj iteracija se moze podesavati i na taj nacin povecati ili smanjiti
		// slozenost hashiranja
		// veci broj znaci vise iteracija hash-iranja
		// poredjenja radi iPhone 4 i noviji za skladistenje lozinki koristi 10 hiljada
		// iteracija ove funkcije
		// broj iteracija treba korigovati imajuci na umu moguc veliki broj zahteva za
		// autentifikacijom
		// (recimo kod web servera) kako se ne bi previse zauzimali resursi
		int iterations = 20000;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		// vratimo hashiranu vrednost lozinke
		// return f.generateSecret(spec).getEncoded();
		SecretKey sk = f.generateSecret(spec);

		return sk.getEncoded();

	}

	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		// vrlo je vazno pri svim kriptografskim operacijama koristiti SecureRandom
		// generator
		// pseudo slucajnih brojeva
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}

	// pomocna funkcija za enkodovanje bajtova u string
	public static String base64Encode(byte[] data) {
		String result = null;
		result = Base64.getEncoder().encodeToString(data);
		return result;
	}

	// pomocna funkcija za dekodovanje stringa u bajt niz
	public static byte[] base64Decode(String base64Data) throws IOException {
		return Base64.getDecoder().decode(base64Data);
	}

	// primer koriscenja
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		// umesto citanja passwora iz baze recimo, ovde imamo hardkodirani password
		// naravno, iz baze bi se citao vec hashirani
		String password = "toomanysecrets";
		// i prethodno generisani salt bi bio snimljen u bazu ...
		byte[] salt = generateSalt();
		// cisto da bi videli kako izgleda ...
		System.out.println("Salt is: " + base64Encode(salt));
		byte[] hashedPassword = hashPassword(password, salt);
		System.out.println("Hashed password is: " + base64Encode(hashedPassword));
		// hashedPassword sluzi kao snimljena lozinka u bazi recimo ...
		// naravno cuva se i salt koji ne mora biti nikako zasticen jer se ne smatra
		// tajnim podatkom
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Password:");
		String attemptedPassword = in.readLine();

		if (authenticate(attemptedPassword, hashedPassword, salt)) {
			System.out.println("ACCESS GRANTED");
		} else {
			System.out.println("ACCESS DENIED");

		}
	}
}
