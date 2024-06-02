package rs.ac.uns.ftn.db.jdbc.report.dto;

import java.util.ArrayList;
import java.util.List;

public class ExtendedComputer {
	private List<ExtendedComponent> extendedComponents;
	private int id;
	private String name;
	private int totalPrice;
	
	public ExtendedComputer(int idr, String name) {
		super();
		this.extendedComponents = new ArrayList<ExtendedComponent>();
		this.id = idr;
		this.name = name;
		this.totalPrice = 0;
	}

	public List<ExtendedComponent> getExtendedComponents() {
		return extendedComponents;
	}

	public void setExtendedComponents(List<ExtendedComponent> extendedComponents) {
		this.extendedComponents = extendedComponents;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String computerData = "\nCOMPUTER{Id:" + id + ", Name:" + name
		+ ", TotalPrice:" + totalPrice;
		sb.append(computerData);
		
		for(ExtendedComponent ec : extendedComponents) {
			sb.append("\n\t" + ec.toString());
		}
		
		sb.append("}\n");
		
		return sb.toString();
	}
	
}
