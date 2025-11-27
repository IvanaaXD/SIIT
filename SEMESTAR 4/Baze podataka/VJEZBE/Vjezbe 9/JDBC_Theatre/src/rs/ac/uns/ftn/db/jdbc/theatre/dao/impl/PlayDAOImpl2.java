package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO2;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4.PlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class PlayDAOImpl2 implements PlayDAO2 {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Play entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deleteAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Play> findAll() throws SQLException {
		String query = "SELECT id_pl, name_pl, duration_pl, year_pl FROM play";
		List<Play> result = new ArrayList<Play>();
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement prepStmt = connection.prepareStatement(query);
				ResultSet res = prepStmt.executeQuery();) {
			while(res.next()) {
				result.add(new Play(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4)));
			}
		}
		return result;
	}

	@Override
	public Iterable<Play> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Play findById(Integer id) throws SQLException {
		String query = "SELECT id_pl, name_pl, duration_pl, year_pl FROM play WHERE id_pl = ?";
		Play result = null;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement prepStmt = connection.prepareStatement(query);)
		{
			prepStmt.setInt(1, id);
			try (ResultSet res = prepStmt.executeQuery();) {
				if(res.isBeforeFirst()) {
					res.next();
					result = new Play(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
				}
			}
		}
		return result;
	}

	@Override
	public boolean save(Play entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Play> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}



}
