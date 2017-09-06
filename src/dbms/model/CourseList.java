package dbms.model;

import javafx.collections.ObservableList;

public class CourseList {
	private String courseCode;
	private String courseName;
	private String time;
	private String roomNo;
	private String buildingCode;	
	private String instructor;
	private ObservableList<Student> student;
	
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	

	public ObservableList<Student> getStudent() {
		return student;
	}
	public void setStudent(ObservableList<Student> student) {
		this.student = student;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

}
