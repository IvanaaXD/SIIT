package zadatak3;

public class Prva {

	private int idKorisnika;
	private String tekstTweeta;
	
	public int getIdKorisnika() {
		return idKorisnika;
	}
	
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	
	public String getTekstTweeta() {
		return tekstTweeta;
	}
	
	public void setTekstTweeta(String tekstTweeta) {
		this.tekstTweeta = tekstTweeta;
	}
	
	@Override
	public String toString() {
		return "Id korisnika: " + this.idKorisnika + ", tweet: " + this.tekstTweeta;
	}
}

