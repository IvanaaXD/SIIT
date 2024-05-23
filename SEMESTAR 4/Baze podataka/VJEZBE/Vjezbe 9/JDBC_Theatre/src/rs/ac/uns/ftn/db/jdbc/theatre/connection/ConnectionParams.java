package rs.ac.uns.ftn.db.jdbc.theatre.connection;

public class ConnectionParams {
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	//TODO: ako koristite SUBP unutar VM, a Eclipse van VM, zamenite localhost u LOCAL_CONNECTION_STRING sa IP adresom VM
	public static final String LOCAL_CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String CLASSROOM_CONNECTION_STRING = "jdbc:oracle:thin:@192.168.0.102:1522:db2016";

	// TODO: promenite username i password
	public static final String USERNAME = "username"; 
	public static final String PASSWORD = "password";
}
