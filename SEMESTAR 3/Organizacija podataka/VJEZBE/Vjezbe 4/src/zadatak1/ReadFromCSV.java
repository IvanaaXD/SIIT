package zadatak1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReadFromCSV {

	public List<Autobus> read(String FilePath){
		
		List<Autobus> autobuskiPolasci = new ArrayList<>();
		
		try (Reader reader = new FileReader(FilePath)){
			CsvToBean<Autobus> csv = new CsvToBeanBuilder<Autobus>(reader).withType(Autobus.class).withSeparator(',').build();

			List<Autobus> buss = csv.parse();

			for (Autobus bus : buss) {
				System.out.println(bus);
				autobuskiPolasci.add(bus);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Could not open file");

		} catch (IOException e) {
			System.out.println("I/O error occured");

		}
		
		return autobuskiPolasci;
		
	}
}
