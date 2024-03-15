package zadatak5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static final String path = "resources/agencija.json";
	static final String path1 = "resources/agencijafirst.yaml";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<RentACar> lista = new ArrayList<RentACar>();
					
		String[] rentACarData = {
			    "001,Belgrade,2023-11-08,2023-11-15,450,40.00,7,John,Doe,1234567890",
			    "002,New York,2023-12-01,2023-12-10,600,50.00,9,Alice,Smith,9876543210",
			    "003,Los Angeles,2023-10-20,2023-10-25,300,45.00,5,Michael,Johnson,5555555555",
			    "004,Paris,2023-11-05,2023-11-12,550,55.00,7,Sophie,Martin,4444444444",
			    "005,London,2023-09-15,2023-09-20,400,60.00,5,William,Taylor,6666666666",
			    "006,Tokyo,2023-08-10,2023-08-17,700,70.00,7,Akihiro,Sato,7777777777",
			    "007,Berlin,2023-07-01,2023-07-10,800,35.00,9,Lena,Müller,8888888888",
			    "008,Sydney,2023-06-15,2023-06-20,350,48.00,5,Olivia,Brown,9999999999",
			    "009,Rome,2023-05-10,2023-05-15,200,42.00,5,Giovanni,Ricci,1111111111",
			    "010,Madrid,2023-04-15,2023-04-22,550,47.00,7,Maria,Lopez,2222222222"
			};
		
		for (String a : rentACarData) {
			
			RentACar rac = new RentACar();
			Osoba oo = new Osoba();

			String[] o = a.split(",");
			rac.setSifraIznajmljivanja(o[0]);
			rac.setMjestoIznajmljivanja(o[1]);
			rac.setDatumIznajmljivanja(o[2]);			
			rac.setDatumVracanja(o[3]);
			rac.setPredjenoKilometara(Double.parseDouble(o[4]));
			rac.setCijenaIznajmljivanja(Double.parseDouble(o[5]));
			rac.setBrojDanaIznajmljivanja(Integer.parseInt(o[6]));
			oo.setIme(o[7]);
			oo.setPrezime(o[8]);
			oo.setJMBG(Long.parseLong(o[9]));
			rac.setOsoba(oo);
			lista.add(rac);
		}

		WriteToJSON wtjson = new WriteToJSON();
		wtjson.write(lista, path);
		
		ReadFromJSON rfjson = new ReadFromJSON();
		List<RentACar> listica = new ArrayList<RentACar>();
		listica = rfjson.read(path);
		
		System.out.println(listica);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Unesite mjesec i godinu za koje zelite izvjestaj: (mm,yyyy)");
		String s = sc.nextLine();
		
		String ss[] = s.split(",");
		int i = Integer.parseInt(ss[0]);
		int ii = Integer.parseInt(ss[1]); 
		
		PreradaPodataka pp = new PreradaPodataka();
		pp.first(i, ii, listica, path1);
	}
}
