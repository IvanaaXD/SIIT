package rs.ac.uns.ftn.db.jdbc.theatre.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;
import rs.ac.uns.ftn.db.jdbc.theatre.service.TheatreService;

public class TheatreUIHandler {

	private static final TheatreService theatreService = new TheatreService();

	public void handleTheatreMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad pozoristima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog pozorista");
			System.out.println("4 - Unos vise pozorista");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja pozoristima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleSingleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			case "6":
				handleDelete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		System.out.println(Theatre.getFormattedHeader());

		try {
			for (Theatre th : theatreService.getAll()) {
				System.out.println(th);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("ID pozorista: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Theatre theatre = theatreService.getById(id);
			System.out.println(Theatre.getFormattedHeader());
			System.out.println(theatre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleInsert() {
		System.out.println("IDPOZ: ");
		int id_th = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Naziv: ");
		String name_th = MainUIHandler.sc.nextLine();

		System.out.println("Adresa: ");
		String address_th = MainUIHandler.sc.nextLine();

		System.out.println("Sajt: ");
		String website_th = MainUIHandler.sc.nextLine();

		System.out.println("Id mesta: ");
		int place_id_pl = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			theatreService.save(new Theatre(id_th, name_th, address_th, website_th, place_id_pl));
			System.out.println("Dodavanje uspesno.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleUpdate() {
		System.out.println("ID pozorista: ");
		int id_th = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			if (!theatreService.existsById(id_th)) {
				System.out.println("Uneta vrednost ne postoji!");
				return;
			}

			System.out.println("Naziv: ");
			String name_th = MainUIHandler.sc.nextLine();

			System.out.println("Adresa: ");
			String address_th = MainUIHandler.sc.nextLine();

			System.out.println("Sajt: ");
			String website_th = MainUIHandler.sc.nextLine();

			System.out.println("Mesto: ");
			int place_id_pl = Integer.parseInt(MainUIHandler.sc.nextLine());

			boolean success = theatreService.save(new Theatre(id_th, name_th, address_th, website_th, place_id_pl));
			if (success) {
				System.out.println("Pozoriste uspesno izmenjeno.");
			} else {
				System.out.println("Izmena pozorista nije uspela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("ID pozorista: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			boolean success = theatreService.deleteById(id);
			if (success) {
				System.out.println("Pozoriste uspesno obrisano.");
			} else {
				System.out.println("Brisanje pozorista nije uspelo.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleMultipleInserts() {
		List<Theatre> theatreList = new ArrayList<>();
		String answer;
		do {
			System.out.println("ID pozorista: ");
			int id_th = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Naziv: ");
			String name_th = MainUIHandler.sc.nextLine();

			System.out.println("Adresa: ");
			String address_th = MainUIHandler.sc.nextLine();

			System.out.println("Sajt: ");
			String website_th = MainUIHandler.sc.nextLine();

			System.out.println("Mesto: ");
			int place_id_pl = Integer.parseInt(MainUIHandler.sc.nextLine());

			theatreList.add(new Theatre(id_th, name_th, address_th, website_th, place_id_pl));

			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));

		try {
			int rowsSaved = theatreService.saveAll(theatreList);
			System.out.println("Uspesno a�urirano " + rowsSaved + " pozorista.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
