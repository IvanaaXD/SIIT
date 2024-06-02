package rs.ac.uns.ftn.db.jdbc.theatre.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Hikari_Practice {

	private static HikariConfig hikariConfig = new HikariConfig();
	private static HikariDataSource hikariDS;
	
	static {
		hikariConfig.setJdbcUrl(ConnectionParams.LOCAL_CONNECTION_STRING);
		hikariConfig.setUsername(ConnectionParams.USERNAME);
		hikariConfig.setPassword(ConnectionParams.PASSWORD);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariDS = new HikariDataSource(hikariConfig);
	}
	
	private Hikari_Practice() {}
	
	public static Connection getConnection() throws SQLException {
		return hikariDS.getConnection();
	}
	
	public static void closeDataSource() {
		hikariDS.close();
	}
	
	@Override 
	protected void finalize() throws Throwable {
		hikariDS.close();
		super.finalize();
	}
}

