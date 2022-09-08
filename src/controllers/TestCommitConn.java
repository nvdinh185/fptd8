package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectionUtil;

public class TestCommitConn {
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static PreparedStatement pst = null;

	public static void main(String[] args) {
		testCommitData();
		showData();
	}

	public static void testCommitData() {

		String sqlUpdate = "UPDATE Sanpham SET TenSP = ? WHERE MaSP = ?";
		String sqlDelete = "DELETE FROM Sanpham WHERE MaSP = 'SP06'";
		String sqlInsert = "INSERT INTO Sanpham VALUES ('SP06', 'Mũ', 'Type002', '147000')";

		try {
			conn = ConnectionUtil.getConnection();
			conn.setAutoCommit(false);

			// update
			pst = conn.prepareStatement(sqlUpdate);
			pst.setString(1, "Áo ba lỗ");
			pst.setString(2, "SP07");
			pst.executeUpdate();

			// delete
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlDelete);

			// insert
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlInsert);

			conn.commit();
			System.out.println("OK");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(stmt, pst, conn);
		}
	}

	public static void showData() {
		conn = ConnectionUtil.getConnection();

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
			ConnectionUtil.close(rs, stmt, conn);
		}
	}

}
