package rs.ac.uns.ftn.db.jdbc.report.dto;

public class ExtendedComponent {
	private String name;
	private String type;
	private String manufacturer;
	private int totalPriceForComponent;

	public ExtendedComponent(String name, String type, String manufacturer, int totalPriceForComponent) {
		super();
		this.name = name;
		this.type = type;
		this.manufacturer = manufacturer;
		this.totalPriceForComponent = totalPriceForComponent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getTotalPriceForComponent() {
		return totalPriceForComponent;
	}

	public void setTotalPriceForComponent(int totalPriceForComponent) {
		this.totalPriceForComponent = totalPriceForComponent;
	}

	@Override
	public String toString() {
		return String.format("COMPONENT{Name: 20%s, Type: 15%s, Manufacturer: %s, totalPriceForComponent: %d}", name, type, manufacturer, totalPriceForComponent);
	}

}
