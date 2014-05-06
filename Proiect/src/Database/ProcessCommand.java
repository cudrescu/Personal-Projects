package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessCommand extends Thread{

	/* Database init */
	Database mainDB;
	
	public ProcessCommand(){
		
		this.mainDB = new Database();
		//mainDB.dropTable("Studenti");
	
	}
	
	public boolean checkStudentRegistration(String username, String password){
		
		String selectString = "SELECT username, password FROM Studenti";
		
		ResultSet rs = mainDB.selectfromTable(selectString);
		
		try {
			while(rs.next()){
			    //Retrieve by column name
			    String usr = rs.getString("USERNAME");
			    String pass = rs.getString("PASSWORD");
			    
			    if(usr.equals(username) && pass.equals(password))
			    	return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void run(){
		mainDB.initDB();
		
		while(true){
			System.out.println("DB up and running");
		}
	}
	
}
