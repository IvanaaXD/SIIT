package vjezbe_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class zadatak2 {
	
	static Scanner sc = new Scanner(System.in);
	static int broj = 0;
	static ArrayList <Artikal> lista = new ArrayList<>();
	
	public static void main(String[] args) {
		
		System.out.println("==================Menu=================");
		System.out.println("1. Unos novog artikla");
		System.out.println("2. Izmjena podataka postojeceg artikla");
		System.out.println("3. Ispis");
		System.out.println("4. Sortiranje artikala po nazivu");
		System.out.println("5. Sortiranje artikala po cijeni");
		System.out.println("6. Sortiranje artikala po kategoriji");
		System.out.println("7. Izlazak iz aplikacije");

		System.out.println("Unesite broj zeljene opcije: ");
		String unos = sc.nextLine();
		
		boolean drama = true;
		while(drama) {
			
			switch(unos) {
			case "1": funkcija_1();
					break;
			case "2": funkcija_2();
					break;
			case "3": funkcija_3();
					break;
			case "4": funkcija_4();
					break;
			case "5": funkcija_5();
					break;
			case "6": funkcija_6();
					break;
			case "7": System.out.println("Hvala sto ste koristili nasu aplikaciju!");
					drama = false;
					break;
			default: System.out.println("Pogresan unos! Pokusajte ponovo!");
					break;
			}
			
		}
		
		sc.close();
		
	}
	
	public static void funkcija_1() {
		
		System.out.println("Unesite podatke o artiklu: ");
		
		System.out.println("Sifra: ");
		String sifra = sc.nextLine();
		
		System.out.println("Naziv: ");
		String naziv = sc.nextLine();
		
		System.out.println("Cijena: ");
		Double cijena = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Raspoloziva kolicina: ");
		Integer raspolozivaKolicina = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Opis: ");
		String opis = sc.nextLine();
		
		System.out.println("Kategorija: ");
		String kategorija = sc.nextLine();
		
		Artikal artikal = new Artikal(sifra, naziv.toLowerCase(), cijena, raspolozivaKolicina, opis, kategorija.toLowerCase());
		lista.add(artikal);
		
		broj += 1;
		System.out.println("Artikal uspjesno dodat!");
		
	}
	
	public static void funkcija_2() {
		
		System.out.println("Unesite sifru artikla koji mijenjate: ");
		String unos = sc.nextLine();
		
		int brojac = 0;
		int indeks = 0;
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jej = lista.get(i);
			if (jej.getSifra() == unos) {
				
				brojac = 1;
				indeks = i;
			}
			else {
				System.out.printf("Artikal sa sifrom %s ne postoji.", unos);
			}
		}
		
		if (brojac == 1) {
			
			Artikal jeeej = lista.get(indeks);
			
			System.out.println("Unesite podatke o artiklu: ");
			
			System.out.println("Sifra: ");
			String sifra = sc.nextLine();
			
			if (!sifra.equals("")) {
				jeeej.setSifra(sifra);
			}
			
			System.out.println("Naziv: ");
			String naziv = sc.nextLine();
			
			if (!naziv.equals("")) {
				jeeej.setNaziv(naziv.toLowerCase());
			}
			
			System.out.println("Cijena: ");
			Double cijena = sc.nextDouble();
			sc.nextLine();
			
			if (cijena != null) {
				jeeej.setCijena(cijena);
			}
			
			System.out.println("Raspoloziva kolicina: ");
			Integer raspolozivaKolicina = sc.nextInt();
			sc.nextLine();
			
			if (raspolozivaKolicina != null) {
				jeeej.setKolicina(raspolozivaKolicina);
			}
			
			System.out.println("Opis: ");
			String opis = sc.nextLine();
			
			if (!opis.equals("")) {
				jeeej.setOpis(opis);
			}
			
			System.out.println("Kategorija: ");
			String kategorija = sc.nextLine();
			
			if (!kategorija.equals("")) {
				jeeej.setKategorija(kategorija.toLowerCase());
			}
			
			System.out.println("Artikal uspjesno izmijenjen!");
			
		}
			
	}
	
	public static void funkcija_3() {
		
		System.out.println("1. Ispis svih podataka o odredjenom artiklu");
		System.out.println("2. Ispis svih artikala u formatu (redni broj, naziv, cijena, kolicina i sifra)");
		System.out.println("3. Ispis svih artikala koji pripadaju odredjenoj kategoriji");
		System.out.println("4. Nazad");
		
		System.out.println("Izaberite zelejnu opciju: ");
		String unos = sc.nextLine();
		
		switch(unos) {
		case "1": funkcija_3_1();
				break;
		case "2": funkcija_3_2();
				break;
		case "3": funkcija_3_2();
				break;
		case "4": main(null);
				break;
		default: System.out.println("Pogresan unos! Pokusajte ponovo!");
				break;
		}
		
	}
	
	public static void funkcija_3_1() {
		
		System.out.println("Unesite sifru artikla: ");
		String unos = sc.nextLine();
		
		int brojac = 0;
		int indeks = 0;
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jej = lista.get(i);
			if (jej.getSifra() == unos) {
				
				brojac = 1;
				indeks = i;
			}
			else {
				System.out.printf("Artikal sa sifrom %s ne postoji.", unos);
			}
		}
		
		if (brojac == 1) {
			
			Artikal jeeej = lista.get(indeks);
			
			System.out.println("Sifra: " + jeeej.getSifra());
			System.out.println("Naziv: " + jeeej.getNaziv());
			System.out.println("Cijena: " + jeeej.getCijena());
			System.out.println("Raspoloziva kolicina: " + jeeej.getKolicina());
			System.out.println("Opis: " + jeeej.getOpis());
			System.out.println("Kategorija: " + jeeej.getKategorija());

		}
		
	}

	public static void funkcija_3_2() {
	
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jeeej = lista.get(i);
			
			System.out.print("Redni broj: " + (i + 1) + ", ");
			System.out.print("Naziv: " + jeeej.getNaziv() + ", ");
			System.out.print("Cijena: " + jeeej.getCijena() + ", ");
			System.out.print("Raspoloziva kolicina: " + jeeej.getKolicina() + ", ");
			System.out.print("Sifra: " + jeeej.getSifra() + ".");
			
		}
		
	}
	
	public static void funkcija_3_3() {
		
		System.out.println("Unesite kategoriju artikla: ");
		String unos = sc.nextLine();
		
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jeeej = lista.get(i);
			
			String kat = jeeej.getKategorija();
			
			if (kat.equals(unos)) {
				
				System.out.print("Sifra: " + jeeej.getSifra() + ", ");
				System.out.print("Naziv: " + jeeej.getNaziv() + ", ");
				System.out.print("Cijena: " + jeeej.getCijena() + ", ");
				System.out.print("Raspoloziva kolicina: " + jeeej.getKolicina() + ", ");
				System.out.print("Opis: " + jeeej.getOpis() + ", ");

			}
			
			System.out.println("\n");
			
		}

	}
	
	public static void funkcija_4() {
		
		System.out.println("1. Opadajuci raspored");
		System.out.println("2. Rastuci raspored");
		System.out.println("3. Nazad");
		
		System.out.println("Izaberite zelejnu opciju: ");
		String unos = sc.nextLine();
		
		int brojac = 0;
		
		switch(unos) {
		case "1": brojac = 1;
				break;
		case "2": brojac = 2;
				break;
		case "3":  main(null);
				break;
		default: System.out.println("Pogresan unos! Pokusajte ponovo!");
				break;
		}
		
		String[] nazivi = new String[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jeeej = lista.get(i);
			String naziv = jeeej.getNaziv();
			
			nazivi[i] = naziv;
		}
		
		if (brojac == 1) {
			
			nazivi = Stream.of(nazivi)
				    .sorted()
				    .toArray(String[]::new);
			
		}
		
		if (brojac == 2) {

			nazivi = Stream.of(nazivi)
				    .sorted(Comparator.reverseOrder())
				    .toArray(String[]::new);
			
		}
		
		System.out.println("Trazeni artikli su:");
		System.out.println(nazivi);
		
	}
	
	public static void funkcija_5() {
		
		System.out.println("1. Opadajuci raspored");
		System.out.println("2. Rastuci raspored");
		System.out.println("3. Nazad");
		
		System.out.println("Izaberite zelejnu opciju: ");
		String unos = sc.nextLine();
		
		int brojac = 0;
		
		switch(unos) {
		case "1": brojac = 1;
				break;
		case "2": brojac = 2;
				break;
		case "3":  main(null);
				break;
		default: System.out.println("Pogresan unos! Pokusajte ponovo!");
				break;
		}
		
		Double[] cijene = new Double[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jeeej = lista.get(i);
			Double cijena = jeeej.getCijena();
			
			cijene[i] = cijena;
		}
		
		if (brojac == 1) {
			
			Arrays.sort(cijene, Collections.reverseOrder());
		}
		
		if (brojac == 2) {

			Arrays.sort(cijene);
			
		}
		
		System.out.println("Trazeni artikli su:");
		System.out.println(cijene);
		
	}
	
	public static void funkcija_6() {
		
		System.out.println("1. Opadajuci raspored");
		System.out.println("2. Rastuci raspored");
		System.out.println("3. Nazad");
		
		System.out.println("Izaberite zelejnu opciju: ");
		String unos = sc.nextLine();
		
		int brojac = 0;
		
		switch(unos) {
		case "1": brojac = 1;
				break;
		case "2": brojac = 2;
				break;
		case "3":  main(null);
				break;
		default: System.out.println("Pogresan unos! Pokusajte ponovo!");
				break;
		}
		
		String[] kategorije = new String[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			
			Artikal jeeej = lista.get(i);
			String kategorija = jeeej.getKategorija();
			
			kategorije[i] = kategorija;
		}
		
		if (brojac == 1) {
			
			kategorije = Stream.of(kategorije)
				    .sorted()
				    .toArray(String[]::new);
			
		}
		
		if (brojac == 2) {

			kategorije = Stream.of(kategorije)
				    .sorted(Comparator.reverseOrder())
				    .toArray(String[]::new);
			
		}
		
		System.out.println("Trazeni artikli su:");

		for (int i = 0; i < kategorije.length; i++) {
			
			System.out.printf("Artikli kategorije %s su: ", kategorije[i]);
			System.out.println();
			
			for (int j = 0; j < lista.size(); j++) {
				
				Artikal jej = lista.get(i);
				
				if (kategorije[i].equals(jej.getKategorija())) {
					
					System.out.print("Sifra: " + jej.getSifra() + ", ");
					System.out.print("Naziv: " + jej.getNaziv() + ", ");
					System.out.print("Cijena: " + jej.getCijena() + ", ");
					System.out.print("Raspoloziva kolicina: " + jej.getKolicina() + ".");
					
				}
				
			}
			
		}
		
	}
	
}

