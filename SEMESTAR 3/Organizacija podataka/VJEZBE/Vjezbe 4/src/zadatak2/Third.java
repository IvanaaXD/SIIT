package zadatak2;

import zadatak1.Autobus;

public class Third {

	private Autobus autobus;
	private double num;
	
	public Autobus getAutobus() {
		return autobus;
	}
	public void setAutobus(Autobus autobus) {
		this.autobus = autobus;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "Third [autobus=" + autobus + ", num=" + num + "]";
	}
	
	
}
