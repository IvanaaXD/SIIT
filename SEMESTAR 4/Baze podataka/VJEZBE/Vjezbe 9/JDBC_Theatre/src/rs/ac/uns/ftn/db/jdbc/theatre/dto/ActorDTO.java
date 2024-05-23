package rs.ac.uns.ftn.db.jdbc.theatre.dto;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class ActorDTO {

	private Actor actor;
	private List<Role> roles;
	
	public ActorDTO() {
		roles = new ArrayList<Role>();
	}

	public ActorDTO(Actor actor, List<Role> roles) {
		super();
		this.actor = actor;
		this.roles = roles;
	}
	
	public Actor getActor() {
		return actor;
	}
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
