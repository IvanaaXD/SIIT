package rs.ac.uns.ftn.db.jdbc.report.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class ReportUtil {
	
	public static String baseResourcePath = "";
	
	
	/**
	 * 
	 * A method that executes an array of SQL commands;
	 * 
	 * NOTE: Each command is executed in a separate transaction!
	 * 
	 * @param commands
	 * @throws SQLException
	 */
	public static void executeCommands(String... commands) throws SQLException {
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				Statement statement = connection.createStatement()) {
			for (String command : commands) {
				statement.execute(command);
			}
		}
	}

	
	/**
	 * A method for extracting SQL commands from an SQL file
	 * 
	 * @param file
	 * @return an array of commands
	 * @throws IOException
	 */
	public static String[] getCommandsFromFile(String file) throws IOException {
		if(baseResourcePath == "") {
			System.out.println("Warning! baseResourcePath not set!");
		}
		
		byte[] fileBytes = Files.readAllBytes(Paths.get(baseResourcePath + file));
		String commandsStr = new String(fileBytes, StandardCharsets.UTF_8);

		String[] commands = commandsStr.split(";");

		String[] trimmedCommands = new String[commands.length];
		for (int i = 0; i < commands.length; i++) {
			trimmedCommands[i] = commands[i].trim();
		}

		return trimmedCommands;
	}
}
