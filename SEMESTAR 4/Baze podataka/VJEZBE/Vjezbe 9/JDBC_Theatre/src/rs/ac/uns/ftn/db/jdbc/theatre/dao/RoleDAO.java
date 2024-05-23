package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public interface RoleDAO extends CRUDDao<Role, Integer> {

	int findCountByPlayId(int idPlay) throws SQLException;
	
	List<Role> findRoleByPlayId(int playId) throws SQLException;

	Integer findCountForRoleGender(int idPlay, String gender) throws SQLException;

}
