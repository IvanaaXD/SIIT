package app;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.mail.internet.MimeMessage;

import com.google.api.services.gmail.Gmail;

import support.MailHelper;
import support.MailWritter;

public class WriteMailClient extends MailClient {

	public static void main(String[] args) {
		
        try {
        	System.out.println("PREDUSLOV: gmail email adresa mora biti dodata u listu testnih korisnika!");
    		System.out.println("PREDUSLOV: da bi bila dodata, recite asistentu na nenadtod@uns.ac.rs!");
        	
        	Gmail service = getGmailService();
            
        	System.out.println("Insert a reciever:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String reciever = reader.readLine();
        	
            System.out.println("Insert a subject:");
            String subject = reader.readLine();
            
            System.out.println("Insert text:");
            String text = reader.readLine();
            
            
            //TODO: Compress and encrypt the content before sending.     
        	MimeMessage mimeMessage = MailHelper.createMimeMessage(reciever, subject, text);
        	MailWritter.sendMessage(service, "me", mimeMessage);
        	
        }catch (Exception e) {
        	e.printStackTrace();
		}
		
	}
}
