package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery10;

import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Actor;

public class ComplexQuery10 {

	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
	private Actor actor;

	private List<ComplexQuery101> cc;

	@Override
	public String toString() {
		return "ComplexQuery10 [actor=" + actor + ", cc=" + cc + "]";
	}
	public List<ComplexQuery101> getCc() {
		return cc;
	}
	public void setCc(List<ComplexQuery101> cc) {
		this.cc = cc;
	}
	
}
