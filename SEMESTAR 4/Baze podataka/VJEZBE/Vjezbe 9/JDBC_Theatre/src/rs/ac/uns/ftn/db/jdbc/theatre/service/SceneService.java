package rs.ac.uns.ftn.db.jdbc.theatre.service;

import java.sql.SQLException;
import java.util.ArrayList;

import rs.ac.uns.ftn.db.jdbc.theatre.dao.SceneDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.SceneDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;

public class SceneService {
	private static final SceneDAO sceneDAO = new SceneDAOImpl();

	public ArrayList<Scene> getAll() throws SQLException {
		return (ArrayList<Scene>) sceneDAO.findAll();
	}

	public Scene getById(int id) throws SQLException {
		return sceneDAO.findById(id);
	}

	public ArrayList<Scene> getScenesByTheatre(int id) throws SQLException {
		return (ArrayList<Scene>) sceneDAO.findSceneByTheatre(id);
	}
}
