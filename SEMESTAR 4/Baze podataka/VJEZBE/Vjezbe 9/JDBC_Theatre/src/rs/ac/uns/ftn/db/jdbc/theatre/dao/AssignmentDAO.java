package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Assignment;

public interface AssignmentDAO extends CRUDDao<Assignment, Integer> {
	
	List<Integer> findRoles() throws SQLException;

	List<Integer> findActors()  throws SQLException;

	List<Assignment> findByActor(int id) throws SQLException;
	
	List<Assignment> findByRole(int id) throws SQLException;

	HashMap<Integer, Integer> findAverage() throws SQLException;

}
