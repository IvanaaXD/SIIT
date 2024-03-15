package zadatak2;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import zadatak1.Autobus;

public class Main {
	
	static final String ZADATAK1A = "resources/zadatak1.csv";
	static final String First = "resources/first.csv";
	static final String Second = "resources/second.csv";
	static final String Third = "resources/third.csv";


	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		PreradaPodataka pp = new PreradaPodataka();
		
		//PRVA TACKA
		
		HashMap<Autobus,Integer> mapica1 = new HashMap<Autobus,Integer>();

		mapica1 = pp.NajveciBrojPolazaka(ZADATAK1A);
		
		System.out.println(mapica1);
		
		//DRUGA TACKA
		
		HashMap<Autobus, Duration> mapica2 = new HashMap<Autobus, Duration>();

		mapica2 = pp.NajduzeVrijemePutovanja(ZADATAK1A);
		
		System.out.println(mapica2);
		
		//TRECA TACKA
		
		HashMap<Autobus, Double> mapica3 = new HashMap<Autobus, Double>();

		mapica3 = pp.NajvecaZarada(ZADATAK1A);
		
		System.out.println(mapica3);
		
		//CETVRTA TACKA
		
		WriteToYAML wtyaml = new WriteToYAML();
		wtyaml.writeFirst(mapica1, First);
		wtyaml.writeSecond(mapica2, Second);
		wtyaml.writeThird(mapica3, Third);
		
		
	}

}

