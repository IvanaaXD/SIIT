package asymmetric;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.util.Base64;


//RSA ALGORITAM MOZE DA KRIPTUJE PODATKE MAKSIMALNE VELICINE DO VELICINE KLJUCA (U BAJTOVIMA)
//STOGA SE OBICNO KORISTI SAMO ZA KRIPTOVANJE TAJNIH KLJUCEVA, A NE I PODATAKA
public class AsymmetricEncription {
	
	private PublicKey  publicKey;
	private PrivateKey privateKey;
	private String data = "Ovo su podaci koji se kriptuju asimetricnim postupkom";


	public AsymmetricEncription() {
		//Postavljamo providera, jer treba za RSA Enkripciji/Dekripciju
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public void testIt() {
		System.out.println("Generisanje kljuceva:");
		generateKeys();
		System.out.println("Radi kriptovanje/dekriptovanje:");
		transfer();
		
	}
	
	private void generateKeys() {
        try {
			System.out.println("Kreira generator kljuceva...");
			
			//generator para kljuceva
			KeyPairGenerator   keyGen = KeyPairGenerator.getInstance("RSA"); 
			//generator pseudo slucajnih brojeva
			SecureRandom       random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			//inicijalizacija generatora, 2048 bitni kljuc
			keyGen.initialize(2048, random);
			
			System.out.println("Kreira par kljuceva...");
			
			//generise par kljuceva
			KeyPair    pair = keyGen.generateKeyPair();
			//preuzimamo javni i privatni kljuc
			publicKey = pair.getPublic();
			privateKey = pair.getPrivate();

        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}
	
	private void transfer() {
			
		try {
			//RSA ALGORITAM MOZE DA KRIPTUJE PODATKE MAKSIMALNE VELICINE DO VELICINE KLJUCA (U BAJTOVIMA)
			//STOGA SE OBICNO KORISTI SAMO ZA KRIPTOVANJE TAJNIH KLJUCEVA, A NE I PODATAKA
			
			System.out.println("Kriptuje se " + data);	
			//kriptovanje poruke javnim kljucem
			Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
			//inicijalizacija za kriptovanje, 
			//kod asimetricnog kriptuje se javnim kljucem, a dekriptuje privatnim
			rsaCipherEnc.init(Cipher.ENCRYPT_MODE, publicKey);
			
			//kriptovanje
			byte[] ciphertext = rsaCipherEnc.doFinal(data.getBytes());
			System.out.println("Kriptovan text: " + Base64.getEncoder().encodeToString(ciphertext));
			
			//dekriptovanje poruke privatnim klucem
			Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", "BC");
			//inicijalizacija za dekriptovanje
			//dekriptovanje se vrsi privatnim kljucem
			rsaCipherDec.init(Cipher.DECRYPT_MODE, privateKey);
			
			//dekriptovanje
			byte[] receivedTxt = rsaCipherDec.doFinal(ciphertext);
			System.out.println("Primljeni text: " + new String(receivedTxt));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
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
		AsymmetricEncription test = new AsymmetricEncription();
		test.testIt();
	}
}
