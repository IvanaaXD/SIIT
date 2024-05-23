package rs.ac.uns.ftn.db.jdbc.theatre.service;

import java.sql.SQLException;
import java.util.ArrayList;

import rs.ac.uns.ftn.db.jdbc.theatre.dao.ShowingDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.ShowingDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.PlayShowingsStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlayStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;

public class ShowingService {
	private static final ShowingDAO showingDAO = new ShowingDAOImpl();

	public ArrayList<PlayStatsDTO> getBySceneId(int id) throws SQLException {
		return (ArrayList<PlayStatsDTO>) showingDAO.findBySceneId(id);
	}

	public ArrayList<Showing> getAll() throws SQLException {
		return (ArrayList<Showing>) showingDAO.findAll();
	}

	public ArrayList<Showing> getShowingsForPlay(Integer id) throws SQLException {
		return (ArrayList<Showing>) showingDAO.findShowingByPlayId(id);
	}

	public ArrayList<PlayShowingsStatsDTO> getSumAvgCountForShowing() throws SQLException {
		return (ArrayList<PlayShowingsStatsDTO>) showingDAO.findSumAvgCountForShowingPlays();
	}
}
