package zadatak5;

public class All {
	
	private DateFormat dateFormat;
	private HelpMenu helpMenu;
	private Support support;
	private SupportArray supportArry;
	private TimeFormat timeFormat;
	private TimeZones timeZones;
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	public HelpMenu getHelpMenu() {
		return helpMenu;
	}
	public void setHelpMenu(HelpMenu helpMenu) {
		this.helpMenu = helpMenu;
	}
	public Support getSupport() {
		return support;
	}
	public void setSupport(Support support) {
		this.support = support;
	}
	public SupportArray getSupportArry() {
		return supportArry;
	}
	public void setSupportArry(SupportArray supportArry) {
		this.supportArry = supportArry;
	}
	public TimeFormat getTimeFormat() {
		return timeFormat;
	}
	public void setTimeFormat(TimeFormat timeFormat) {
		this.timeFormat = timeFormat;
	}
	public TimeZones getTimeZones() {
		return timeZones;
	}
	public void setTimeZones(TimeZones timeZones) {
		this.timeZones = timeZones;
	}
	
	@Override
	public String toString() {
	    return "YourTopLevelClass{" +
	           "dateFormat=" + dateFormat.toString() +
	           ", timeFormat=" + timeFormat.toString() +
	           ", timeZones=" + timeZones.toString() +
	           ", support=" + support.toString() +
	           ", supportArry=" + supportArry.toString() +
	           ", helpMenu=" + helpMenu.toString() +
	           '}';
	}

}
