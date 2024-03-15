package zadatak1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main {
	
	static final String ZADATAK1A = "resources/zadatak1.csv";
	static final String ZADATAK1B = "resources/zadatak1.json";

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		WriteToCSV wtcsv = new  WriteToCSV();
		wtcsv.write(ZADATAK1A);
		
		ReadFromCSV rfcsv = new ReadFromCSV();
		List<Autobus> autobusi = new ArrayList<Autobus>(rfcsv.read(ZADATAK1A));
		
		WriteToJSON wtjson = new WriteToJSON();
		wtjson.write(autobusi, ZADATAK1B);
	}

}
