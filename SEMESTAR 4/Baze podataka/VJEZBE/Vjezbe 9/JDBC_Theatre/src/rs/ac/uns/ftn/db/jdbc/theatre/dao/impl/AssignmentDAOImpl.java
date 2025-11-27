package rs.ac.uns.ftn.db.jdbc.theatre.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.theatre.connection.Hikari_Practice;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.AssignmentDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Assignment;

public class AssignmentDAOImpl implements AssignmentDAO{

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Assignment entity) throws SQLException {
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
	public Iterable<Assignment> findAll() throws SQLException {
		String query = "Select ID_AS, HONORAR_AS, STARTDATE_AS, ENDDATE_AS, ACTOR_ID_AC, ROLE_ID_RO \r\n"
				+ "FROM assignment";
		List<Assignment> asses = new ArrayList<Assignment>();
		
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		
		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
			while (rs.next()) {
				Assignment ass = new Assignment(rs.getInt(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
				asses.add(ass);
			}
		}
		return asses;		
	}

	@Override
	public Iterable<Assignment> findAllById(Iterable<Integer> ids) throws SQLException {
		return null;
	}

	@Override
	public Assignment findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Assignment entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveAll(Iterable<Assignment> entities) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Integer> findRoles() throws SQLException {
	
		ArrayList<Integer> listOfIds = new ArrayList<Integer>();
		
		List<Assignment> asses = (List<Assignment>) findAll();
		
		for (Assignment ass: asses) {
			if (!listOfIds.contains(ass.getPartId())) {
				listOfIds.add(ass.getPartId());
			}
		}
		
		return listOfIds;
	}
	
	public List<Integer> findActors() throws SQLException {
		
		ArrayList<Integer> listOfIds = new ArrayList<Integer>();
		
		List<Assignment> asses = (List<Assignment>) findAll();
		
		for (Assignment ass: asses) {
			if (!listOfIds.contains(ass.getActorId())) {
				listOfIds.add(ass.getActorId());
			}
		}
		
		return listOfIds;
	}

	public List<Assignment> findByActor(int idActor) throws SQLException {
	    
	    String query = "select ID_AS, HONORAR_AS, STARTDATE_AS, ENDDATE_AS, ACTOR_ID_AC, ROLE_ID_RO from assignment where actor_id_ac=?";
	    
	    List<Assignment> roles = new ArrayList<Assignment>();
	    
	    
	    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
	    		PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, idActor);
	        
	        try (ResultSet sr = ps.executeQuery()) {
	            while (sr.next()) {
	                Assignment r = new Assignment(sr.getInt(1), sr.getInt(2), sr.getDate(3), sr.getDate(4), sr.getInt(5), sr.getInt(6));
	                roles.add(r);
	            }
	        }
	    }
	    
	    return roles;
	}

	
	public List<Assignment> findByRole(int idRole) throws SQLException {
		
		String query = "select ID_AS, HONORAR_AS, STARTDATE_AS,ENDDATE_AS,ACTOR_ID_AC,ROLE_ID_RO from assignment where ROLE_ID_RO=?";
		
		List<Assignment> roles = new ArrayList<Assignment>();
				
	    try (Connection connection = ConnectionUtil_HikariCP.getConnection();
	    		PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, idRole);
	        
	        try (ResultSet sr = ps.executeQuery()) {
	            while (sr.next()) {
	                Assignment r = new Assignment(sr.getInt(1), sr.getInt(2), sr.getDate(3), sr.getDate(4), sr.getInt(5), sr.getInt(6));
	                roles.add(r);
	            }
	        }
	    }
		
		return roles;	
	}
	
	public HashMap<Integer, Integer> findAverage() throws SQLException {
		
		HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		
		String query = "SELECT ROLE_ID_RO, AVG(honorar_as) FROM assignment GROUP BY role_id_ro";
		
		Connection connection = ConnectionUtil_HikariCP.getConnection();
		
		try (PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				mapa.put(rs.getInt(1), rs.getInt(2));
			}
			
		}
		
		return mapa;
		
	}
}
