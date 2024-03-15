package zadatak3;

import java.util.HashMap;
import java.util.List;

import zadatak1.Autobus;
import zadatak1.ReadFromCSV;

public class PreradaPodataka {

	public 	HashMap<Autobus, Boolean> zad(String filePath){
		
		HashMap<Autobus, Boolean> map = new HashMap<Autobus, Boolean>();
		
		ReadFromCSV rfcsv = new ReadFromCSV();
		List<Autobus> autobusi = rfcsv.read(filePath);
		
		for (Autobus a: autobusi) {
			
			String dolazak = a.getMjestoDolaska();
			
			for (Autobus aa: autobusi) {
				
				if (aa.getMjestoPolaska().equals(dolazak) && !a.getSifraRelacije().equals(aa.getSifraRelacije())) {
					map.put(a, true);
				} else if (!aa.getMjestoPolaska().equals(dolazak) && !a.getSifraRelacije().equals(aa.getSifraRelacije())) {
					map.put(a, false);
				}
			}
		}
		
		return map;
	}
	
}
