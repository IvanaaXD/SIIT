package zadatak2;

import java.time.Duration;

import zadatak1.Autobus;

public class Second {

	private Autobus autobus;
	private Duration duration;
	

	public Autobus getAutobus() {
		return autobus;
	}
	public void setAutobus(Autobus autobus) {
		this.autobus = autobus;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return "Second [autobus=" + autobus + ", duration=" + duration + "]";
	}
}
