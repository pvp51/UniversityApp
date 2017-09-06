package dbms.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbms.model.CourseList;
import dbms.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassListDOA {
	
	public ObservableList<String> searchAll() throws ClassNotFoundException, SQLException{
		String queryStmt = "SELECT Sec_No FROM section";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		ObservableList<String> courseList = getCoursesFromResultSet(rs);
		return courseList;
		
	}
	
	public CourseList searchCourseInfo(String sectionNum) throws ClassNotFoundException, SQLException{
		
		String queryStmt = "SELECT `course`.`C_Code`, student.SID, student.FirstName,`student`.`LastName`,"+
							"student.Major, student.Year, `course`.`C_Name`, section.Lec_Time,"+
							"section.Room_No, room.B_Code, staff.LastName FROM `section` LEFT JOIN `course`"+
							"ON `section`.`C_Code` = `course`.`C_Code` LEFT JOIN `studentsection`"+
							"ON `studentsection`.`Sec_No` = `section`.`Sec_No`LEFT JOIN `student`"+
							"ON `studentsection`.`SID` = `student`.`SID` left join room on section.Room_No = room.Room_No "+
							"left join faculty on section.Instructor_ID = faculty.Staff_ID left join staff on faculty.Staff_ID = staff.SSN "+
							"WHERE section.Sec_No='"+sectionNum+"'";
		ResultSet rs = DBUtil.dbExecuteQuery(queryStmt);
		CourseList courseList = getClassListFromResultSet(rs);
		return courseList;
		
	}

	private static ObservableList<String> getCoursesFromResultSet(ResultSet rs) throws SQLException
    {
		ObservableList<String> courseList = FXCollections.observableArrayList();
		String course = "";
        while(rs.next()) {
        	course = "";
        	course= rs.getString("Sec_No");
            courseList.add(course);
        }
        return courseList;
    }
	
	private static CourseList getClassListFromResultSet(ResultSet rs) throws SQLException
    {
		CourseList course = null;
		Student student = null;
		ObservableList<Student> studentList = FXCollections.observableArrayList();
        while(rs.next()) {
        	course = new CourseList();
        	student = new Student();
        	course.setCourseCode(rs.getString("C_Code"));
        	course.setCourseName(rs.getString("C_Name"));
        	course.setTime(rs.getString("Lec_Time"));
        	course.setRoomNo(rs.getString("Room_No"));
        	course.setBuildingCode(rs.getString("B_Code"));
        	course.setInstructor(rs.getString("LastName"));   
        	student.setSid(rs.getString("SID"));
        	student.setFirstName(rs.getString("FirstName"));
        	student.setLastName(rs.getString("LastName"));
        	student.setMajor(rs.getString("Major"));
        	student.setYear(rs.getString("Year"));
            studentList.add(student);
        }
        course.setStudent(studentList);
        return course;
    }
}
