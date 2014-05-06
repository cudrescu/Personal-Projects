package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	
	/** The name of the MySQL account to use (or empty for anonymous) */
	private static final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private static final String password = "root";

	/** The name of the computer running MySQL */
	private static final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private static final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private static final String dbName = "test";
	
	/** Name of the init file for db */
	private static final String studentiInitFile = "Studenti.init";
	
	/**Connection to database */
	private static Connection conn = null;

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ serverName + ":" + portNumber + "/" + dbName,
				connectionProps);

		return conn;
	}
	
	public void connectToDatabase(){
		
		// Connect to MySQL
		try {
			conn = getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
			}
	}
	
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public void createTable(String tableName, int tableType){
		
		// Create a table
		/* 0 - Studenti
		 * 1 - Materii
		 * 2 - Profesori
		 * 3 - Link
		 */
		try {
			
			String createString = "";
			
			switch(tableType){
			
			case 0:
				 createString =
					"CREATE TABLE " + "Studenti" + " ( " +
					"ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "USERNAME varchar(20) NOT NULL, " +
			        "PASSWORD varchar(20) NOT NULL, " +
			        "PRIMARY KEY (ID))";		
				 executeUpdate(conn, createString);
				 System.out.println("Created table Studenti");
				 break;
				 
				 
			case 1:
				 createString =
					"CREATE TABLE " + "Materii" + " ( " +
					"ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "ID_TITULAR varchar(20) NOT NULL, " +
			        "PRIMARY KEY (ID))";		
				 executeUpdate(conn, createString);
				 System.out.println("Created table Materii");
				 break;
		 
			case 2:
				createString =
					"CREATE TABLE " + "Profesori" + " ( " +
					"ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "USERNAME varchar(20) NOT NULL, " +
			        "PASSWORD varchar(20) NOT NULL, " +
			        "PRIMARY KEY (ID))";		
				executeUpdate(conn, createString);
				System.out.println("Created table Studenti");
				break;
			
			case 3:
				createString =
					"CREATE TABLE " + "Link" + " ( " +
					"ID_Stud INTEGER NOT NULL, " +
			        "ID_Materie INTEGER NOT NULL, " +
			        "ID_Prf INTEGER NOT NULL, " +
			        "NOTA INTEGER NOT NULL )";		
			executeUpdate(conn, createString);
			System.out.println("Created table Studenti");
			break;
			}
			
	    } catch (SQLException e) {
	    		System.out.println("ERROR: Could not create the table" +tableType);
	    		e.printStackTrace();
			return;
		}
	}
	

	public void dropTable(String tableName){
		
		// Drop the table
		try {
		    String dropString = "DROP TABLE " + tableName;
			executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
	
	public void insertInTable(String insertString){
		
		System.out.println("Inserting records into the table...");
		try{
			executeUpdate(conn, insertString);
			System.out.println("Records inserted successfuly");
			
		}catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}	
	}
	
	public ResultSet selectfromTable(String selectString){
		
		System.out.println("Selecting records from the table...");
		try{
			 Statement stmt = null;  
			 stmt = conn.createStatement();
			 
			 ResultSet rs = stmt.executeQuery(selectString);
			 
			 System.out.println("Records selected successfuly");
			 return rs;
			 
		}catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return null;
		}	
	
	}
	
	public void initDB(){
		
		connectToDatabase();
		BufferedReader in = null;
		
		//init studenti
		try {
			in = new BufferedReader(new FileReader(studentiInitFile));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Could not find file");
			e.printStackTrace();
		}
		
		try {
			int dbSize = Integer.parseInt(in.readLine());
			
			for(int i = 0; i < dbSize; i++){
				
				String insertString = "INSERT INTO Studenti " + in.readLine();
				insertInTable(insertString);
				
			}
		
			in.close();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		//init Profesori 
		
		//init Materii
		
		//init Link
		
	}
	
}
