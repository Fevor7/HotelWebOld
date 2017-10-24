package by.htp.itacademy.hotel.dao.databaseconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * The object of this class creates and returns a connection to the database
 * using the standard tomcat pool coonection.
 * 
 * @author viktor
 *
 */
public class ConnectionDataBase {

	private static final ConnectionDataBase INSTANCE = new ConnectionDataBase();
	private DataSource ds;

	private ConnectionDataBase() {
		initConnection();
	}

	/**
	 * Initialize the connection to the database.
	 */
	private void initConnection() {
		PoolProperties pool = new PoolProperties();
		pool.setUrl(
				"jdbc:mysql://localhost/hotel?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=true&serverTimezone=Europe/Minsk");
		pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
		pool.setUsername("root");
		pool.setPassword("root");
		pool.setJmxEnabled(true);
		pool.setTestWhileIdle(false);
		pool.setTestOnBorrow(true);
		pool.setValidationQuery("SELECT 1");
		pool.setTestOnReturn(false);
		pool.setValidationInterval(30000);
		pool.setTimeBetweenEvictionRunsMillis(30000);
		pool.setMaxActive(100);
		pool.setInitialSize(10);
		pool.setMaxWait(10000);
		pool.setRemoveAbandonedTimeout(60);
		pool.setMinEvictableIdleTimeMillis(30000);
		pool.setMinIdle(10);
		pool.setLogAbandoned(true);
		pool.setRemoveAbandoned(true);
		pool.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		ds = new DataSource();
		ds.setPoolProperties(pool);
	}

	public static ConnectionDataBase getInstance() {
		return INSTANCE;
	}

	/**
	 * The method returns the connection object to the database.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * The method objects to the connection to the tomcat pool.
	 * 
	 * @param connection
	 * @param ps
	 */
	public void closeConnection(Connection connection, PreparedStatement ps) {
		try {
			connection.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method objects to a link to the connection and to the result object of
	 * the tomcat pool.
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
	public void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
