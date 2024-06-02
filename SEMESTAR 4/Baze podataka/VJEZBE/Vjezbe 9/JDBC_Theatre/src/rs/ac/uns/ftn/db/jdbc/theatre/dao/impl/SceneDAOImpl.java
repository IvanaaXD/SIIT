package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.SceneDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;

public class SceneDAOImpl implements SceneDAO {

	@Override
	public int count() throws SQLException {
		String query = "select count(*) from scene";

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
	public boolean delete(Scene entity) throws SQLException {
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
	public Iterable<Scene> findAll() throws SQLException {
		String query = "select id_sc, name_sc, numofseats_sc, theatre_id_th from scene";
		List<Scene> scenaList = new ArrayList<Scene>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Scene scena = new Scene(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
				scenaList.add(scena);
			}

		}
		return scenaList;
	}

	@Override
	public Iterable<Scene> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scene findById(Integer id) throws SQLException {
		String query = "select id_sc, name_sc, numofseats_sc, theatre_id_th from scene where id_sc = ?";
		Scene scene = null;

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.isBeforeFirst()) {
					resultSet.next();
					scene = new Scene(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
				}
			}
		}

		return scene;
	}

	@Override
	public boolean save(Scene entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Scene> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Scene> findSceneByTheatre(Integer idTheatre) throws SQLException {
		String query = "select id_sc, name_sc, numofseats_sc ,theatre_id_th from scene where theatre_id_th = ?";
		List<Scene> sceneList = new ArrayList<Scene>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, idTheatre);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					Scene scene = new Scene(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
					sceneList.add(scene);
				}
			}
		}

		return sceneList;
	}

	@Override
	public List<Scene> findSceneForSpecificNumberOfSeats() throws SQLException {
		String query = "SELECT S.id_sc,S.name_sc, s.numofseats_sc, s.theatre_id_th \n"
				+ "FROM Scene S,  Scene S1, Theatre T1 \n" + "WHERE s.numofseats_sc >= s1.numofseats_sc*0.8 AND \n"
				+ "s.numofseats_sc <= s1.numofseats_sc*1.2 AND s1.name_sc = 'Scena Joakim Vujic' AND s1.theatre_id_th = t1.id_th AND \n"
				+ "t1.name_th = 'Knjazevsko-srpski teatar Kragujevac'";
		List<Scene> sceneList = new ArrayList<Scene>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Scene scene = new Scene(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
				sceneList.add(scene);
			}

		}
		return sceneList;
	}
	
	public HashMap<Role, List<Scene>> findTheatreByScene(HashMap<Role, List<Showing>> sceneIds) throws SQLException{
		
		HashMap<Role, List<Scene>> ret = new HashMap<Role, List<Scene>> ();
		List<Scene> allScenes = (List<Scene>) findAll(); 
		
        for (Map.Entry<Role, List<Showing>> entry : sceneIds.entrySet()) {
            Role role = entry.getKey();
            List<Showing> showings = entry.getValue();

    		List<Scene> scenes = new ArrayList<Scene>();
    		for (Scene s: allScenes) {
    			for(Showing sh: showings) {
        			if (sh.getSceneId() == s.getId()){
        				if (!scenes.contains(s)) {
            				scenes.add(s);
        				}
        			}
    			}

    		}
    		
    		ret.put(role, scenes);
        }
		
		return ret;
	}
}
