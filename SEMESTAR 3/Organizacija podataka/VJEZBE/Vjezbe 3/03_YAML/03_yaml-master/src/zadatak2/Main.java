package zadatak2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import rs.ac.uns.ftn.siit.op.yaml.example03.Invoice;
import rs.ac.uns.ftn.siit.op.yaml.example03.Product;

public class Main {

	static final String FILEPATH1 = "resources/invoice.yaml";
	static final String FILEPATH2 = "resources/exchange.yaml";

	public static void main(String[] args) throws IOException {
		
		ReadFromYAML rfyaml = new ReadFromYAML();
		Invoice invoice = rfyaml.readInvoice(FILEPATH1);
		
		WriteToYAML wtyaml = new WriteToYAML();

		Map<String, Double> exchangeRates = new HashMap<String, Double>(rfyaml.readExchange(FILEPATH2));
		
		int counter = 1;
		for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
			System.out.println(counter + ": " + entry.getKey());
			counter++;
			
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Izaberite valutu u koju zelite da prebacite: ");
		
		String s = sc.nextLine();
		
		String path = "resources/invoice_" + s + ".yaml"; 
		
		boolean k = true;
		while(k) {
		
			switch(s) {
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
					
					counter = 1;
					for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
						if (counter == Integer.parseInt(s)) {
							double d = entry.getValue();

							invoice.total = (float) (invoice.total * d);
							List<Product> kk = invoice.product;
							
							for (int i = 0; i < kk.size(); i++) {
								kk.get(i).price = (float) (kk.get(i).price * d);
							}
							
							invoice.product = kk;
							wtyaml.write(invoice, path);

						}
						counter++;
					}
					k = false;
					break;
				default:
					System.out.println("Molimo ukucajte brojeve izmedju 1 i 7.");
					s = sc.nextLine();
					break;
			}
		}

		
	}

}
