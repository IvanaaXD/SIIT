package zadatak2;

public class Country {

	private String CountryName;
	private String CapitalName;
	private double CapitalLatitude;
	private double CapitalLongitude;
	private String CountryCode;
	private String ContinentName;
	
	public String getCountryName() {
		return CountryName;
	}
	
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	
	public String getCapitalName() {
		return CapitalName;
	}
	
	public void setCapitalName(String capitalName) {
		CapitalName = capitalName;
	}
	
	public double getCapitalLatitude() {
		return CapitalLatitude;
	}
	
	public void setCapitalLatitude(double capitalLatitude) {
		CapitalLatitude = capitalLatitude;
	}
	
	public double getCapitalLongitude() {
		return CapitalLongitude;
	}
	
	public void setCapitalLongitude(double capitalLongitude) {
		CapitalLongitude = capitalLongitude;
	}
	
	public String getCountryCode() {
		return CountryCode;
	}
	
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	
	public String getContinentName() {
		return ContinentName;
	}
	
	public void setContinentName(String continentName) {
		ContinentName = continentName;
	}
	
	@Override
	public String toString() {
		return "Drzava: " + this.CountryName + ", glavni grad: " + this.CapitalName + ", sirina: " + this.CapitalLatitude + ", duzina: " + this.CapitalLongitude + ", kod drzave: " + this.CountryCode + ", kontinent: " + this.ContinentName;
	}

}
