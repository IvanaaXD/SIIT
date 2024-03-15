package zadatak5;

public class SupportArray {
	
    private String twoWordsConnector;
    private String lastWordConnector;
    
	public String getTwoWordsConnector() {
		return twoWordsConnector;
	}
	
	public void setTwoWordsConnector(String twoWordsConnector) {
		this.twoWordsConnector = twoWordsConnector;
	}
	
	public String getLastWordConnector() {
		return lastWordConnector;
	}
	
	public void setLastWordConnector(String lastWordConnector) {
		this.lastWordConnector = lastWordConnector;
	}
	
	@Override
	public String toString() {
	    return "SupportArray{" +
	           "twoWordsConnector='" + twoWordsConnector + '\'' +
	           ", lastWordConnector='" + lastWordConnector + '\'' +
	           '}';
	}


}

