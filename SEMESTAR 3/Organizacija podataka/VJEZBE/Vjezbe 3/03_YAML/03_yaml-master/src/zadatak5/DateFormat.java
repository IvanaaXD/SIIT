package zadatak5;

public class DateFormat {
    private String weekday;
    private String shortWeekday;
    private String mediumWithWeekday;
    private String longWithWeekday;
    private String longFormat;
    private String mediumFormat;
    private String shortFormat;
    private String shortWithWeekday;
    private String shortMonth;
    private String mediumMonth;
    private String dateAtTime;
    
	public String getWeekday() {
		return weekday;
	}
	
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	
	public String getShortWeekday() {
		return shortWeekday;
	}
	
	public void setShortWeekday(String shortWeekday) {
		this.shortWeekday = shortWeekday;
	}
	
	public String getMediumWithWeekday() {
		return mediumWithWeekday;
	}
	
	public void setMediumWithWeekday(String mediumWithWeekday) {
		this.mediumWithWeekday = mediumWithWeekday;
	}
	
	public String getLongWithWeekday() {
		return longWithWeekday;
	}
	
	public void setLongWithWeekday(String longWithWeekday) {
		this.longWithWeekday = longWithWeekday;
	}
	
	public String getLongFormat() {
		return longFormat;
	}
	
	public void setLongFormat(String longFormat) {
		this.longFormat = longFormat;
	}
	
	public String getMediumFormat() {
		return mediumFormat;
	}
	
	public void setMediumFormat(String mediumFormat) {
		this.mediumFormat = mediumFormat;
	}
	
	public String getShortFormat() {
		return shortFormat;
	}
	
	public void setShortFormat(String shortFormat) {
		this.shortFormat = shortFormat;
	}
	
	public String getShortWithWeekday() {
		return shortWithWeekday;
	}
	
	public void setShortWithWeekday(String shortWithWeekday) {
		this.shortWithWeekday = shortWithWeekday;
	}
	
	public String getShortMonth() {
		return shortMonth;
	}
	
	public void setShortMonth(String shortMonth) {
		this.shortMonth = shortMonth;
	}
	
	public String getMediumMonth() {
		return mediumMonth;
	}
	
	public void setMediumMonth(String mediumMonth) {
		this.mediumMonth = mediumMonth;
	}
	
	public String getDateAtTime() {
		return dateAtTime;
	}
	
	public void setDateAtTime(String dateAtTime) {
		this.dateAtTime = dateAtTime;
	}
	
	@Override
	public String toString() {
	    return "DateFormat{" +
	           "weekday='" + weekday + '\'' +
	           ", shortWeekday='" + shortWeekday + '\'' +
	           ", mediumWithWeekday='" + mediumWithWeekday + '\'' +
	           ", longWithWeekday='" + longWithWeekday + '\'' +
	           ", longFormat='" + longFormat + '\'' +
	           ", mediumFormat='" + mediumFormat + '\'' +
	           ", shortFormat='" + shortFormat + '\'' +
	           ", shortWithWeekday='" + shortWithWeekday + '\'' +
	           ", shortMonth='" + shortMonth + '\'' +
	           ", mediumMonth='" + mediumMonth + '\'' +
	           ", dateAtTime='" + dateAtTime + '\'' +
	           '}';
	}

}

