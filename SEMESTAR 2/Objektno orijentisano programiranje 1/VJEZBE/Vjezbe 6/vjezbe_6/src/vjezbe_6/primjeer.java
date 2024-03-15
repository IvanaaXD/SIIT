package vjezbe_6;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class primjeer {

	public static void main(String[] args) {

		LocalDate date = LocalDate.parse("04.04.2020.", DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		
		System.out.println(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
		
	}

}
