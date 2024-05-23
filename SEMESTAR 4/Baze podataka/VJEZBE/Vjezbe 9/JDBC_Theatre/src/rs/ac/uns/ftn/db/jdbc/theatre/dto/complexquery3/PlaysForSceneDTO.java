package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3;

import java.util.ArrayList;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;

public class PlaysForSceneDTO {
	private Scene scene;
	private ArrayList<PlayStatsDTO> plays = new ArrayList<PlayStatsDTO>();

	public PlaysForSceneDTO(Scene scena) {
		this.scene = scena;
	}

	public void addShowingOfPlay(PlayStatsDTO dto) {
		this.plays.add(dto);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public ArrayList<PlayStatsDTO> getShowingOfPlaysDTO() {
		return plays;
	}

	public void setShowingOfPlaysDTO(ArrayList<PlayStatsDTO> showingOfPlaysDTO) {
		this.plays = showingOfPlaysDTO;
	}

}
