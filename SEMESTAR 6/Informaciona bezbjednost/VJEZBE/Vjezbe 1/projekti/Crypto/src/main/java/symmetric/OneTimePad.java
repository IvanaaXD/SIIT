package symmetric;

import java.security.SecureRandom;
import java.util.Base64;

// 1010 1100 - plaintext
// 0110 1010 - key
// xor
// 1100 0110  - cyphertext

// 0110 1010 - key
// 1010 1100 - plaintext



public class OneTimePad {
    
    public static void main(String []args) {
    	
    	// Convert secret text to byte array
        byte[] secret = "TOP SECRET TEXT. CONFIDENTIAL.".getBytes();

        byte[] encrypted = new byte[secret.length];
        byte[] decrypted = new byte[secret.length];
    	
    	byte[] key = new byte[secret.length];
        new SecureRandom().nextBytes(key);
        
        // Encrypt the secret   
        for (int i = 0; i < secret.length; i++) {
            encrypted[i] = (byte) (secret[i] ^ key[i]);
        }
        
        // Encode the encrypted secret.
        char [] encoded = Base64.getEncoder().encodeToString(encrypted).toCharArray();
        
        // Print the encrypted secret and encoded encrypted secret.
        System.out.println(new String(encrypted));
        System.out.println(new String(encoded));
        
        // Decrypt the encrypted secret.
        for (int j = 0; j < encrypted.length; j++) {
        	decrypted[j] = (byte) (encrypted[j] ^ key[j]);
        }

        System.out.println(new String(decrypted));
    }
}
