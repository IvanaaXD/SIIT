package zadatak1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class ReadFromYAML {

	public Invoice read(String FilePath) throws IOException {
		
		//List<Invoice> invoices = new ArrayList<Invoice>();
		
		Yaml yaml = new Yaml(new Constructor(Invoice.class));
		File file = new File(FilePath);
		
		Invoice invoice = null;
		
		try (InputStream input = new FileInputStream(file)){
			invoice = (Invoice) yaml.load(input);
			System.out.println(invoice);
			
//            Iterable<Object> iterable = yaml.loadAll(input);
//            for (Object obj : iterable) {
//                if (obj instanceof Invoice) {
//                    invoices.add((Invoice) obj);
//                    System.out.println(obj);
//                }
//            }
			
		} 
		
		return invoice;
		
	}
}
