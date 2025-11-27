package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


//Primer simetricnog sifrovanja AES algoritmom, u CBC modu.
//Moze da se sifruje podatak prozivoljne duzine
public class SymmetricAES {
	
	private static short BLOCK_SIZE = 16;//Velicina bloka u AES algoritmu. Ne menja se u zavisnosti od velicine kljuca.
	
	private String data = "Ovo su podaci koji se kriptuju simetricnim AES algoritmom, duzina podataka nije bitna, tj. i AES moze da se koristi za proizvoljnu duzinu podataka";


	public SymmetricAES() {
	}
	
	public void testIt() {
		System.out.println("Generisanje kljuca:");
		SecretKey secretKey = generateKey();
		IvParameterSpec ivParameterSpec = generateIV();
		System.out.println("Radi kriptovanje/dekriptovanje:");
		transfer(secretKey, ivParameterSpec);
		
	}
	
	private SecretKey generateKey() {
        try {
			//generator para kljuceva za AES algoritam
			KeyGenerator   keyGen = KeyGenerator.getInstance("AES"); 
			//generise kljuc za AES, defaultne velicine od 128 bita
			SecretKey secretKey = keyGen.generateKey();
			return secretKey;
			
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	private IvParameterSpec generateIV() {
		byte []iv = new byte[BLOCK_SIZE];
		
		//Inicijalizacioni vektor je nasumicni niz bajtova.
		//U zavisnosti od moda, IV mora da zadovoljava kriterijum da se
		//NE ponavlja za isti kljuc, i da NE sme biti predvidiv.
		//NE mora biti tajna.
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		return ivParameterSpec;
	}
	
	private void transfer(SecretKey secretKey, IvParameterSpec ivParameterSpec) {
			
		try {
			System.out.println("Kriptuje se: " + data);	
			//klasa za sifrovanje
			Cipher aesCipherEnc = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//inicijalizacija za sifrovanje,
			//isti inicijalizacioni vektor mora se koristi prilikom enkripcije
			//i prilikom dekripcije
			aesCipherEnc.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			
			//sifrovanje
			byte[] ciphertext = aesCipherEnc.doFinal(data.getBytes());
			System.out.println("Kriptovan text: " + Base64.getEncoder().encodeToString(ciphertext));
			
			//desifrovanje poruke 
			//algoritam MORA biti isti kao i kod sifrovanja, provider moze da se razlikuje
			Cipher aesCipherDec = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//inicijalizacija za dekriptovanje
			aesCipherDec.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			
			//dekriptovanje
			byte[] receivedTxt = aesCipherDec.doFinal(ciphertext);
			System.out.println("Primljeni text: " + new String(receivedTxt));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		SymmetricAES test = new SymmetricAES();
		test.testIt();
	}
}

