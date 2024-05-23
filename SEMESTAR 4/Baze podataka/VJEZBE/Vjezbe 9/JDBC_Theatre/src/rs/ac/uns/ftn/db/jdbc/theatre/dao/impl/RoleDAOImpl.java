package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.RoleDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Role entity) throws SQLException {
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
	public Iterable<Role> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Role> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(Integer id) throws SQLException {

		return null;

	}

	@Override
	public boolean save(Role entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Role> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findCountByPlayId(int idPlay) throws SQLException {
		String query = "select count(*) from role where play_id_pl=?";
		int count = 0;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, idPlay);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					count = resultSet.getInt(1);
				}

			}
		}

		return count;
	}
	
	public List<Role> findRoleByPlayId(int playId) throws SQLException {
		String query = "select id_ro, name_ro, gender_ro, type_ro, play_id_pl from role where play_id_pl = ?";
		List<Role> result = new ArrayList<Role>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, playId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Role u = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getInt(5));
					result.add(u);
				}
			}
		}

		return result;
	}

	@Override
	public Integer findCountForRoleGender(int idPlay, String gender) throws SQLException {
		String query = "select count(gender_ro) from role where play_id_pl=? and gender_ro=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idPlay);
			preparedStatement.setString(2, gender);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1);
				} else
					return -1;
			}
		}
	}

}
