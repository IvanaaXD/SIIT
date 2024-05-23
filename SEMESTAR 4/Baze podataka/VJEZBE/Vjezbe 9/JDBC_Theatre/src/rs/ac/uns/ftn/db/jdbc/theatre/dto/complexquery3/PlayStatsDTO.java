package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3;

//for complex query 3
public class PlayStatsDTO {

	private int playId;
	private int totalNumOfSpecs;
	private int totalNumOfRoles;

	public PlayStatsDTO(int playId, int tns) {
		this.playId = playId;
		this.totalNumOfSpecs = tns;
	}

	public int getPlayId() {
		return playId;
	}

	public void setPlayId(int playId) {
		this.playId = playId;
	}

	public int getTotalNumOfSpecs() {
		return totalNumOfSpecs;
	}

	public void setTotalNumOfSpecs(int totalNumOfSpecs) {
		this.totalNumOfSpecs = totalNumOfSpecs;
	}

	public int getTotalNumOfRoles() {
		return totalNumOfRoles;
	}

	public void setTotalNumOfRoles(int totalNumOfRoles) {
		this.totalNumOfRoles = totalNumOfRoles;
	}

}
