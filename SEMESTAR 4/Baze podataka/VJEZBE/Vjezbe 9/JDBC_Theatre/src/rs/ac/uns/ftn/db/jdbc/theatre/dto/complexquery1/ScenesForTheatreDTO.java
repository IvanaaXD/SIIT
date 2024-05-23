package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery1;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;

public class ScenesForTheatreDTO {
	private Theatre theatre;
	private List<Scene> scenes;

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

}
