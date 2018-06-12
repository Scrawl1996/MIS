package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private String driver;
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	Statement stmt;
	ResultSet rs;

	public void initParam(String paramFile) throws Exception {
		// ʹ��Properties�������������ļ�
		Properties props = new Properties();
		String fullFileName = "E://git//ManageSystem//MIS//src//util//mysql.ini";
		// DBUtil.class.getResource(paramFile).getFile();
		props.load(new FileInputStream(fullFileName));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
	}

	public Connection getConnection() {

		// ��������
		try {
			initParam("mysql.ini");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void executeSql(String sql) throws Exception {
		try {
			// ��������
			Class.forName(driver);
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, user, pass);
			// ʹ��Connection������һ��Statment����
			stmt = conn.createStatement();
			// ִ��SQL,����booleanֵ��ʾ�Ƿ����ResultSet
			boolean hasResultSet = stmt.execute(sql);
			// ���ִ�к���ResultSet�����
			if (hasResultSet) {
				// ��ȡ�����
				rs = stmt.getResultSet();

				// ResultSetMetaData�����ڷ����������Ԫ���ݽӿ�
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				// �������ResultSet����
				while (rs.next()) {
					// �������ÿ�е�ֵ
					for (int i = 0; i < columnCount; i++) {
						System.out.print(rs.getString(i + 1) + "\t");
					}
					System.out.print("\n");
				}
			} else {
				System.out
						.println("��SQL���Ӱ��ļ�¼��" + stmt.getUpdateCount() + "��");
			}
		}
		// ʹ��finally�����ر����ݿ���Դ
		finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void dbClose(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void dbClose(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
