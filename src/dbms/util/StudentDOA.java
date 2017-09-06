package dbms.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import dbms.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDOA {
	
	public ObservableList<Student> searchAll() throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT * FROM student";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<Student> studentList = getStudentFromResultSet(rs);
		return studentList;
		
	}
	
	/*public void insert(Student student) throws ClassNotFoundException, SQLException{
		String queryStmt = "INSERT INTO student\n" +
                "(Name, City, State)\n" +
                "VALUES\n" +
                "('"+student.getName()+"','"+student.getCity()+"','"+student.getState() +"')";
		System.out.println(queryStmt);
		DBUtil.dbExecuteUpdate(queryStmt);		
	}
	
	public void update(Student student) throws ClassNotFoundException, SQLException{
		String queryStmt = "Update student\n" +
                " SET Name='" +student.getName() + "',City='" +student.getCity()+"', State='"+student.getState()+"'\n" +
                " Where SID="+student.getSid(); 
		System.out.println(queryStmt);
		//Update student SET Name='Parth', City='Somerset', State='NJ' Where SID=1
		DBUtil.dbExecuteUpdate(queryStmt);		
	}
	
	public void delete(Student student) throws ClassNotFoundException, SQLException{
		String queryStmt = "Delete FROM student\n" +
                " Where SID="+student.getSid(); 
		System.out.println(queryStmt);
		//DELETE FROM student WHERE SID=3
		DBUtil.dbExecuteUpdate(queryStmt);		
	}
	*/
	

	private static ObservableList<Student> getStudentFromResultSet(ResultSet rs) throws SQLException
    {
		ObservableList<Student> studentList = FXCollections.observableArrayList();
		Student student = null;
        while(rs.next()) {
        	student = new Student();
        	student.setSid(rs.getString("SID"));
        	student.setFirstName(rs.getString("FirstName"));
        	student.setLastName(rs.getString("LastName"));
        	student.setMajor(rs.getString("Major"));
        	student.setYear(rs.getString("Year"));
            studentList.add(student);
        }
        return studentList;
    }
}
