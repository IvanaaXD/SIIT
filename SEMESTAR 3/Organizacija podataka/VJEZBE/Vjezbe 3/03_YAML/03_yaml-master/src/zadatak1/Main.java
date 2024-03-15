package zadatak1;

import java.io.IOException;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class Main {

	static final String filePath = "resources/invoice.yaml";
	public static void main(String[] args) throws IOException {

		ReadFromYAML rfyaml = new ReadFromYAML();
		Invoice invoice = rfyaml.read(filePath);
		
		WriteToYAML wtyaml = new WriteToYAML();
		wtyaml.write(invoice, filePath);
	}

}
