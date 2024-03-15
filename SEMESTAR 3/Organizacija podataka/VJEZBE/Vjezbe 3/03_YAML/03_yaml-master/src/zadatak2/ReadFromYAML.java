package zadatak2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;

public class ReadFromYAML {

	public Invoice readInvoice(String FilePath) throws IOException{
		
		Invoice invoice = null;
		
		Yaml yaml = new Yaml(new Constructor(Invoice.class));
		File file = new File(FilePath);
		
		try (InputStream input = new FileInputStream(file)){
			
			invoice = (Invoice) yaml.load(input);
			System.out.println(invoice);
		}
		
		return invoice;
		
	}
	
	public Map<String, Double> readExchange(String FilePath)throws IOException{
		
		List<Exchange> exchangeRates = new ArrayList<Exchange>();
		
		Yaml yaml = new Yaml(new Constructor(Exchange.class));
		File file = new File(FilePath);
		
		try (InputStream input = new FileInputStream(file)) {
			
			Iterable<Object> iterables = yaml.loadAll(input);
			
			for (Object o: iterables) {
				if (o instanceof Exchange) {
					exchangeRates.add((Exchange)o);
				}
			}
		} 
		
		Map<String, Double> rates = new HashMap<String, Double>();
		
		for (Exchange e: exchangeRates) {
		
	        rates = e.getExchange_rates();
	        for (Map.Entry<String, Double> entry : rates.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
		}
		
		return rates;
	}
}
