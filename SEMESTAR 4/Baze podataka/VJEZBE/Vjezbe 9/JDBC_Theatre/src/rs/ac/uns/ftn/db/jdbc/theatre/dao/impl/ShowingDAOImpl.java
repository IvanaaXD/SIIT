package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.ShowingDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.PlayShowingsStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlayStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery5.ShowingDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;

public class ShowingDAOImpl implements ShowingDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from showing";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next())
				return resultSet.getInt(1);
			else {
				return -1;
			}
		}
	}

	@Override
	public boolean delete(Showing entity) throws SQLException {
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
		String query = "delete from showing where ordnum_sh=?";
		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	// connection set as parameter because this method is used in a transaction (see
	// saveAll method)
	public boolean existsByIdTransactional(Connection connection, Integer id) throws SQLException {
		String query = "select * from showing where ordnum_sh=?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.isBeforeFirst();
			}
		}
	}

	@Override
	public Iterable<Showing> findAll() throws SQLException {
		String query = "select ordnum_sh, date_sh, time_sh, numofspec_sh, play_id_pl, scene_id_sc from showing";
		List<Showing> showingList = new ArrayList<Showing>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				// if we need time and not just date, we can not use getDate (it will only set
				// day, month and year)
				Timestamp timestamp = resultSet.getTimestamp(3);
				Date dateWithTime = null;
				if (timestamp != null)
					dateWithTime = new Date(timestamp.getTime());
				Showing showing = new Showing(resultSet.getInt(1), resultSet.getDate(2), dateWithTime,
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
				showingList.add(showing);
			}

		}
		return showingList;
	}

	@Override
	public Iterable<Showing> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Showing findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Showing entity) throws SQLException {
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			return saveTransactional(connection, entity);
		}
	}

	@Override
	public int saveAll(Iterable<Showing> entities) throws SQLException {

		int numSaved = 0;
		
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction start
			
			// insert or update every showing
			for (Showing entity : entities) {
				// if we want to see current changes, we must use the same connection
				// changes that happen here will only be visible on that connection
				boolean success = saveTransactional(connection, entity);
				if (success) numSaved++;
			}

			connection.commit(); // transaction ends successfully, changes are now visible to other connections
									// as well
		}
		
		return numSaved;
	}

	// will be used from save and saveAll, but also from findShowingForDeleting
	private boolean saveTransactional(Connection connection, Showing showing) throws SQLException {
		// ordnum_sh is in last place, so order of columns is same for update and insert
		// statement
		String insertCommand = "insert into showing (date_sh, time_sh, numofspec_sh, play_id_pl, scene_id_sc, ordnum_sh) values (?,TO_DATE(?, 'DD.MM.YYYY HH24:mi'),?,?,?,?)";
		String updateCommand = "update showing set date_sh=?, time_sh=TO_DATE(?, 'DD.MM.YYYY HH24:mi'), numofspec_sh=?, play_id_pl = ?, scene_id_sc = ? where ordnum_sh=?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				existsByIdTransactional(connection, showing.getOrdNum()) ? updateCommand : insertCommand)) {
			int i = 1;
			// set Date will assume the format is day.month.year, and won't use time
			preparedStatement.setDate(i++, (Date) showing.getDate());
			// we used TO_DATE conversion from string in insert/update query, so we match
			// that format
			String timePattern = "dd.MM.yyyy HH:mm";
			DateFormat df = new SimpleDateFormat(timePattern);
			preparedStatement.setString(i++, df.format(showing.getTime()));
			preparedStatement.setInt(i++, showing.getNumOfSpec());
			preparedStatement.setInt(i++, showing.getPlayId());
			preparedStatement.setInt(i++, showing.getSceneId());
			preparedStatement.setInt(i++, showing.getOrdNum());
			int rowsAffected =  preparedStatement.executeUpdate();
			return rowsAffected == 1;
		}
	}

	@Override
	public List<PlayStatsDTO> findBySceneId(Integer idScene) throws SQLException {

		String query = "select play_id_pl, sum(numofspec_sh) from showing  where scene_id_sc=?  group by play_id_pl";

		List<PlayStatsDTO> showingList = new ArrayList<PlayStatsDTO>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idScene);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					PlayStatsDTO showing = new PlayStatsDTO(resultSet.getInt(1), resultSet.getInt(2));
					showingList.add(showing);
				}
			}
		}

		return showingList;
	}

	@Override
	public List<PlayShowingsStatsDTO> findSumAvgCountForShowingPlays() throws SQLException {
		ArrayList<PlayShowingsStatsDTO> result = new ArrayList<PlayShowingsStatsDTO>();

		String query = "SELECT play_id_pl, SUM(numofspec_sh), AVG(numofspec_sh) , COUNT(*) FROM showing GROUP BY play_id_pl";

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					PlayShowingsStatsDTO showingDTO = new PlayShowingsStatsDTO(resultSet.getInt(1), resultSet.getInt(2),
							resultSet.getFloat(3), resultSet.getInt(4));
					result.add(showingDTO);
				}
			}
		}

		return result;
	}

	@Override
	public List<ShowingDTO> findShowingForDeleting(Connection connection) throws SQLException {
		String query = "select name_sc,s.numofseats_sc,s.theatre_id_th,s.id_sc,sh.ordnum_sh,sh.date_sh,sh.time_sh,sh.numofspec_sh,sh.play_id_pl\n"
				+ "from scene s, showing sh\n"
				+ "where sh.scene_id_sc = s.id_sc and sh.numofspec_sh>s.numofseats_sc and date_sh>sysdate";
		List<ShowingDTO> result = new ArrayList<ShowingDTO>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Timestamp timestamp = resultSet.getTimestamp(7);
					Date dateWithTime = null;
					if (timestamp != null)
						dateWithTime = new Date(timestamp.getTime());
					ShowingDTO showing = new ShowingDTO(resultSet.getString(1), resultSet.getInt(2),
							resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getDate(6),
							dateWithTime, resultSet.getInt(8), resultSet.getInt(9));
					result.add(showing);
				}
			}
		}
		return result;
	}

	@Override
	public List<Showing> findShowingByPlayId(Integer idPlay) throws SQLException {
		String query = "select ordnum_sh,date_sh,time_sh,numofspec_sh,play_id_pl,scene_id_sc from showing where play_id_pl = ?";
		List<Showing> showingList = new ArrayList<Showing>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idPlay);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					Showing showing = new Showing(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3),
							resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
					showingList.add(showing);
				}
			}
		}

		return showingList;
	}

	@Override
	public List<ShowingDTO> deleteAndInsertIntoShowing() throws SQLException {
		List<ShowingDTO> showingDTOs = null;
		try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
			connection.setAutoCommit(false); // transaction starts

			// findShowingsThatNeedToBeDeleted
			showingDTOs = findShowingForDeleting(connection);
			for (ShowingDTO deletedItem : showingDTOs) {
				// delete old showing
				// we can not call deleteById or delete since they create new connection
				String query = "delete from showing where ordnum_sh=?";
				try (PreparedStatement deleteStatement = connection.prepareStatement(query)) {
					deleteStatement.setInt(1, deletedItem.getOrdNum());
					deleteStatement.executeUpdate();
				}

				// number of sold out showings = number of required tickets / number or seats
				int n = deletedItem.getNumOfSpec() / deletedItem.getNumOfSeats();

				// how many tickets is still needed after n sold out showings
				int last = deletedItem.getNumOfSpec() % deletedItem.getNumOfSeats();

				// needed to choose ordnum_sh for new showings later
				// currentMax + 1 is first free id
				int currentMax = findMaxId(connection) + 1;

				// add new showings that are sold out
				for (int i = 0; i < n; i++) {
					// multiple new showings are created for one deleted, so calculate unique key
					// values
					Showing showing = createNewShowing(deletedItem, currentMax + i);
					saveTransactional(connection, showing);
				}

				// add last showing that is not sold out
				if (last != 0) {
					Showing showing = createNewShowing(deletedItem, currentMax + n);
					showing.setNumOfSpec(last);
					saveTransactional(connection, showing);
				}
			}

			connection.commit(); // transaction end
		}

		return showingDTOs;
	}

	// creates new showing base on data used for showing that is being deleted
	private Showing createNewShowing(ShowingDTO old, Integer newOrdNum) {
		Showing showing = new Showing();
		showing.setPlayId(old.getPlayId());
		showing.setOrdNum(newOrdNum); // multiple new showings will be created, and key has to be unique for each
		showing.setNumOfSpec(old.getNumOfSeats());
		showing.setIdsce(old.getSceneId());
		// TODO namesiti danasnji datum i uzastopne termine
		// if needed, add more parameters or change existing (etc, add start of previous
		// showing)
		showing.setDate(old.getDate());
		showing.setTime(old.getTime());
		return showing;
	}

	// used in deleteAndInsertIntoShowing
	private int findMaxId(Connection connection) throws SQLException {
		String query = "select max(ordnum_sh) from showing";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			if (resultSet.next())
				return resultSet.getInt(1);
			else {
				return 0;
			}
		}
	}

}
