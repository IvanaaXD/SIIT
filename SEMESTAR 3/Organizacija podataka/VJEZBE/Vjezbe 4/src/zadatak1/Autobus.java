package zadatak1;

import com.opencsv.bean.CsvBindByPosition;

public class Autobus {

	@CsvBindByPosition(position = 0, required = true)
	public String sifraRelacije;
	@CsvBindByPosition(position = 1, required = true)
	public String mjestoPolaska;	
	@CsvBindByPosition(position = 2, required = true)
	public String mjestoDolaska;	
	@CsvBindByPosition(position = 3, required = true)
	public String datumPolaska;	
	@CsvBindByPosition(position = 4, required = true)
	public String vrijemePolaska;
	@CsvBindByPosition(position = 5, required = true)
	public String planiranoVrijemeDolaska;
	@CsvBindByPosition(position = 6, required = true)
	public int peron;
	@CsvBindByPosition(position = 7, required = true)
	public double cijena;
	@CsvBindByPosition(position = 8, required = true)
	public int brojProdatihKarata;
	
	public Autobus() {
		
	}
	
	public Autobus(String sifraRelacije, String mjestoPolaska, String mjestoDolaska, String datumPolaska, String vrijemePolaska, String planiranoVrijemeDolaska, int peron, double cijena, int brojProdatihKarata) {
		this.sifraRelacije = sifraRelacije;
		this.mjestoPolaska = mjestoPolaska;
		this.mjestoDolaska = mjestoDolaska;
		this.datumPolaska = datumPolaska;
		this.vrijemePolaska = vrijemePolaska;
		this.planiranoVrijemeDolaska = planiranoVrijemeDolaska;
		this.peron = peron;
		this.cijena = cijena;
		this.brojProdatihKarata = brojProdatihKarata;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

	public String getMjestoPolaska() {
		return mjestoPolaska;
	}
	
	public void setMjestoPolaska(String mjestoPolaska) {
		this.mjestoPolaska = mjestoPolaska;
	}
	
	public String getMjestoDolaska() {
		return mjestoDolaska;
	}
	
	public void setMjestoDolaska(String mjestoDolaska) {
		this.mjestoDolaska = mjestoDolaska;
	}
	
	public String getDatumPolaska() {
		return datumPolaska;
	}
	
	public void setDatumPolaska(String datumPolaska) {
		this.datumPolaska = datumPolaska;
	}
	
	public String getVrijemePolaska() {
		return vrijemePolaska;
	}
	
	public void setVrijemePolaska(String vrijemePolaska) {
		this.vrijemePolaska = vrijemePolaska;
	}
	
	public String getPlaniranoVrijemeDolaska() {
		return planiranoVrijemeDolaska;
	}
	
	public void setPlaniranoVrijemeDolaska(String planiranoVrijemeDolaska) {
		this.planiranoVrijemeDolaska = planiranoVrijemeDolaska;
	}
	
	public int getPeron() {
		return peron;
	}
	
	public void setPeron(int peron) {
		this.peron = peron;
	}
	
	public int getBrojProdatihKarata() {
		return brojProdatihKarata;
	}
	
	public void setBrojProdatihKarata(int brojProdatihKarata) {
		this.brojProdatihKarata = brojProdatihKarata;
	}
	
	public String getSifraRelacije() {
		return sifraRelacije;
	}
	
	public void setSifraRelacije(String sifraRelacije) {
		this.sifraRelacije = sifraRelacije;
	}
	
	@Override
	public String toString() {
		return "Sifra relacija: " + this.sifraRelacije + ", mjesto polaska: " + this.mjestoPolaska + ", mjesto dolaska: " + this.mjestoDolaska + ", datum polaska: " + this.datumPolaska + ", vrijeme polaska: " + this.vrijemePolaska + ", planirano vrijeme dolaska: " + this.planiranoVrijemeDolaska + ", broj perona: " + this.peron + ", cijena: " + this.cijena + ", broj prodatih karata: " + this.brojProdatihKarata;
	}
}
