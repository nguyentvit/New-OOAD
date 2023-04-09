package Main;

import java.sql.Connection;
import java.sql.SQLException;

import KetNoiCSDL.ConnectionDB;
import View.View;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection conn = ConnectionDB.getConnect();
		if(conn != null)
		{
			System.out.print("hello");
			View ui = new View();
			ui.ShowWindow();
		}

	}

}
