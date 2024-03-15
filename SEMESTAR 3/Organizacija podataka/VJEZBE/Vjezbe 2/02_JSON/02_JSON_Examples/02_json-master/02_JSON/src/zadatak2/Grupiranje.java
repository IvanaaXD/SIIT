package zadatak2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grupiranje {

	public HashMap<String, List<Country>> group(List<Country> countries) {
		
		List<Country> Evropa = new ArrayList<Country>();
		List<Country> Azija = new ArrayList<Country>();
		List<Country> Afrika = new ArrayList<Country>();
		List<Country> SjevernaAmerika = new ArrayList<Country>();
		List<Country> CentralnaAmerika = new ArrayList<Country>();
		List<Country> JuznaAmerika = new ArrayList<Country>();
		List<Country> Australija = new ArrayList<Country>();
		List<Country> Antartik = new ArrayList<Country>();
		
		HashMap<String, List<Country>> mapica = new HashMap<String, List<Country>>();
		
		for (Country c :countries) {
			
			if (c.getContinentName().equals("Europe")) {
				Evropa.add(c);
			}
			
			else if (c.getContinentName().equals("Africa")) {
				Afrika.add(c);
			}
			
			else if (c.getContinentName().equals("Asia")) {
				Azija.add(c);
			}
			
			else if (c.getContinentName().equals("Australia")) {
				Australija.add(c);
			}
			
			else if (c.getContinentName().equals("Antarctica")) {
				Antartik.add(c);
			}
			
			else if (c.getContinentName().equals("North America")) {
				SjevernaAmerika.add(c);
			}
			
			else if (c.getContinentName().equals("Central America")) {
				CentralnaAmerika.add(c);
			}
			
			else if (c.getContinentName().equals("South America")) {
				JuznaAmerika.add(c);
			}
		}
		
		mapica.put("Europe", Evropa);
		mapica.put("Asia", Azija);
		mapica.put("Africa", Afrika);
		mapica.put("Antarctica", Antartik);
		mapica.put("Australia", Australija);
		mapica.put("North America", SjevernaAmerika);
		mapica.put("Central America", CentralnaAmerika);
		mapica.put("South America", JuznaAmerika);

		return mapica;

	}
}
