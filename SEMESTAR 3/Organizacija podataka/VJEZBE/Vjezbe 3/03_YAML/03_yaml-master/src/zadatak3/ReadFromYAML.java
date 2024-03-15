package zadatak3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class ReadFromYAML {

	public List<Invoice> read(String filePath) throws FileNotFoundException, IOException {
		
		Yaml yaml = new Yaml(new Constructor(Invoice.class));
		File file = new File(filePath);
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		try (InputStream input = new FileInputStream(file)){
						
			Iterable<Object> iterable = yaml.loadAll(input);
			for (Object obj : iterable) {
			    if (obj instanceof Invoice) {
			        invoices.add((Invoice) obj);
			        System.out.println(obj);
			    }
			}
		} 
		
		return invoices;
	}
}
