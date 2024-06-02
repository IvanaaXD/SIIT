package rs.ac.uns.ftn.db.jdbc.report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;
import rs.ac.uns.ftn.db.jdbc.report.dto.ExtendedComponent;
import rs.ac.uns.ftn.db.jdbc.report.dto.ExtendedComputer;
import rs.ac.uns.ftn.db.jdbc.report.util.ReportUtil;

public class Example_ComputerConfiguration {

	private static String tablesFile = "tables.sql";
	private static String dataFile = "data.sql";
	private static String dropTablesFile = "drop_tables.sql";

	public static void main(String[] args) {
		ReportUtil.baseResourcePath = "resources/computer_configuration/";

		createTables();

		populateData();

		generateReport();

		dropTables();

	}

	private static void createTables() {
		try {
			String[] commands = ReportUtil.getCommandsFromFile(tablesFile);
			ReportUtil.executeCommands(commands);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void populateData() {
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				Statement statement = connection.createStatement()) {
			connection.setAutoCommit(false);
			String[] commands = ReportUtil.getCommandsFromFile(dataFile);

			try {
				for (String command : commands) {
					statement.execute(command);
				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	private static void generateReport() {
		final String getComputers = "select idr, nazivr from racunar order by nazivr";
		final String getExtendedComponents = "select kp.nazivk, kp.tip, kp.proizvodjac, kp.cena*kf.komada ukupna_cena_za_komponentu from komponenta kp left outer join konfiguracija kf on kp.idk = kf.idk where kf.idr = ? order by ukupna_cena_za_komponentu desc";

		List<ExtendedComputer> extendedComputers = new ArrayList<>();

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement psComputers = connection.prepareStatement(getComputers);
				PreparedStatement psExtendedComponents = connection.prepareStatement(getExtendedComponents);) {

			ResultSet rsComputers = psComputers.executeQuery();

			if (!rsComputers.isBeforeFirst()) {
				System.out.println("No matching computer items!");
				return;
			}

			while (rsComputers.next()) {
				int computerId = rsComputers.getInt("idr");
				String computerName = rsComputers.getString("nazivr");
				
				ExtendedComputer extendedComputer = new ExtendedComputer(computerId, computerName);
				extendedComputers.add(extendedComputer);

				psExtendedComponents.setInt(1, computerId);
				ResultSet rsExtendedComponent = psExtendedComponents.executeQuery();

				if (!rsExtendedComponent.isBeforeFirst()) {
					continue;
				}

				int totalComputerPrice = 0;

				while (rsExtendedComponent.next()) {
					String componentName = rsExtendedComponent.getString(1);
					String componentType = rsExtendedComponent.getString(2);
					String componentManufacturer = rsExtendedComponent.getString(3);
					int totalPriceForComponent = rsExtendedComponent.getInt(4);
					extendedComputer.getExtendedComponents().add(new ExtendedComponent(componentName, componentType,
							componentManufacturer, totalPriceForComponent));

					totalComputerPrice += totalPriceForComponent;
				}

				extendedComputer.setTotalPrice(totalComputerPrice);
			}

			System.out.println(extendedComputers);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void dropTables() {
		try {
			String[] commands = ReportUtil.getCommandsFromFile(dropTablesFile);
			ReportUtil.executeCommands(commands);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
