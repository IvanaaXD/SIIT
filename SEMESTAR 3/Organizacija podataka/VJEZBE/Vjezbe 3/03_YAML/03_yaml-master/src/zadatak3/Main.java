package zadatak3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;
import rs.ac.uns.ftn.siit.op.yaml.example03.Product;

public class Main {

	static final String file = "resources/invoice.yaml";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		ReadFromYAML rfyaml = new ReadFromYAML();
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = rfyaml.read(file);
		
		WriteToYAML wtyaml = new WriteToYAML();
		
		int counter = 1;
		for (Invoice i:invoices) {
			List<Product> products = i.product;
			
			for (Product p: products) {
				String filePath = "resources/invoice_" + counter + ".yaml";
				int q = p.quantity;
				p.price = p.price / q;
				p.quantity = 1;
				wtyaml.write(p, filePath);
				counter++;
			}
			
		}
		
	}

}
