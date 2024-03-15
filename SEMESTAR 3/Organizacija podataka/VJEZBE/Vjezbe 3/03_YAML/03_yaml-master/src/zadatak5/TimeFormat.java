package zadatak5;

public class TimeFormat {
    private String tiny;
    private String tinyOnTheHour;
    
	public String getTiny() {
		return tiny;
	}
	
	public void setTiny(String tiny) {
		this.tiny = tiny;
	}
	
	public String getTinyOnTheHour() {
		return tinyOnTheHour;
	}
	
	public void setTinyOnTheHour(String tinyOnTheHour) {
		this.tinyOnTheHour = tinyOnTheHour;
	}
	
	@Override
	public String toString() {
	    return "TimeFormat{" +
	           "tiny='" + tiny + '\'' +
	           ", tinyOnTheHour='" + tinyOnTheHour + '\'' +
	           '}';
	}


}
