package rs.ac.uns.ftn.db.jdbc.theatre.ui_handler;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;
import rs.ac.uns.ftn.db.jdbc.theatre.service.SceneService;

public class SceneUIHandler {

	private static final SceneService sceneService = new SceneService();

	public void handleSceneMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad scenama:");
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
		System.out.println(Scene.getFormattedHeader());

		try {
			for (Scene scene : sceneService.getAll()) {
				System.out.println(scene);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showById() {
		System.out.println("ID scene: ");
		int id_sc = Integer.parseInt(MainUIHandler.sc.nextLine());
		try {
			Scene scene = sceneService.getById(id_sc);
			System.out.println(Scene.getFormattedHeader());
			System.out.println(scene);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
