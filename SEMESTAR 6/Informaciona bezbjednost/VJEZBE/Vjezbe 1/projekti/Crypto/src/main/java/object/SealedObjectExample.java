package object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SealedObjectExample {

	private static final String KEY_FILE = "dataKey1.key";

	// private SecretKey secretKey;

	public void testIt() {
		// kriptovanje
		SealedObject sealedObject = encrypt();
		// dekriptovanje
		decrypt(sealedObject);
	}

	/**
	 * 
	 * Kreira tajni kljuc
	 */
	private SecretKey createSecretKey() {
		try {
			// Kreira se (jedan) tajni kljuc za DES algoritam
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			return keygen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Snima tajni kljuc
	 */
	private void storeSecretKey(SecretKey key, String fileName) {
		try {

			URI uri = SealedObjectExample.class.getClassLoader().getResource(fileName).toURI();
			Path path = Paths.get(uri);
			// enkodiran kljuc
			byte[] keyBytes = key.getEncoded();
			// snimaju se bajtovi
			File kekFile = new File(path.toString());
			FileOutputStream f = new FileOutputStream(kekFile);
			f.write(keyBytes);
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Ucitava tajni kljuc iz fajla
	 */
	private static SecretKey loadSecretKey(String fileName) {

		try {

			URI uri = SealedObjectExample.class.getClassLoader().getResource(fileName).toURI();
			Path path = Paths.get(uri);
			byte[] bytes = Files.readAllBytes(path);
			// Od bajtova se kreira DES specifikacija
			// bajtovi su od enkodiranog kljuca
			DESKeySpec keySpec = new DESKeySpec(bytes);
			// key factory transformise u objekat SecretKey
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			SecretKey key = skf.generateSecret(keySpec);

			return key;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Kriptovanje
	 */
	private SealedObject encrypt() {
		try {
			String txt = "Ovo je poruka koja se kriptuje";

			System.out.println("Generise se tajni kljuc...");
			// generisanje kljuca
			SecretKey secretKey = createSecretKey();
			// snimanje kljuca
			storeSecretKey(secretKey, KEY_FILE);

			System.out.println("Kriptuje se " + txt);

			// kriptovanje poruke
			Cipher desCipherEnc = Cipher.getInstance("DES/ECB/PKCS5Padding");

			desCipherEnc.init(Cipher.ENCRYPT_MODE, secretKey);
			// Objekat sadrzi objekat koji se kriptuje - txt
			SealedObject sealedObject = new SealedObject(txt, desCipherEnc);

			return sealedObject;

		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Dekriptovanje
	 */
	private void decrypt(SealedObject sealedObject) {
		try {

			SecretKey secretKey = loadSecretKey(KEY_FILE);

			String receivedTxt = (String) sealedObject.getObject(secretKey);

			System.out.println("Primljeni text " + receivedTxt);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SealedObjectExample testIt = new SealedObjectExample();
		testIt.testIt();
	}
}
