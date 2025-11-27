package rs.ac.uns.ftn.db.jdbc.theatre.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil_HikariCP {
	private static HikariConfig hikariConfig = new HikariConfig();
	private static HikariDataSource hikariDS;

	static { // needs to be static
		hikariConfig.setJdbcUrl(ConnectionParams.LOCAL_CONNECTION_STRING);
		hikariConfig.setUsername(ConnectionParams.USERNAME);
		hikariConfig.setPassword(ConnectionParams.PASSWORD);
		hikariConfig.setMaximumPoolSize(20); // default - 10
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariDS = new HikariDataSource(hikariConfig);
	}

	private ConnectionUtil_HikariCP() {
	}

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
