package zadatak5;

public class Osoba {

	private String ime;
	private String prezime;
	private long JMBG;
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public long getJMBG() {
		return JMBG;
	}
	public void setJMBG(long jMBG) {
		JMBG = jMBG;
	}
	
	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + "]";
	}
	
	
}
