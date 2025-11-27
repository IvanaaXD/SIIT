package rs.ac.uns.ftn.db.jdbc.theatre.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO2;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.PlayDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.PlayDAOImpl2;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;

public class PlayService {
	private static final PlayDAO2 playDAO2 =  new PlayDAOImpl2();
	
	public Play getById(int id) throws SQLException {
		return playDAO2.findById(id);
	}
	
	public List<Play> getAll() throws SQLException {
		return (ArrayList<Play>) playDAO2.findAll();
	}
}
