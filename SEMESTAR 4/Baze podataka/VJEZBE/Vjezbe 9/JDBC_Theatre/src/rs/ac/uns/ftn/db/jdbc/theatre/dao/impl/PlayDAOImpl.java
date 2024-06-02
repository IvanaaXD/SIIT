package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4.PlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class PlayDAOImpl implements PlayDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Play> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Play findById(Integer id) throws SQLException {
		String query = "select id_pl, name_pl, duration_pl,year_pl from play where id_pl = ?";
		Play play = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					play = new Play(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
				}
			}
		}

		return play;

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

	@Override
	public List<PlayDTO> findMostVisitedPlays() throws SQLException {
		String query = "SELECT p.id_pl ,name_pl, AVG(s.numofspec_sh)\n" + "FROM play p, showing s \n"
				+ "WHERE p.id_pl = s.play_id_pl\n" + "GROUP BY p.id_pl, name_pl \n"
				+ "HAVING AVG(s.numofspec_sh) >= ALL(SELECT AVG(numofspec_sh) FROM showing GROUP BY play_id_pl)";

		List<PlayDTO> result = new ArrayList<PlayDTO>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				PlayDTO playDTO = new PlayDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3));
				result.add(playDTO);
			}

		}

		return result;
	}
	
	public List<Play> findByRole(List<Role> roles) throws SQLException{
		List<Play> plays = new ArrayList<Play>();
		
		for (Role role : roles) {
			Play p = findById(role.getPlayId());
			plays.add(p);
		}
		return plays;
	}

}
