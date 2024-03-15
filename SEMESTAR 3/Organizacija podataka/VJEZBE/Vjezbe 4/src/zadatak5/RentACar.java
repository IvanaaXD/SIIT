package zadatak5;

public class RentACar {
	
	private String sifraIznajmljivanja;
	private String mjestoIznajmljivanja;
	private String datumIznajmljivanja;
	private String datumVracanja;
	private double predjenoKilometara;
	private double cijenaIznajmljivanja;
	private int brojDanaIznajmljivanja;
	private Osoba osoba;
	
	public String getSifraIznajmljivanja() {
		return sifraIznajmljivanja;
	}
	public void setSifraIznajmljivanja(String sifraIznajmljivanja) {
		this.sifraIznajmljivanja = sifraIznajmljivanja;
	}
	public String getMjestoIznajmljivanja() {
		return mjestoIznajmljivanja;
	}
	public void setMjestoIznajmljivanja(String mjestoIznajmljivanja) {
		this.mjestoIznajmljivanja = mjestoIznajmljivanja;
	}
	public String getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivanja(String datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	public String getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(String datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	public double getPredjenoKilometara() {
		return predjenoKilometara;
	}
	public void setPredjenoKilometara(double predjenoKilometara) {
		this.predjenoKilometara = predjenoKilometara;
	}
	public double getCijenaIznajmljivanja() {
		return cijenaIznajmljivanja;
	}
	public void setCijenaIznajmljivanja(double cijenaIznajmljivanja) {
		this.cijenaIznajmljivanja = cijenaIznajmljivanja;
	}
	public int getBrojDanaIznajmljivanja() {
		return brojDanaIznajmljivanja;
	}
	public void setBrojDanaIznajmljivanja(int brojDanaIznajmljivanja) {
		this.brojDanaIznajmljivanja = brojDanaIznajmljivanja;
	}
	public Osoba getOsoba() {
		return osoba;
	}
	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}
	@Override
	public String toString() {
		return "RentACar [sifraIznajmljivanja=" + sifraIznajmljivanja + ", mjestoIznajmljivanja=" + mjestoIznajmljivanja
				+ ", datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja=" + datumVracanja
				+ ", predjenoKilometara=" + predjenoKilometara + ", cijenaIznajmljivanja=" + cijenaIznajmljivanja
				+ ", brojDanaIznajmljivanja=" + brojDanaIznajmljivanja + ", osoba=" + osoba + "]";
	}
	
	

}
