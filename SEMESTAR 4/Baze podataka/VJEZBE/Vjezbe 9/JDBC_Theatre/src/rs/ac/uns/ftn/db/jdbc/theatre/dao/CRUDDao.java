package rs.ac.uns.ftn.db.jdbc.theatre.dao;

import java.sql.SQLException;

public interface CRUDDao<T, ID> {

	int count() throws SQLException;

	boolean delete(T entity) throws SQLException;

	int deleteAll() throws SQLException;

	boolean deleteById(ID id) throws SQLException;

	boolean existsById(ID id) throws SQLException;

	Iterable<T> findAll() throws SQLException;

	Iterable<T> findAllById(Iterable<ID> ids) throws SQLException;

	T findById(ID id) throws SQLException;

	boolean save(T entity) throws SQLException;

	int saveAll(Iterable<T> entities) throws SQLException;
}
