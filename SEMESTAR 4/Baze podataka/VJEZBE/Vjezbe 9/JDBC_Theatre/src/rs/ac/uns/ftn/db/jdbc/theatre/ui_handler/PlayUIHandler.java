package rs.ac.uns.ftn.db.jdbc.theatre.ui_handler;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.service.PlayService;
public class PlayUIHandler {
	private static final PlayService playService = new PlayService();

	public void handleSceneMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad predstavama:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jedne scene");
			System.out.println("4 - Unos vise scena");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja scenama");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				//TODO: implementirati
				break;
			case "4":
				//TODO: implementirati
				break;
			case "5":
				//TODO: implementirati
				break;
			case "6":
				//TODO: implementirati
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		System.out.println(Play.getFormattedHeader());
		try {
			for(Play play : playService.getAll()) {
				System.out.println(play);
			}
		} catch (SQLException e) {
			// TODO Auto-gene rated catch block
			e.printStackTrace();
		}
	}
	private void showById() {
		System.out.println("ID scene: ");
		int id_sc = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			System.out.println(Play.getFormattedHeader());
			Play play = playService.getById(id_sc);
			System.out.println(play);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
