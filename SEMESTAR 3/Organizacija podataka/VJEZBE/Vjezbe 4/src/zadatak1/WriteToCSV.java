package zadatak1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class WriteToCSV {
	
	public void write(String FilePath) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String[] autobuskeRelacije = {
			    "RS001,Beograd,Novi Sad,2023-11-10,08:00,10:30,3,1000,50",
			    "RS002,Niš,Kragujevac,2023-11-12,07:30,09:45,2,800,35",
			    "RS011,Novi Sad,Beograd,2023-11-10,14:00,16:30,4,1000,45",
			    "RS003,Novi Sad,Novi Pazar,2023-11-15,09:15,17:45,1,1500,20",
			    "RS002,Kragujevac,Niš,2023-11-12,13:45,16:00,3,650,19",
			    "RS004,Krusevac,Niš,2023-11-12,13:45,16:00,3,900,20",
			    "RS005,Niš,Kragujevac,2023-11-12,13:45,16:00,3,500,30",
			    "RS006,Niš,Krusevac,2023-11-12,13:45,16:00,3,800,15",
			    "RS007,Subotica,Novi Pazar,2023-11-12,13:45,16:00,3,1200,30",
			    "RS008,Beograd,Vranje,2023-11-12,13:45,16:00,3,1000,24",
			    "RS009,Vranje,Beograd,2023-11-12,13:45,16:00,3,800,60",
			};
		
		List<Autobus> autobuskiPolasci = new ArrayList<>();
		
		for (String r : autobuskeRelacije) {
			String[] s = r.split(",");

			Autobus a = new Autobus(s[0], s[1], s[2], s[3], s[4], s[5], Integer.parseInt(s[6]), Double.parseDouble(s[7]),Integer.parseInt(s[8]));
			autobuskiPolasci.add(a);
		}
		
		try (Writer writer = new FileWriter(FilePath)){
			StatefulBeanToCsv<Autobus> beanToCsv = new StatefulBeanToCsvBuilder<Autobus>(writer).withApplyQuotesToAll(false).build();
			beanToCsv.write(autobuskiPolasci);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
