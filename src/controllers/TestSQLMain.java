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
				System.out.print(tenSP + " - ");
				System.out.println(rs.getString("GiaBan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(rs, stmt, conn);
		}
	}

	public static void insertData() {
		Connection conn = ConnectionUtil.getConnection();
		Statement stmt = null;

		String sql = "INSERT INTO Sanpham VALUES ('SP08', 'Quần đỏ', 'Type002', '147000')";

		try {
			stmt = conn.createStatement();
			int results = stmt.executeUpdate(sql);

			System.out.println(results);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(stmt, conn);
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
			ConnectionUtil.close(pst, conn);
		}
	}

	public static void deleteData() {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pst = null;

		String sql = "DELETE FROM Sanpham WHERE MaSP = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "SP08");
			int results = pst.executeUpdate();

			System.out.println(results);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(pst, conn);
		}
	}

	public static void main(String[] args) {
//		insertData();
//		updateData();
		updatable();
		showData();
//		deleteData();

	}

	public static void updatable() {
		Connection conn = ConnectionUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from Sanpham");
			while (rs.next()) {
				String MaLoaiSP = rs.getString("MaLoaiSP");
				if ("Type001".equals(MaLoaiSP)) {
					rs.updateString("GiaBan", "100");
					rs.updateRow();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(rs, stmt, conn);
		}
	}

}
