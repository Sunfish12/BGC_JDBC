package model.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BoardGamesImageBean;
import model.BoardGamesImageDAO;

public class BoardGamesImageDAO_JDBC implements BoardGamesImageDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BoardGames";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";

	// private DataSource dataSource;
	//
	// public MemberDAO_JDBC() {
	// try {
	// Context ctx = new InitialContext();
	// dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/xxx");
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// }

	private static final String SELECT_BY_ID = "select * from boardgamesimage where storeimageid = ?";

	@Override
	public BoardGamesImageBean findByPrimeKey(Integer storeImageId) {
		BoardGamesImageBean result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, storeImageId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new BoardGamesImageBean();
				result.setBoardGamesId(rs.getInt("boardgamesid"));
				result.setStoreImageId(rs.getInt("storeimageid"));
				result.setImgFileName(rs.getString("imgFileName"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimage");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String SELECT_ALL = "select * from boardgamesimage order by storeimageid";

	@Override
	public List<BoardGamesImageBean> getAll() {
		List<BoardGamesImageBean> result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			result = new ArrayList<BoardGamesImageBean>();
			while (rs.next()) {
				BoardGamesImageBean bean = new BoardGamesImageBean();
				bean.setBoardGamesId(rs.getInt("boardgamesid"));
				bean.setStoreImageId(rs.getInt("storeimageid"));
				bean.setImgFileName(rs.getString("imgFileName"));

				// 圖片另存
				File f = new File("imagesDB/image_boardgames.jpg");

				try {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(f));
					byte[] b = rs.getBytes("boardgameimage");
					if (b != null) {
						bos.write(b, 0, (int) b.length);
						bos.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public BoardGamesImageBean insert(BoardGamesImageBean bean, InputStream is,
			long size, String filename) {
		BoardGamesImageBean result = null;
		return result;
	}

	@Override
	public BoardGamesImageBean update(BoardGamesImageBean bean, InputStream is,
			long size, String filename) {
		BoardGamesImageBean result = null;
		return result;
	}

	@Override
	public boolean delete(Integer storeImageId) {

		return false;
	}

	public static void main(String[] args) {

	}
}
