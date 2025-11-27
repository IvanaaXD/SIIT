package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery10;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class ComplexQuery101 {

	private Role role;
	private List<Actor> actors;
	private List<Double> honorars;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public List<Double> getHonorars() {
		return honorars;
	}
	public void setHonorars(List<Double> honorars) {
		this.honorars = honorars;
	}
	@Override
	public String toString() {
		return "ComplexQuery101 [role=" + role + ", actors=" + actors + ", honorars=" + honorars + "]";
	}
	
	
	
	
}