class Artikal{
	
	protected String sifra;
	protected String naziv;
	protected Double cijena;
	protected Integer raspolozivaKolicina;
	protected String opis;
	protected String kategorija;

	Artikal(){	
	}
	

	Artikal(String sifra, String naziv, Double cijena, Integer raspolozivaKolicina, String opis, String kategorija){
		
		this.sifra = sifra;
		this.naziv = naziv;
		this.cijena = cijena;
		this.raspolozivaKolicina = raspolozivaKolicina;
		this.opis = opis;
		this.kategorija = kategorija;
		
	}
	
	public void setSifra(String sifra){
		this.sifra = sifra;
	}
	
	public String getSifra(){
		return sifra;
	}
	
	public void setNaziv(String naziv){
		this.naziv = naziv;
	}

	public String getNaziv(){
		return naziv;
	}
	
	public void setCijena(Double cijena){
		this.cijena = cijena;
	}
	
	public double getCijena(){
		return cijena;
	}
	
	public void setKolicina(Integer raspolozivaKolicina){
		this.raspolozivaKolicina = raspolozivaKolicina;
	}
	
	public int getKolicina(){
		return raspolozivaKolicina;
	}
	
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public String getOpis(){
		return opis;
	}
	
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	
	public String getKategorija(){
		return kategorija;
	}
		
}
