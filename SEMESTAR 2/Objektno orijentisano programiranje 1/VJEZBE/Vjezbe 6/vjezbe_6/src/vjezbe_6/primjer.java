package vjezbe_6;

import java.time.LocalDate;

public class primjer {

	public static void main(String[] args) {

		//LocalDate date = LocalDate.now();
		LocalDate date = LocalDate.of(2020, 1, 1);
		date = date.withYear(2015).withMonth(4);		
		
		date = date.minusDays(35);
		
		
		System.out.println(date);
		
	}

}
