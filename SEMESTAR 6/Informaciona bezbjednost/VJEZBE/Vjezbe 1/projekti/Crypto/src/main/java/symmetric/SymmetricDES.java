package symmetric;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

//Primer simetricnog siforvanja DES algoritmom, u ECB modu.
//Moze da se sifruje podatak prozivoljne duzine
public class SymmetricDES {
	
	private SecretKey secretKey;//Duzina kljuca je 64 bita, mada algoritam ne koristi sve.
	private String data = "Ovo su podaci koji se kriptuju simetricnim DES algoritmom, duzina podataka nije bitna, tj. DES moze da se koristi za proizvoljnu duzinu podataka";


	public SymmetricDES() {
	}
	
	public void testIt() {
		System.out.println("Generisanje kljuca:");
		generateKey();
		System.out.println("Radi kriptovanje/dekriptovanje:");
		transfer();
		
	}
	
	private void generateKey() {
        try {
			//generator kljuceva za DES algoritam
			KeyGenerator   keyGen = KeyGenerator.getInstance("DES"); 
			//generise kljuc za DES 
			secretKey = keyGen.generateKey();

         } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private void transfer() {
			
		try {
			System.out.println("Kriptuje se: " + data);	
			//klasa za sifrovanje
			Cipher desCipherEnc = Cipher.getInstance("DES/ECB/PKCS5Padding");
			//inicijalizacija za sifrovanje, 
			desCipherEnc.init(Cipher.ENCRYPT_MODE, secretKey);
			
			//sifrovanje
			byte[] ciphertext = desCipherEnc.doFinal(data.getBytes());
			System.out.println("Kriptovan text: " + Base64.getEncoder().encodeToString(ciphertext));
			
			//desifrovanje poruke 
			//algoritam MORA biti isti kao i kod sifrovanja, provider moze da se razlikuje
			Cipher desCipherDec = Cipher.getInstance("DES/ECB/PKCS5Padding");
			//inicijalizacija za dekriptovanje
			desCipherDec.init(Cipher.DECRYPT_MODE, secretKey);
			
			//dekriptovanje
			byte[] receivedTxt = desCipherDec.doFinal(ciphertext);
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
		}
		
	}
	
	
	public static void main(String[] args) {
		SymmetricDES test = new SymmetricDES();
		test.testIt();
	}
}
