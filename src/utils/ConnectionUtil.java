package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Shop;";
	private static final String USER_NAME = "dinh";
	private static final String PASSWORD = "111";

	/**
	 * Create connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
//			System.out.println("connect successfully!");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("connect failure!");
			ex.printStackTrace();
		}
		return conn;
	}

	/**
	 * Close connection
	 * 
	 * @param connect
	 */
	public static void close(Connection connect) {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.out.println("close connection failure!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close statement
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Close statement failure!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close prepare statement
	 * 
	 * @param prStmt
	 */
	public static void close(PreparedStatement prStmt) {
		if (prStmt != null) {
			try {
				prStmt.close();
			} catch (SQLException e) {
				System.out.println("Close prepare statement failure!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close result set
	 * 
	 * @param prStmt
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Close result set failure!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close result stmt, connect
	 * 
	 * @param stmt
	 * @param connect
	 */
	public static void close(Statement stmt, Connection connect) {
		close(stmt);
		close(connect);
	}

	/**
	 * Close result stmt, prStmt, connect
	 * 
	 * @param stmt
	 * @param prStmt
	 * @param connect
	 */
	public static void close(Statement stmt, PreparedStatement prStmt, Connection connect) {
		close(stmt);
		close(prStmt);
		close(connect);
	}

	/**
	 * Close result rs, stmt, connect
	 * 
	 * @param rs
	 * @param stmt
	 * @param connect
	 */
	public static void close(ResultSet rs, Statement stmt, Connection connect) {
		close(rs);
		close(stmt);
		close(connect);
	}

}
