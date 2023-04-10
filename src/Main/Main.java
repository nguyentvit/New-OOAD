package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import KetNoiCSDL.ConnectionDB;
import View.Appt;
import View.ChiTiet;
import View.KiemTra;
import View.ThongTin;
import View.View;

public class Main {

	public static void main(String[] args) throws SQLException, ParseException {
			View ui = new View();
			ui.ShowWindow();
		
		//View ui = new View();
		//ui.ShowWindow();
		 	//String sDate1="31-12-1998";  
		 	//String sDate2="2003-02-24 15";  
		   // Date date1=new SimpleDateFormat("yy-MM-dd HH").parse(sDate2);
		    
		    //System.out.println(sDate1+"\t"+date1);  
		

	}

}
