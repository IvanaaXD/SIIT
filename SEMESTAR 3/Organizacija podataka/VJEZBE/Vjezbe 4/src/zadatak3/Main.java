package zadatak3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import zadatak1.Autobus;
import zadatak1.WriteToJSON;
import zadatak2.WriteToYAML;

public class Main {
	
	static final String path = "resources/zadatak1.csv";
	static final String path1 = "resources/zadatak3_true.yaml";
	static final String path2 = "resources/zadatak3_false.json";

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		PreradaPodataka pp = new PreradaPodataka();
		WriteToYAML wtyaml = new WriteToYAML();
		WriteToJSON wtjson = new WriteToJSON();
		
		HashMap<Autobus, Boolean> mapa = new HashMap<Autobus, Boolean>();
		mapa = pp.zad(path);
		
		List<Autobus> autobusiTrue = new ArrayList<Autobus>();
		List<Autobus> autobusiFalse = new ArrayList<Autobus>();

		for (Map.Entry<Autobus, Boolean> entry : mapa.entrySet()) {
			if(entry.getValue() == true) {
				autobusiTrue.add(entry.getKey());
			} else if (entry.getValue() == false) {
				autobusiFalse.add(entry.getKey());
			}
		}
		
		wtyaml.write(autobusiTrue, path1);
		wtjson.write(autobusiFalse, path2);
	}

}
