package rs.ac.uns.ftn.db.jdbc.theatre.ui_handler;

import java.sql.SQLException;
import java.util.Scanner;

public class MainUIHandler {

	public static Scanner sc = new Scanner(System.in);

	private final TheatreUIHandler theatreUIHandler = new TheatreUIHandler();
	private final SceneUIHandler sceneUIHandler = new SceneUIHandler();
	private final PlayUIHandler playUIHandler = new PlayUIHandler();
	private final ComplexQueryUIHandler complexQueryUIHandler = new ComplexQueryUIHandler();

	public void handleMainMenu() throws SQLException {

		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Rukovanje pozoristima");
			System.out.println("2 - Rukovanje scenama");
			System.out.println("3 - Rukovanje predstavama");
			System.out.println("4 - Kompleksni upiti");
			System.out.println("X - Izlazak iz programa");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				theatreUIHandler.handleTheatreMenu();
				break;
			case "2":
				sceneUIHandler.handleSceneMenu();
				break;
			case "3":
				playUIHandler.handleSceneMenu();
				break;
			case "4":
				complexQueryUIHandler.handleComplexQueryMenu();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}

}
