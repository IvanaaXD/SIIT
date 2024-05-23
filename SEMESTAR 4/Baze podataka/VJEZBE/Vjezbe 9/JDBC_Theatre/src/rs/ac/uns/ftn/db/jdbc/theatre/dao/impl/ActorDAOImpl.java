package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.ActorDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;

public class ActorDAOImpl implements ActorDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Actor entity) throws SQLException {
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
	public Iterable<Actor> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Actor> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Actor entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Actor> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Actor> findAllWithAppropriateHonorar() throws SQLException {
		String query = "select distinct id_ac, name_ac, salary_ac " + 
				"from actor, assignment a " + 
				"where id_ac = actor_id_ac " + 
				"    and honorar_as > (select avg(honorar_as)  " + 
				"                        from assignment " + 
				"                        where role_id_ro = a.role_id_ro)";
		List<Actor> actorList = new ArrayList<Actor>();

		try (Connection connection = ConnectionUtil_HikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setId(resultSet.getInt(1));
				actor.setName(resultSet.getString(2));
				actor.setSalary(resultSet.getFloat(3));
				actorList.add(actor);
			}

		}
		return actorList;
	}

}
