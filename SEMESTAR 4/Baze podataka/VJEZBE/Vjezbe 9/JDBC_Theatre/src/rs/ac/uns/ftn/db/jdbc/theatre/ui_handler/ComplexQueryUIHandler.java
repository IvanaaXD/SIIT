package rs.ac.uns.ftn.db.jdbc.theatre.ui_handler;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery1.ScenesForTheatreDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery2.ShowingsForPlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlayStatsDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery3.PlaysForSceneDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery4.PlayDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.dto.complexquery5.ShowingDTO;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Role;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Play;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Scene;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Showing;
import rs.ac.uns.ftn.db.jdbc.theatre.model.Theatre;
import rs.ac.uns.ftn.db.jdbc.theatre.service.ComplexFuncionalityService;
import rs.ac.uns.ftn.db.jdbc.theatre.service.ShowingService;

public class ComplexQueryUIHandler {

	private static final ComplexFuncionalityService complexQueryService = new ComplexFuncionalityService();
	private static final ShowingService showingService = new ShowingService();

	public void handleComplexQueryMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite funkcionalnost:");
			System.out.println(
					"\n1  - Za svako pozoriste prikazati listu scene koje ima. Ukoliko pozoriste nema scenu ispisati: NEMA SCENE");
			System.out.println(
					"\n2  - Prikazati informacije o predstavama koje se prikazuju. Pored osnovnih informacija o predstavama prikazati sva prikazivanja"
							+ "\n     za svaku od njih. Za svako prikazivanje prikazati ukupan broj gledalaca, prosecan broj gledalaca i broj prikazivanja.");
			System.out.println(
					"\n3  - Prikazati nazive scena i broj sedista za sve scene ciji broj sedista je u intervalu plus/minus 20% od broja sedista"
							+ "\n     koje ima scena Joakim Vujic pozorista Knjazevsko-srpski teatar Kragujevac."
							+ "\n     Za sve predstave koje se prikazuju na tim scenama izracunati ukupan broj gledalaca. Prikazati samo one predstave ciji je ukupan broj gledalaca veci od 400."
							+ "\n     Za te predstave prikazati ukupan broj uloga na toj predstavi. Za scene na kojima se ne prikazuju predstave ispisati poruku. ");
			System.out.println(
					"\n4  - Prikazati id, nazive i prosecan broj gledalaca predstava koje imaju najveci prosecan broj gledalaca. Za te predstave prikazati listu uloga."
							+ "\n     Pored toga prikazati koliko ukupno ima muskih uloga i koliko ukupno ima zenskih uloga.");
			System.out.println(
					"\n5  - Prikazati prikazivanja predstava u narednom periodu na scenema na kojima je broj gledalaca veci od broja sedista na toj sceni. "
							+ "\n     Obrisati te torke. Raspodeliti sedista tako da se uvedu novi termini prikazivanja ove predstave na toj sceni. "
							+ "\n     Zauzeti pune scene onoliko puta koliko je potrebno, i napuniti poslednju scenu sa onoliko mesta koliko je ostalo. "
							+ "\n     Za novi datum uneti danasnji datum. Ispisati sva prikazivanja.");
			System.out.println(
					"\n6  - Za podelu napraviti CRUD operacije i kroz aplikaciju uneti nekoliko torki u tabelu Assignment. Nakon toga napraviti izvestaj "
							+ "\n     koji ce za svakog glumca prikazati ime glumca, naziv predstave, uloge u predstavi i honorar za koju je dobio "
							+ "\n     najveci honorar. Za glumce koji nemaju podelu ispisati umesto naziva predstave i uloge NEMA, a umesto honorara "
							+ "\n     staviti 0.");
			System.out.println(
					"\n7  - Prikazati sve uloge koje nisu podeljene. Zatim za te uloge prikazati spisak glumaca koji rade u pozoristu u kome se ta "
							+ "\n     predstava prikazuje u nekom narednom periodu, a koji nemaju nijedan angazman.");
			System.out.println(
					"\n8  - Prikazati za svakog glumca kojem je dodeljena neka uloga, id_ac, prezg, imeg, name_pl, name_pt i spisak drugih glumaca "
							+ "\n     kojima je dodeljena ista uloga. Pored toga neophodno je prikazati ukupan broj drugih glumaca kojima je "
							+ "\n     dodeljena ista uloga.");
			System.out.println(
					"\n9  - Prikazati za svakog glumca kom je dodeljena uloga, id_ac, prezg, imeg, kao i name_pl, name_pt prikazati spisak glumaca "
							+ "\n     za dodeljenu ulogu. Pored toga prikazati udeo tog glumca u ukupnom honoraru koji se izdvaja za dodeljenu "
							+ "\n     ulogu za sve njene glumce. Udeo izraziti u procentima zaokruzeno na dve decimale.");
			System.out.println(
					"\n10 - Prikazati maticni broj, ime, prezime i platu glumca i listu ostalih glumaca i njihovih honorara za tu ulogu. "
							+ "\n     Prikazati samo one glumce ciji je honorar za neku ulogu veci od prosecnog honorara za tu ulogu. ");
			System.out.println(
					"\n11 - Za svakog glumca prikazati koliki je ukupni honorar glumaca na svim njegovim predstavama. Uzeti u obzir samo"
							+ "\n     glumce koji glume i u predstavama koje se ne prikazuju u njihovom maticnom pozoristu.");
			System.out.println(
					"\n12 - Rebalansirati opterecenje glumcima starijim od 60 godina tako da u jednoj nedelji mogu da glume u najvise dve predstave."
							+ "\n     Sve uloge koje ostanu slobodne rebalansiranjem ovakvih glumaca, dodeliti drugim glumcima koji su bili prethodno u podeli"
							+ "\n     za tu ulogu. Ako ne postoje takvi glumci prikazati uloge koje su ostale neupraznjene. Ukoliko novom podelom postoje"
							+ "\n     prikazivanja predstava koje se daju u istom terminu sa istim glumcem u razlicitim ulogama, premestiti jedno od"
							+ "\n     prikazivanja u drugi termin.");
			System.out.println(
					"\n13 -  Omoguciti interaktivni unos novog pozorista za koji je potrebno uneti sve osnovne podatke, scene i mesto pozorista."
							+ "\n     Ukoliko mesto ne postoji dodati ga u bazu podataka, a ukoliko postoji samo povezati. Scene kreirati u bazi podataka.");

