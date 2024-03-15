package zadatak2;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zadatak1.Autobus;
import zadatak1.ReadFromCSV;

public class PreradaPodataka {

	public HashMap<Autobus, Integer> NajveciBrojPolazaka(String FilePath) {
		
		HashMap<Autobus, Integer> mapica = new HashMap<Autobus, Integer>();
		HashMap<Autobus, Integer> map = new HashMap<Autobus, Integer>();

		ReadFromCSV rfcsv = new ReadFromCSV();
		List<Autobus> autobusi = new ArrayList<Autobus>(rfcsv.read(FilePath));
		
		String relacija = "";
		
		for (Autobus aa : autobusi) {
			
			relacija = aa.getMjestoPolaska();
			int suma = 1;
			for (Autobus aaa : autobusi) {
				
				if (!aaa.getSifraRelacije().equals(aa.getSifraRelacije())) {
					
					if (relacija.equals(aaa.getMjestoPolaska())) {
						suma += 1;
					}
					
				}
			}
			
			mapica.put(aa, suma);
			
		}
		
		int max = 0;
		for (Map.Entry<Autobus, Integer> entry : mapica.entrySet()) {
			if (entry.getValue() > max) {
				map.clear();
				map.put(entry.getKey(), entry.getValue());
				max = entry.getValue();
			} else if (entry.getValue() == max) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		
		return map;
	}
	
	public HashMap<Autobus, Duration> NajduzeVrijemePutovanja(String FilePath){
		
		HashMap<Autobus, Duration> mapica = new HashMap<Autobus, Duration>();

		ReadFromCSV rfcsv = new ReadFromCSV();
		List<Autobus> autobusi = new ArrayList<Autobus>(rfcsv.read(FilePath));
		
		String vrijemePolaska = "";
		String vrijemeDolaska = "";
		
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
		
		vrijemePolaska = autobusi.get(0).getVrijemePolaska();
		vrijemeDolaska = autobusi.get(0).getPlaniranoVrijemeDolaska();
		
		LocalTime vrijemePolaskaTime = LocalTime.parse(vrijemePolaska, timeFormat);
		LocalTime vrijemeDolaskaTime = LocalTime.parse(vrijemeDolaska, timeFormat);
		
		Duration difference = Duration.between(vrijemePolaskaTime, vrijemeDolaskaTime);
		
		for (int i = 1; i < autobusi.size(); i++) {
			vrijemePolaska = autobusi.get(i).getVrijemePolaska();
			vrijemeDolaska = autobusi.get(i).getPlaniranoVrijemeDolaska();
			
			vrijemePolaskaTime = LocalTime.parse(vrijemePolaska, timeFormat);
			vrijemeDolaskaTime = LocalTime.parse(vrijemeDolaska, timeFormat);
			
			Duration timeDifference = Duration.between(vrijemePolaskaTime, vrijemeDolaskaTime);
			
			long seconds1 = difference.getSeconds();
			long seconds2 = timeDifference.getSeconds();
			
			if (seconds2 <= seconds1) {
				mapica.put(autobusi.get(i), timeDifference);
			}
			
		}
		
		return mapica;
	}
	
	public HashMap<Autobus, Double> NajvecaZarada(String FilePath){
		
		HashMap<Autobus, Double> mapica = new HashMap<Autobus, Double>();
		
		double zaradaMax = 0;
		double zarada = 0;
		
		ReadFromCSV rfcsv = new ReadFromCSV();
		List<Autobus> autobusi = new ArrayList<Autobus>(rfcsv.read(FilePath));
		
		for (Autobus a: autobusi) {
			zarada = a.getCijena() * a.getBrojProdatihKarata();
		
			if (zarada > zaradaMax) {
				mapica.clear();
				mapica.put(a, zarada);
				zaradaMax = zarada;
			}
			
			if (zarada == zaradaMax) {
				mapica.put(a, zarada);
			}
		}
		

		return mapica;
	}
}
