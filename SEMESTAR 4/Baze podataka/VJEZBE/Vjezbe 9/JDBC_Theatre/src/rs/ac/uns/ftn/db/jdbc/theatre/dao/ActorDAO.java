package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;

public interface ActorDAO extends CRUDDao<Actor, Integer> {

	List<Actor> findAllWithAppropriateHonorar() throws SQLException;
	
}
