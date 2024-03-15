package zadatak2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class WriteToYAML {

	public void write(Invoice invoice, String filePath) throws IOException{
		
		Yaml yaml = new Yaml(new Constructor(Invoice.class));
		File file = new File(filePath);
		
		try (FileWriter writer = new FileWriter(file)){
			
			yaml.dump(invoice, writer);
			System.out.println("Invoice has been written to file.");
			
		}
	}
}
