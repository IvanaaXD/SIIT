package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2;

public class PlayShowingsStatsDTO {
	private int playId;
	private int totalNumOfSpecs;
	private float avgNumgOfSpecs;
	private int totalNumOfShowings;

	public PlayShowingsStatsDTO(int playId, int tnspecs, float ans, int tns) {
		this.playId = playId;
		this.totalNumOfSpecs = tnspecs;
		this.avgNumgOfSpecs = ans;
		this.totalNumOfShowings = tns;
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

	public float getAvgNumgOfSpecs() {
		return avgNumgOfSpecs;
	}

	public void setAvgNumgOfSpecs(float avgNumgOfSpecs) {
		this.avgNumgOfSpecs = avgNumgOfSpecs;
	}

	public int getTotalNumOfShowings() {
		return totalNumOfShowings;
	}

	public void setTotalNumOfShowings(int totalNumOfShowings) {
		this.totalNumOfShowings = totalNumOfShowings;
	}

	@Override
	public String toString() {
		return String.format("%-27d %-29.2f %-17d", totalNumOfSpecs, avgNumgOfSpecs, totalNumOfShowings);
	}

}
