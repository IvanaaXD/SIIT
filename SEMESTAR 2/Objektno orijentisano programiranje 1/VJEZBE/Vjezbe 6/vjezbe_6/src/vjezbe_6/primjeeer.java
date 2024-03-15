package vjezbe_6;

import java.time.LocalDateTime;

public class primjeeer {

	public static void main(String[] args) {

		LocalDateTime date = LocalDateTime.now();
		
		System.out.println(date);
		
		GodisnjeDoba doba = GodisnjeDoba.LJETO;
		
		System.out.println(doba);
		
		System.out.println(doba.ordinal());
		
		for (GodisnjeDoba d: GodisnjeDoba.values()){
			System.out.println(d);
		}
		
		
	}
	

}

enum GodisnjeDoba{
	
	PROLJECE(1), LJETO(2), JESEN(3), ZIMA(4);

	int doba;

	private GodisnjeDoba() {
		
	}

	private GodisnjeDoba(int i) {
		
		this.doba = i;
	}
			
	
}