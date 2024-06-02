package rs.ac.uns.ftn.jdbc.theatre.dto.complexquery8;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class Query8DTO {

	private Actor actor;
	private Play play;
	private Role role;
	private List<Actor> actors;
	private int numOfActors;
	
	public Query8DTO(Actor actor, Play play, Role role, List<Actor> actors, int numOfActors) {
		super();
		this.actor = actor;
		this.play = play;
		this.role = role;
		this.actors = actors;
		this.numOfActors = numOfActors;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

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

	public int getNumOfActors() {
		return numOfActors;
	}

	public void setNumOfActors(int numOfActors) {
		this.numOfActors = numOfActors;
	}

	@Override
	public String toString() {
		return "Query8DTO [actor=" + actor + ", play=" + play + ", role=" + role + ", actors=" + actors
				+ ", numOfActors=" + numOfActors + "]";
	}
	
	
}
