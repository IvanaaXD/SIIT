package digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MessageDigestExample {

	private String message = "poruka"; // bilo koja poruka, pa i lozinka

	public void testIt() {

		try {
			String digest = null;

			// Primer osnovne upotrebe SHA256 algoritma za racunanje digest vrednosti
			MessageDigest sha = MessageDigest.getInstance("SHA-256");

			byte[] messageBytes = (message).getBytes();				
			byte[] digestBytes = sha.digest(messageBytes);

			System.out.println("Poruka " + message);
			digest = Base64.getEncoder().encodeToString(digestBytes);
			System.out.println("Digest poruke " + digest);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MessageDigestExample messageDigestExample = new MessageDigestExample();
		messageDigestExample.testIt();
	}

}