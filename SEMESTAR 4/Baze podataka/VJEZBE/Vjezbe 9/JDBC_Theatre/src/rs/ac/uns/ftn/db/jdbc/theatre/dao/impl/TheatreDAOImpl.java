package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.TheatreDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;

public class TheatreDAOImpl implements TheatreDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from theatre";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return -1;
			}
		}
	}

	@Override
	public boolean delete(Theatre entity) throws SQLException {
		return deleteById(entity.getId());
	}

	@Override
	public int deleteAll() throws SQLException {
		String query = "delete from theatre";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			int rowsAfffected = preparedStatement.executeUpdate();
			return rowsAfffected;
		}

	}

	@Override
	public boolean deleteById(Integer id) throws SQLException {
		String query = "delete from theatre where id_th=?";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return existsByIdTransactional(connection, id);
		}
	}

	// connection is a parameter because this method is used in a transaction (see
	// saveAll method)
	private boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
		String query = "select * from theatre where id_th=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Theatre> findAll() throws SQLException {
		String query = "select id_th, name_th, address_th, website_th, place_id_pl  from theatre";
		List<Theatre> theatreList = new ArrayList<Theatre>();
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Theatre theatre = new Theatre(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5));
				theatreList.add(theatre);
			}

		}
		return theatreList;
	}

	@Override
	public Iterable<Theatre> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Theatre> theatreList = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();

        String queryBegin = "select id_th, name_th, address_th, website_th, place_id_pl  from theatre where id_th in(";
        stringBuilder.append(queryBegin);

        for (@SuppressWarnings("unused")
        Integer id : ids) {
            stringBuilder.append("?,");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1); // delete last ','
        stringBuilder.append(")");

        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());) {

            int i = 0;
            for (Integer id : ids) {
            	
                preparedStatement.setInt(++i, id);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    theatreList.add(new Theatre(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getInt(5)));
                }
            }
        }

        return theatreList;
	}

	@Override
	public Theatre findById(Integer id) throws SQLException {
		String query = "select id_th, name_th, address_th, website_th,place_id_pl  from theatre where id_th = ?";
		Theatre pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Theatre(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getInt(5));
				}
			}
		}

		return pozoriste;
	}
	
	public Theatre findByScene(Integer id) throws SQLException {
		String query = "select id_th, name_th, address_th, website_th,place_id_pl  from theatre where id_th = ?";
		Theatre pozoriste = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();

					pozoriste = new Theatre(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getInt(5));
				}
			}
		}

		return pozoriste;
	}

	@Override
	public boolean save(Theatre entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Theatre> entities) throws SQLException {
		
		int rowsSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start

			// insert or update every theatre
			for (Theatre entity : entities) {
				boolean success = saveTransactional(connection, entity); // changes are visible only to current connection
				if (success) rowsSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections as well
		}
		
		return rowsSaved;
	}

	// used by save and saveAll
	private boolean saveTransactional(Connection connection, Theatre entity) throws SQLException {
		// id_th intentionally in the last place, so that the order between commands remains the same
		String insertCommand = "insert into theatre (name_th, address_th, website_th,place_id_pl, id_th) values (?, ? , ?, ?,?)";
		String updateCommand = "update theatre set name_th=?, address_th=?, website_th=?, place_id_pl=? where id_th=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				existsByIdTransactional(connection, entity.getId()) ? updateCommand : insertCommand)) {
			int i = 1;
			preparedStatement.setString(i++, entity.getName());
			preparedStatement.setString(i++, entity.getAddress());
			preparedStatement.setString(i++, entity.getWebsite());
			preparedStatement.setInt(i++, entity.getPlaceId());
			preparedStatement.setInt(i++, entity.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

}
