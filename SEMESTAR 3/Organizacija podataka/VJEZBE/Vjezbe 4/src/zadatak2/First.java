package zadatak2;

import zadatak1.Autobus;

public class First {

	private Autobus autobus;
	private int num;
	
	public Autobus getAutobus() {
		return autobus;
	}
	public void setAutobus(Autobus autobus) {
		this.autobus = autobus;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "First [autobus=" + autobus + ", num=" + num + "]";
	}
}
