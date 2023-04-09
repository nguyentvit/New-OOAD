package KetNoiCSDL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	public class ConnectionDB {
			public static Connection getConnect() throws SQLException
			{
				Connection conn = null;
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					//String connecttionUrl = "jdbc:sqlserver://"+strServer+":1433;DatabaseName=" + strDatabase + '"sa","nguyenqb242"';
					conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QuanLySinhVien;user=sa;password=nguyenqb242;encrypt=false");
				}
				catch(SQLException e)
				{
					System.out.println("SQL Exception: " + e.toString());
				}
				catch(ClassNotFoundException cE)
				{
					System.out.println("Class Not Found Exception: "+cE.toString());
				}
				return conn;
			}
		
		}