			System.out.println("\nX - Izlazak iz kompleksnih upita");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showSceneForTheatre();
				break;
			case "2":
				showReportingForShowingShows();
				break;
			case "3":
				showComplexQuery();
				break;
			case "4":
				showMostVisitedPlays();
				break;
			case "5":
				showShowingForDeleting();
				break;
			case "6":
				// TODO implementirati
				break;
			case "7":
				// TODO implementirati
				break;
			case "8":
				// TODO implementirati
				break;
			case "9":
				// TODO implementirati
				break;
			case "10":
				// TODO implementirati
				break;
			case "11":
				// TODO implementirati
				break;
			case "12":
				// TODO implementirati
				break;
			case "13":
				// TODO implementirati
				break;

			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	public void showSceneForTheatre() {

		System.out.println(Theatre.getFormattedHeader());

		try {
			List<ScenesForTheatreDTO> dtos = complexQueryService.getScenesForTheatres();
			if (!dtos.isEmpty()) {
				for (ScenesForTheatreDTO dto : dtos) {
					System.out.println(dto.getTheatre());
					System.out.println("\t\t------------------------------- SCENE -------------------------------");

					if (!dto.getScenes().isEmpty()) {
						System.out.println("\t\t" + Scene.getFormattedHeader());
						for (Scene scene : dto.getScenes()) {
							System.out.println("\t\t" + scene);
						}
					} else {
						System.out.println("\t\tNEMA SCENE");
					}
					System.out.println("\t\t---------------------------------------------------------------------");
					System.out.println();
				}
			} else {
				System.out.println("\t\tNEMA POZORISTA.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void showReportingForShowingShows() {

		try {
			for (ShowingsForPlayDTO dto: complexQueryService.getShowingsForPlays()) {
				String statsHeader =  String.format("%-27s %-29s %-17s", "UKUPAN BROJ GLEDALACA", "PROSECAN BROJ GLEDALACA", "BROJ PRIKAZIVANJA");
				System.out.println(Play.getFormattedHeader() + " " + statsHeader);
				System.out.println(dto.getPlay() + " " + dto.getStats());		
				
				System.out.println("\t\t---------------------------PRIKAZIVANJA----------------------------------");
				System.out.println("\t\t" + Showing.getFormattedHeader());
				for (Showing showing : dto.getShowings()) {
					System.out.println("\t\t" + showing);
				}
				System.out.println("\t\t-------------------------------------------------------------------------");
				System.out.println("\n\n");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showComplexQuery() {
		try {
			System.out.println(Scene.getFormattedHeader());
			for (PlaysForSceneDTO dto : complexQueryService.getDataForComplexQuery()) {
				System.out.println(dto.getScene());
				if (!dto.getShowingOfPlaysDTO().isEmpty()) {
					System.out.println("\t\t----------------------- PREDSTAVE -----------------------");

					for (PlayStatsDTO sopDTO : dto.getShowingOfPlaysDTO()) {
						System.out.println("\t\tID predstave: " + sopDTO.getPlayId());
						if (sopDTO.getTotalNumOfSpecs() > 400) {

							System.out
									.println("\t\tUkupan broj gledalaca za predstavu: " + sopDTO.getTotalNumOfSpecs());
							System.out.println("\t\tUkupan broj uloga za predstavu: " + sopDTO.getTotalNumOfRoles());
							System.out.println("\t\t--------------------------------------------------------");
							System.out.println();

						} else {
							System.out.println("\t\tUKUPAN BROJ GLEDALACA ZA OVU PREDSTAVU NIJE VECI OD 400!\n");
						}
					}
					System.out.println();
				} else {
					System.out.println("\t\tNEMA PREDSTAVA ZA PRIKAZIVANJE NA OVOJ SCENI!\n");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMostVisitedPlays() {
		try {
			for (PlayDTO p : complexQueryService.getMostVisitedPlays()) {
				System.out.println("IDPRED \t NAZIV\t PROSECAN_BR_GLEDALACA");
				System.out.println(p.toString());
				System.out.println("\t\t--------------------ULOGE------------------------");
				System.out.println("\t\t" + Role.getFormattedHeader());
				for (Role role : p.getRoles()) {
					System.out.println("\t\t" + role.toString());
				}
				System.out.println("\t\t-----------UKUPAN BROJ ZENSKIH ULOGA-------------");
				System.out.println("\t\t" + p.getNumOfFemaleRoles());
				System.out.println("\t\t-----------UKUPAN BROJ MUSKIH ULOGA--------------");
				System.out.println("\t\t" + p.getNumOfMaleRoles());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showShowingForDeleting() {
		try {
			List<ShowingDTO> forDeleting = complexQueryService.deleteShowings();
			if (forDeleting.isEmpty()) {
				System.out.println("Nema prikazivanja za brisanje.");
			} else {
				for (ShowingDTO pd : forDeleting) {
					System.out.println(ShowingDTO.getFormattedHeader());
					System.out.println(pd);					
				}
				System.out.println("--------------------Nakon brisanja:---------------------");
				System.out.println(Showing.getFormattedHeader());
				for (Showing p : showingService.getAll()) {
					System.out.println(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
