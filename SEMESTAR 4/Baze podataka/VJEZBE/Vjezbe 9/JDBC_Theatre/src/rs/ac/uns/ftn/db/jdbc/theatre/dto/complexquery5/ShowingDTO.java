package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery5;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ShowingDTO {

	private String sceneName;
	private int numOfSeats;
	private int theatreId;
	private int sceneId;
	private int ordNum;
	private Date date;
	private Date time;
	private int numOfSpec;
	private int playId;

	public ShowingDTO() {
		super();
	}

	public ShowingDTO(String sceneName, int numofseats, int theatreId, int sceneId, int ordnum,
			Date date, Date time, int numofspec, int playId) {
		this.sceneName = sceneName;
		this.numOfSeats = numofseats;
		this.theatreId = theatreId;
		this.sceneId = sceneId;
		this.ordNum = ordnum;
		this.date = date;
		this.time = time;
		this.numOfSpec = numofspec;
		this.playId = playId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numofseats) {
		this.numOfSeats = numofseats;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public int getOrdNum() {
		return ordNum;
	}

	public void setOrdNum(int ordnum) {
		this.ordNum = ordnum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getNumOfSpec() {
		return numOfSpec;
	}

	public void setNumOfSpec(int numofspec) {
		this.numOfSpec = numofspec;
	}

	public int getPlayId() {
		return playId;
	}

	public void setPlayId(int playId) {
		this.playId = playId;
	}

	@Override
	public String toString() {
		String timePattern = "HH:mm";
		DateFormat df = new SimpleDateFormat(timePattern);
		return String.format("%-10d %-20.20s %-20.20s %-16d %-13d %-10d", ordNum, date.toString(),
				df.format(time), numOfSpec, playId, sceneId);
	}

	public static String getFormattedHeader() {
		return String.format("%-10s %-20s %-20s %-16s %-13s %-10s", "R. BROJ", "DATUM", "VREME", "BROJ GLEDALACA",
				"ID PREDSTAVE", "ID SCENE");
	}
}
