package zadatak1;

public class Magazine {

    private String Title;
    private String Month;
    private int Year;
    
	public int getYear() {
		return Year;
	}
	
	public void setYear(int year) {
		Year = year;
	}
	
	public String getMonth() {
		return Month;
	}
	
	public void setMonth(String month) {
		Month = month;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}
	
	@Override
	public String toString() {
		return "Magazine {Title=" + Title + ", Month=" + Month +", Year="
				+ Year + "}";
	}
    
}
