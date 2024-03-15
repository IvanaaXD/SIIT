package zadatak2;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReadFromCSV {
	
	public List<Country> read(String FilePath){
		
		List<Country> countries = new ArrayList<Country>();
		
		try (Reader reader = new FileReader(FilePath)){
			
			CsvToBean<Country> csv = new CsvToBeanBuilder<Country>(reader).withType(Country.class).withSeparator(',').build();

			List<Country> cc = csv.parse();
			
			for (Country c : cc) {
				countries.add(c);
				System.out.println(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return countries;
	}

}
