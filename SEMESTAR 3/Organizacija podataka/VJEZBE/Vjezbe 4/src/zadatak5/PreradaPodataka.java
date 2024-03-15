package zadatak5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class PreradaPodataka {

	public void first(int mjesec, int godina, List<RentACar> lista, String filePath) throws IOException {
		
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate datumPocetka = LocalDate.of(godina, mjesec, 1);
		LocalDate datumKraja;
		
		if (mjesec == 1 || mjesec == 3 || mjesec == 5 || mjesec == 7 || mjesec == 8 || mjesec == 10 || mjesec == 12) {
			datumKraja = LocalDate.of(godina, mjesec, 31);
		} else if (mjesec == 4 || mjesec == 6 || mjesec == 9 || mjesec == 11) {
			datumKraja = LocalDate.of(godina, mjesec, 30);
		} else {
			if (godina%4 == 0) {
				datumKraja = LocalDate.of(godina, mjesec, 29);
			} else {
				datumKraja = LocalDate.of(godina, mjesec, 28);
			}
		}

		List<RentACar> listica = new ArrayList<RentACar>();

		for (RentACar rac : lista) {
			LocalDate dp = LocalDate.parse(rac.getDatumIznajmljivanja(), dateFormatter);
			LocalDate dk = LocalDate.parse(rac.getDatumIznajmljivanja(), dateFormatter);

            if (dp.compareTo(datumPocetka) >= 0 && dk.compareTo(datumKraja) <= 0) {
            	listica.add(rac);
			}
		}
		
		Yaml yaml = new Yaml(new Constructor(RentACar.class));
		File file = new File(filePath);
		
		try (FileWriter w = new FileWriter(file)){
			
			yaml.dumpAll(listica.iterator(), w);
		}
	}
}
