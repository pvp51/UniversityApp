package dbms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil  {
	private static Connection conn;  
	private static String url = "jdbc:mysql://localhost:3306/university";  
	private static String user = "root";//Username of database  
	private static String pass = "";//Password of database

	public static Connection dbConnect() throws SQLException{  
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
		}catch(ClassNotFoundException cnfe){  
			System.err.println("Error: "+cnfe.getMessage());  
		}catch(InstantiationException ie){  
			System.err.println("Error: "+ie.getMessage());  
		}catch(IllegalAccessException iae){  
			System.err.println("Error: "+iae.getMessage());  
		}  
		conn = (Connection) DriverManager.getConnection(url,user,pass);  
		return conn;  
	}  

	public static Connection getConnection() throws SQLException, ClassNotFoundException{  
		if(conn !=null && !conn.isClosed())  
			return conn;  
		dbConnect();  
		return conn;  
	}

	//Close Connection
	public static void dbDisconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e){
			throw e;
		}
	}

	//DB Execute Query Operation
	public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
		//Declare statement, resultSet and CachedResultSet as null
		Statement stmt = null;
		ResultSet resultSet = null;
		CachedRowSet crs = null;

		try {
			//Connect to DB (Establish Connection)
			dbConnect();
			System.out.println("Select statement: " + queryStmt + "\n");

			//Create statement
			stmt = (Statement) conn.createStatement();

			//Execute select (query) operation
			resultSet = (ResultSet) stmt.executeQuery(queryStmt);			

			//CachedRowSet Implementation
			//In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
			//We are using CachedRowSet
			crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(resultSet);

		} catch (SQLException e) {
			System.out.println("Problem occurred at executeQuery operation : " + e);
			throw e;
		} finally {
			if (resultSet != null) {
				//Close resultSet
				resultSet.close();
			}
			if (stmt != null) {
				//Close Statement
				stmt.close();
			}
			//Close connection
			dbDisconnect();
		}
		//Return CachedRowSet
		return crs;
	}

	//DB Execute Update (For Update/Insert/Delete) Operation
	public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
		//Declare statement as null
		Statement stmt = null;
		try {
			//Connect to DB (Establish DB Connection)
			dbConnect();
			//Create Statement
			stmt = (Statement) conn.createStatement();
			//Run executeUpdate operation with given sql statement
			stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			System.out.println("Problem occurred at executeUpdate operation : " + e);
			throw e;
		} finally {
			if (stmt != null) {
				//Close statement
				stmt.close();
			}
			//Close connection
			dbDisconnect();
		}
	}
}
