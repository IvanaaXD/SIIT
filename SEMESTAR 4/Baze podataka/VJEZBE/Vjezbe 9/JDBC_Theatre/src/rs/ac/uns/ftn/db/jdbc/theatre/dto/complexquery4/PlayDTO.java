package rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4;

import java.util.ArrayList;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;

public class PlayDTO {

	private int id;
	private String name;
	private double avgNumOfSpecs;

	private int numOfFemaleRoles;
	private int numOfMaleRoles;

	private ArrayList<Role> roles = new ArrayList<Role>();

	public PlayDTO(int id, String name, double avg) {
		this.id = id;
		this.name = name;
		this.avgNumOfSpecs = avg;
	}

	public PlayDTO(PlayDTO pDTO) {
		this.id = pDTO.getId();
		this.name = pDTO.getName();
		this.avgNumOfSpecs = pDTO.getAvgNumOfSpecs();
	}

	public void addRole(Role p) {
		roles.add(p);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvgNumOfSpecs() {
		return avgNumOfSpecs;
	}

	public void setAvgNumOfSpecs(double avgNumOfSpecs) {
		this.avgNumOfSpecs = avgNumOfSpecs;
	}

	public int getNumOfFemaleRoles() {
		return numOfFemaleRoles;
	}

	public void setNumOfFemaleRoles(int numOfFemaleRoles) {
		this.numOfFemaleRoles = numOfFemaleRoles;
	}

	public int getNumOfMaleRoles() {
		return numOfMaleRoles;
	}

	public void setNumOfMaleRoles(int numOfMaleRoles) {
		this.numOfMaleRoles = numOfMaleRoles;
	}

	public ArrayList<Role> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return String.format("%-6d %-10s %-8f ", id, name, avgNumOfSpecs);
	}

}
