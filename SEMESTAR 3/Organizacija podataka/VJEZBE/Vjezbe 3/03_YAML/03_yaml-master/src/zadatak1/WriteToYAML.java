package zadatak1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Address;
import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class WriteToYAML {

	public void write(Invoice invoice, String FilePath) {
		
		Yaml yaml = new Yaml(new Constructor(Invoice.class));
		File file = new File(FilePath);
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("Unesite novu adresu: (ulica,grad,drzava,postanski broj)");
		
		String[] wuhu = scanner.nextLine().split(",");
		
		Address adresa = new Address();
		adresa.lines = wuhu[0];
		adresa.city = wuhu[1];
		adresa.state = wuhu[2];
		adresa.postal = wuhu[3];
		
		invoice.billTo.address = adresa;
		
		try (FileWriter writer = new FileWriter(file)) {
	        yaml.dump(invoice, writer);
	        System.out.println("Invoice has been written to " + FilePath);
	    } catch (IOException e) {
	        System.err.println("Error writing the invoice to " + FilePath);
	        e.printStackTrace();
	    }
		
	}
}
