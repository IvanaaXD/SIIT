package rs.ac.uns.ftn.db.jdbc.theatre.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.db.jdbc.theatre.dao.ActorDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.AssignmentDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.PlayDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.RoleDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.SceneDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.ShowingDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.TheatreDAO;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.ActorDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.AssignmentDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.PlayDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.RoleDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.SceneDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.ShowingDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dao.impl.TheatreDAOImpl;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery1.ScenesForTheatreDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery10.ComplexQuery10;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery10.ComplexQuery101;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.PlayShowingsStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.ShowingsForPlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlayStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlaysForSceneDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4.PlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery5.ShowingDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Assignment;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;
import rs.ac.uns.ftn.jdbc.theatre.dto.complexquery7.RoleDTO;

public class ComplexFuncionalityService {
	private static final TheatreDAO theatreDAO = new TheatreDAOImpl();
	private static final SceneDAO sceneDAO = new SceneDAOImpl();
	private static final ShowingDAO showingDAO = new ShowingDAOImpl();
	private static final PlayDAO playDAO = new PlayDAOImpl();
	private static final RoleDAO roleDAO = new RoleDAOImpl();
	private static final ActorDAO actorDAO = new ActorDAOImpl();
	private static final AssignmentDAO assignmentDAO = new AssignmentDAOImpl();

	
	//complex query 1
	public List<ScenesForTheatreDTO> getScenesForTheatres() throws SQLException{
		List<ScenesForTheatreDTO> ret = new ArrayList<ScenesForTheatreDTO>();
		for(Theatre t: theatreDAO.findAll()) {
			ScenesForTheatreDTO dto = new ScenesForTheatreDTO();
			dto.setTheatre(t);
			dto.setScenes(sceneDAO.findSceneByTheatre(t.getId()));
			ret.add(dto);
		}
		return ret;
	}
	
	//complex query 2
	public List<ShowingsForPlayDTO> getShowingsForPlays() throws SQLException{
		List<ShowingsForPlayDTO> ret = new ArrayList<ShowingsForPlayDTO>();
		for(PlayShowingsStatsDTO stats: showingDAO.findSumAvgCountForShowingPlays()) {
			ShowingsForPlayDTO dto = new ShowingsForPlayDTO();
			dto.setStats(stats);
			dto.setPlay(playDAO.findById(stats.getPlayId()));
			dto.setShowings(showingDAO.findShowingByPlayId(stats.getPlayId()));
			ret.add(dto);
		}
		return ret;
	}
	
	//complex query 3
	public List<PlaysForSceneDTO> getDataForComplexQuery() throws SQLException {
		ArrayList<PlaysForSceneDTO> result = new ArrayList<PlaysForSceneDTO>();

		for (Scene scene : sceneDAO.findSceneForSpecificNumberOfSeats()) {
			PlaysForSceneDTO dto = new PlaysForSceneDTO(scene);

			for (PlayStatsDTO sotDTO : showingDAO.findBySceneId(scene.getId())) {
				sotDTO.setTotalNumOfRoles(roleDAO.findCountByPlayId(sotDTO.getPlayId()));
				dto.addShowingOfPlay(sotDTO);
			}

			result.add(dto);
		}

		return result;
	}

	//complex query 4
	public List<PlayDTO> getMostVisitedPlays() throws SQLException {
		ArrayList<PlayDTO> result = new ArrayList<PlayDTO>();

		for (PlayDTO playDTO : playDAO.findMostVisitedPlays()) {
			PlayDTO pDTO = new PlayDTO(playDTO);

			for (Role role : roleDAO.findRoleByPlayId(playDTO.getId())) {
				pDTO.addRole(role);
			}

			pDTO.setNumOfMaleRoles(roleDAO.findCountForRoleGender(playDTO.getId(), "m"));
			pDTO.setNumOfFemaleRoles(roleDAO.findCountForRoleGender(playDTO.getId(), "z"));

			result.add(pDTO);
		}

		return result;
	}

	//complex query 5
	public List<ShowingDTO> deleteShowings() throws SQLException {
		return showingDAO.deleteAndInsertIntoShowing();
	}
	
