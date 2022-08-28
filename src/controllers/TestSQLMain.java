package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectionUtil;

public class TestSQLMain {

	public static void showData() {
		Connection conn = ConnectionUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Sanpham";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String tenSP = rs.getString("tenSP");
				System.out.println(tenSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(rs);
			ConnectionUtil.close(stmt);
			ConnectionUtil.close(conn);
		}
	}

	public static void insertData() {
		Connection conn = ConnectionUtil.getConnection();
		Statement stmt = null;

		String sql = "INSERT INTO Sanpham VALUES ('SP07', 'Mũ Bảo hiểm', 'Type002', '147000')";

		try {
			stmt = conn.createStatement();
			int results = stmt.executeUpdate(sql);

			System.out.println(results);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(stmt);
			ConnectionUtil.close(conn);
		}
	}

	public static void updateData() {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pst = null;

		String sql = "UPDATE Sanpham SET TenSP = ? WHERE MaSP = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "Áo ba lỗ");
			pst.setString(2, "SP06");
			int results = pst.executeUpdate();

			System.out.println(results);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(pst);
			ConnectionUtil.close(conn);
		}
	}

	public static void main(String[] args) {
		showData();
//		insertData();
//		updateData();
//		updatable();

	}

	public static void updatable() {
		Connection conn = ConnectionUtil.getConnection();
		// Creating a Statement object
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// Retrieving the data
			ResultSet rs = stmt.executeQuery("select * from Sanpham");
			// Printing the contents of the table
			System.out.println("Contents of the table: ");
			rs.beforeFirst();
			while (rs.next()) {
				System.out.print("MaSP: " + rs.getString("MaSP"));
				System.out.print(", TenSP: " + rs.getString("TenSP"));
				System.out.print(", MaLoaiSP: " + rs.getString("MaLoaiSP"));
				System.out.println(", GiaBan: " + rs.getString("GiaBan"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
