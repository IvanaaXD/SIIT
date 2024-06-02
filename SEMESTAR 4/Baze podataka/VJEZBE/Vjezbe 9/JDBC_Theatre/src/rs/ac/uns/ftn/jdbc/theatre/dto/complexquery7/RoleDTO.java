package rs.ac.uns.ftn.jdbc.theatre.dto.complexquery7;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;

public class RoleDTO {

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Actor> getActor() {
		return actor;
	}
	
	public void setActor(List<Actor> actor) {
		this.actor = actor;
	}
	
	public RoleDTO(Role role, List<Actor> actor, List<Theatre> theatre) {
		super();
		this.role = role;
		this.actor = actor;
		this.theatre = theatre;
	}
	@Override
	public String toString() {
	
		return "RoleDTO [role=" + role + ", actor=" + actor + ", theatre=" + theatre + "]";
	}
	private Role role;
	private List<Actor> actor;
	private List<Theatre> theatre;
	
	public List<Theatre> getTheatre() {
		return theatre;
	}
	public void setTheatre(List<Theatre> theatre) {
		this.theatre = theatre;
	}
}
