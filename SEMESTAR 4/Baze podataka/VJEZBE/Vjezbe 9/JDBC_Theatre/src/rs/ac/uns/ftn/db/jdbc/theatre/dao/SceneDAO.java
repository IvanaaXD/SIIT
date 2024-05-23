package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;

public interface SceneDAO extends CRUDDao<Scene, Integer> {

	// metoda koja vraca sve scene za odredjeno pozoriste
	public List<Scene> findSceneByTheatre(Integer idTheatre) throws SQLException;

	// metoda za 2 zadatak iz ComplexUIHandler
	public List<Scene> findSceneForSpecificNumberOfSeats() throws SQLException;

}
