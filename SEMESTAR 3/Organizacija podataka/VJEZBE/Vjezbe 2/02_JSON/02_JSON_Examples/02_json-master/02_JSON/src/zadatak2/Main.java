package zadatak2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	static final String FILE = "resources/countries_cities.csv";

	public static void main(String[] args) {

		ReadFromCSV rfcsv = new ReadFromCSV();
		
		List<Country> countries = rfcsv.read(FILE);
		
		Grupiranje g = new Grupiranje();
		
		int fileCounter = 1;
		
		HashMap<String, List<Country>> mapa = new HashMap<String, List<Country>>();
		mapa = g.group(countries);
		
		ObjectMapper om = new ObjectMapper();
		om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		
		for (Map.Entry<String, List<Country>> entry : mapa.entrySet()) {
		    List<Country> countryList = entry.getValue();
		    
	        String outputFileName = "resources/file" + fileCounter + ".json";
	        File file = new File(outputFileName);

	        try {
	            om.writeValue(file, countryList);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    
	        fileCounter++;

		}

	}

}