	//complex query 7
	public List<RoleDTO> showRoles() throws SQLException {
		
		ArrayList<RoleDTO> roles = new ArrayList<RoleDTO>();
		
		List<Integer> rolesInAss = assignmentDAO.findRoles();
		Iterable<Role> allRoles = roleDAO.findAll();
		
		List<Role> rolesNotInAss = new ArrayList<Role>();
		for (Role role: allRoles) {
			if (!rolesInAss.contains(role.getId())) {
				rolesNotInAss.add(role);
			}
		}
		
		for (Role r:rolesNotInAss) {
			RoleDTO d = new RoleDTO(r, new ArrayList<Actor>(), new ArrayList<Theatre>());
			roles.add(d);
		}

		HashMap<Role, List<Showing>> sceneIds = new HashMap<Role, List<Showing>>();
		for (Role role: rolesNotInAss) {
			List<Showing> s = showingDAO.findShowingByPlayId(role.getPlayId());
			sceneIds.put(role, s);
		}
		
		HashMap<Role, List<Scene>> scenes = sceneDAO.findTheatreByScene(sceneIds);
		
		HashMap<Role, List<Theatre>> ts = new HashMap<Role, List<Theatre>>();
			
        for (Map.Entry<Role, List<Scene>> entry : scenes.entrySet()) {
            Role role = entry.getKey();
            List<Scene> sceness = entry.getValue();

    		List<Theatre> tsss = new ArrayList<Theatre>();
    		for (Scene s: sceness) {
    			Theatre t = theatreDAO.findById(s.getTheatreId());
    			if(!tsss.contains(t)) {
    				tsss.add(t);
    			}
    		}
    		ts.put(role, tsss);
        }

        for (int i = 0; i < roles.size(); i++) {
        	roles.get(i).setTheatre(ts.get(roles.get(i).getRole()));
        }
        
		List<Integer> actorsInAss = assignmentDAO.findActors();
		Iterable<Actor> allActors= actorDAO.findAll();
		
		List<Actor> actorsNotInAss = new ArrayList<Actor>();
		for (Actor a: allActors) {
			if (!actorsInAss.contains(a.getId())) {
				actorsNotInAss.add(a);
			}
		}
		
		HashMap<Role, List<Actor>> as = new HashMap<Role, List<Actor>>();
		
        for (Map.Entry<Role, List<Theatre>> entry : ts.entrySet()) {
            Role role = entry.getKey();
            List<Theatre> sceness = entry.getValue();
            
    		List<Actor> actorsInTheatre = new ArrayList<Actor>();

    		for (Actor a: actorsNotInAss) {
    			for (Theatre t :sceness) {
    				if (a.getTheatreId() == (t.getId())) {
    					actorsInTheatre.add(a);
    					continue;
    				}
    			}
    		}
    		as.put(role, actorsInTheatre);
        }
        
        for (int i = 0; i < roles.size(); i++) {
        	roles.get(i).setActor(as.get(roles.get(i).getRole()));
        }
		
		return roles;
	}
	
	// complex query 10
	public List<ComplexQuery10> getQuery10() throws SQLException{
		List<ComplexQuery10> query = new ArrayList<ComplexQuery10>();
		
		HashMap<Integer, Integer> mapa = assignmentDAO.findAverage();
		
		
		for(Actor actor: actorDAO.findAll()) {
			ComplexQuery10 dto = new ComplexQuery10();
			dto.setActor(actor);
			
			List<ComplexQuery101> qq = new ArrayList<ComplexQuery101>();
			for (Assignment r: assignmentDAO.findByActor(actor.getId())) {
				ComplexQuery101 c = new ComplexQuery101();
				c.setRole(roleDAO.findById(r.getPartId()));
				qq.add(c);
			}
			
			for (ComplexQuery101 c: qq) {
				List<Assignment> aa = assignmentDAO.findByRole(c.getRole().getId());
				List<Actor> aaa = new ArrayList<Actor>();
				List<Double> l = new ArrayList<>();
				
				for (Assignment a: aa) {
					Actor k = actorDAO.findById(a.getActorId());
					if (k.getId() != actor.getId()) {
						aaa.add(k);
						l.add(a.getHonorar());
					}
				}
				
				c.setActors(aaa);
				c.setHonorars(l);
			}
			
			dto.setCc(qq);	
			query.add(dto);
		}
		
		return query;
	}

}
