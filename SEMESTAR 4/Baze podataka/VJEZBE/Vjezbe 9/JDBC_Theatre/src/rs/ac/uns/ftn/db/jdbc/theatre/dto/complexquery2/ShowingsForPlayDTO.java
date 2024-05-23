package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;

public class ShowingsForPlayDTO {
	private Play play;
	private List<Showing> showings;
	private PlayShowingsStatsDTO stats;

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public List<Showing> getShowings() {
		return showings;
	}

	public void setShowings(List<Showing> showings) {
		this.showings = showings;
	}

	public PlayShowingsStatsDTO getStats() {
		return stats;
	}

	public void setStats(PlayShowingsStatsDTO stats) {
		this.stats = stats;
	}

}
