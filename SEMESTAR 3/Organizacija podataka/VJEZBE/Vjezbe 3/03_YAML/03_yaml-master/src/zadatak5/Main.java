package zadatak5;

import java.util.Scanner;

public class Main {

	static final String path = "resources/en.yml";
	
	public static void main(String[] args) {

		ReadFromYAML rfyaml = new ReadFromYAML();
		All all = rfyaml.read(path);
		
		System.out.println("Izaberite sta zelite da promijenite: ");
		System.out.println("1. Date");
		System.out.println("2. Time");
		System.out.println("3. Time zones");
		System.out.println("4. Support");

		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		switch(s) {
		case "1":
			System.out.println("Izaberite sta zelite da promijenite: ");

			DateFormat dateFormat = all.getDateFormat();
			System.out.println("1. weekday");
			System.out.println("2. shortWeekday");
			System.out.println("3. mediumWithWeekday");
			System.out.println("4. longWithWeekday");
			System.out.println("5. longFormat");
			System.out.println("5. mediumFormat");
			System.out.println("7. shortFormat");
			System.out.println("8. shortWithWeekday");
			System.out.println("9. shortMonth");
			System.out.println("10. mediumMonth");
			System.out.println("11. dateAtTime");

			String ss = sc.nextLine();
			String g = "";

			switch(ss) {
			case "1":
				g = dateFormat.getWeekday();
				System.out.println("Unesite promjenu: ");
				String sss = sc.nextLine();
				dateFormat.setWeekday(sss);
				break;
			case "2":
				g = dateFormat.getShortWeekday();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setShortWeekday(sss);
				break;
			case "3":
				g = dateFormat.getMediumWithWeekday();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setMediumWithWeekday(sss);
				break;
			case "4":
				g = dateFormat.getLongWithWeekday();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setLongWithWeekday(sss);
				break;
			case "5":
				g = dateFormat.getLongFormat();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setLongFormat(sss);
				break;
			case "6":
				g = dateFormat.getMediumFormat();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setMediumFormat(sss);
				break;
			case "7":
				g = dateFormat.getShortFormat();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setShortFormat(sss);
				break;
			case "8":
				g = dateFormat.getShortWithWeekday();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setShortWithWeekday(sss);
				break;
			case "9":
				g = dateFormat.getShortMonth();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setShortMonth(sss);
				break;
			case "10":
				g = dateFormat.getMediumMonth();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setMediumMonth(sss);
				break;
			case "11":
				g = dateFormat.getDateAtTime();
				System.out.println("Unesite promjenu: ");
				sss = sc.nextLine();
				dateFormat.setDateAtTime(sss);
				break;
			default:
				break;
			}			
			
			break;
		case "2":
			System.out.println("Izaberite sta zelite da promijenite: ");

			break;
		case "3":
			System.out.println("Izaberite sta zelite da promijenite: ");

			break;
		case "4":
			System.out.println("Izaberite sta zelite da promijenite: ");

			break;
		default:
			System.out.println("Pogresan unos");
			break;
		}

	}

}
