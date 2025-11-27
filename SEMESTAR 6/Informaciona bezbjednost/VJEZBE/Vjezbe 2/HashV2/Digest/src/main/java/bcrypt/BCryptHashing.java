package bcrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BCryptHashing {

	public static void main(String[] args) throws IOException {
		System.out.println("Hesiramo lozinku sa saltom.");
		String hashed = BCrypt.hashpw("test", BCrypt.gensalt());//10
		System.out.println("Hesirana lozinka: " + hashed );
		System.out.println("Hesiramo lozinku sa saltom i tezinom 12.");
		String hashed1 = BCrypt.hashpw("test", BCrypt.gensalt(12));
		System.out.println("Hesirana lozinka: " + hashed1 );
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Password:");
		String attemptedPassword = in.readLine();
		if (BCrypt.checkpw(attemptedPassword, hashed))
			System.out.println("ACCESS GRANTED");
		else
			System.out.println("ACCESS DENIED!!!");
	}
	
}
