package dbms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbms.model.CourseInfo;
import dbms.model.StudentRegistration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentRegistrationDOA {
	
	public void insert(StudentRegistration studentReg) throws ClassNotFoundException, SQLException{
		String queryStmt = "INSERT INTO studentsection\n" +
                "(SID, Sec_No)\n" +
                "VALUES\n" +
                "('"+studentReg.getSid()+"','"+studentReg.getSectionNumber()+"')";
		System.out.println(queryStmt);
		DBUtil.dbExecuteUpdate(queryStmt);		
	}
	
	public ObservableList<String> searchAll() throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT C_Code FROM course";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> courseList = getCoursesFromResultSet(rs, "C_Code");
		return courseList;
		
	}
	
	public ObservableList<String> searchStudent(String sid) throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT SID FROM student WHERE SID="+sid;
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> studentList = getCoursesFromResultSet(rs, "SID");
		return studentList;
		
	}
	
	public String searchStudentinSection(String sid) throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT count(studentsection.SID) FROM `studentsection` WHERE studentsection.SID="+sid;
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> studentList = getCoursesFromResultSet(rs, "count(studentsection.SID)");
		return studentList.get(0);
		
	}
	
	public String searchStudentandSection(String sid, String secNumber) throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT count(studentsection.SID) FROM `studentsection` WHERE studentsection.SID=\n"
							+sid+" and studentsection.Sec_No='"+secNumber+"'";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> studentList = getCoursesFromResultSet(rs, "count(studentsection.SID)");
		return studentList.get(0);
		
	}
	
	public ObservableList<String> searchSection(String courseCode) throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT Sec_No FROM section WHERE C_Code='"+courseCode+"'";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> sectionList = getCoursesFromResultSet(rs, "Sec_No");
		return sectionList;
		
	}
	
	private static ObservableList<String> getCoursesFromResultSet(ResultSet rs, String columnName) throws SQLException
    {
		ObservableList<String> resultList = FXCollections.observableArrayList();
		String rows = "";
        while(rs.next()) {
        	rows = "";
        	rows= rs.getString(columnName);
            resultList.add(rows);
        }
        return resultList;
    }
	
	public ObservableList<CourseInfo> searchCourses() throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT C_Code, C_Name FROM course";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<CourseInfo> courseList = getCoursesFromResultSet(rs);
		return courseList;
		
	}
	
	private static ObservableList<CourseInfo> getCoursesFromResultSet(ResultSet rs) throws SQLException
    {
		CourseInfo courseInfo;
		ObservableList<CourseInfo> resultList = FXCollections.observableArrayList();
        while(rs.next()) {
        	courseInfo = new CourseInfo();
        	courseInfo.setCourseCode(rs.getString("C_Code"));
        	courseInfo.setCourseName(rs.getString("C_Name"));
            resultList.add(courseInfo);
        }
        return resultList;
    }

}
