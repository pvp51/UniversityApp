package dbms.model;

import javafx.collections.ObservableList;

public class Course {
	
	private ObservableList<CourseInfo> courseInfo;

	public ObservableList<CourseInfo> getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(ObservableList<CourseInfo> courseInfo) {
		this.courseInfo = courseInfo;
	}
	
}
