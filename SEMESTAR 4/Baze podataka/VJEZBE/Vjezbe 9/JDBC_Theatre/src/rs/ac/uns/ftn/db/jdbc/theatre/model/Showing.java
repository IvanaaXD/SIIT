package rs.ac.uns.ftn.db.jdbc.theatre.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Showing {
	private int ordNum;
	private Date date;
	private Date time;
	private int numOfSpec;
	private int playId;
	private int sceneId;

	public Showing() {
		super();
	}

	public Showing(int ordnum, Date date, Date time, int numOfSpec, int playId, int sceneId) {
		this.ordNum = ordnum;
		this.date = date;
		this.time = time;
		this.numOfSpec = numOfSpec;
		this.playId = playId;
		this.sceneId = sceneId;
	}

	public Showing(int numOfSpec, int playId) {
		this.numOfSpec = numOfSpec;
		this.playId = playId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numOfSpec;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + playId;
		result = prime * result + sceneId;
		result = prime * result + ordNum;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showing other = (Showing) obj;
		if (numOfSpec != other.numOfSpec)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (playId != other.playId)
			return false;
		if (sceneId != other.sceneId)
			return false;
		if (ordNum != other.ordNum)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
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

	public void setNumOfSpec(int numOfSpec) {
		this.numOfSpec = numOfSpec;
	}

	public int getPlayId() {
		return playId;
	}

	public void setPlayId(int playId) {
		this.playId = playId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public int getIdsce() {
		return sceneId;
	}

	public void setIdsce(int idsce) {
		this.sceneId = idsce;
	}

	@Override
	public String toString() {
		String timePattern = "HH:mm";
		DateFormat df = new SimpleDateFormat(timePattern);
		return String.format("%-10d %-20.20s %-20.20s %-16d %-13d %-10d", ordNum, date.toString(), df.format(time),
				numOfSpec, playId, sceneId);
	}

	public static String getFormattedHeader() {
		return String.format("%-10s %-20s %-20s %-16s %-13s %-10s", "R. BROJ", "DATUM", "VREME", "BROJ GLEDALACA",
				"ID PREDSTAVE", "ID SCENE");
	}

}
