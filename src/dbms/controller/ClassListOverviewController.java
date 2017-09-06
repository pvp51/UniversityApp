package dbms.controller;

import java.sql.SQLException;

import dbms.model.CourseList;
import dbms.model.Student;
import dbms.util.ClassListDOA;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClassListOverviewController {

	@FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Student, String> sidColumn;
	@FXML
	private TableColumn<Student, String> firstNameColumn;
	@FXML
	private TableColumn<Student, String> lastNameColumn;
	@FXML
	private TableColumn<Student, String> majorColumn;
	@FXML
	private TableColumn<Student, String> yearColumn;
	@FXML
	private Label courseCodeLabel;
	@FXML
	private Label courseNameLabel;
	@FXML
	private Label roomNumberLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label buildingCodeLabel;
	@FXML
	private Label instructorlastNameLabel;
	@FXML
	private Button searchButton;
	@FXML 
	private ChoiceBox<String> sectionChoiceBox;

	@SuppressWarnings("unused")
	private MainApp mainApp;
	private ClassListDOA classListDOA;


	public ClassListOverviewController() { 


	}

	public void setMainApp(MainApp mainApp) throws ClassNotFoundException, SQLException {
		this.mainApp = mainApp;
		sectionChoiceBox.setItems(mainApp.getSectionListData());		
	}

	@FXML
	private void initialize() {
		// Initialize the customer table
		sidColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("sid"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		majorColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("year"));

		// Auto resize columns
		studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Listen for selection changes
		studentTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
			@Override
			public void changed(ObservableValue<? extends Student> observable,
					Student oldValue, Student newValue) {
				//showStudentDetails(newValue);
			}
		});
	}
	
	

	@FXML
	private void handleSearch() throws ClassNotFoundException, SQLException{
		String selectedChoice = sectionChoiceBox.getSelectionModel().getSelectedItem();
		classListDOA = new ClassListDOA();
		CourseList courseList = classListDOA.searchCourseInfo(selectedChoice);

		studentTable.setItems(courseList.getStudent());
		
		courseCodeLabel.setText(courseList.getCourseCode());
		courseNameLabel.setText(courseList.getCourseName());
		timeLabel.setText(courseList.getTime());
		roomNumberLabel.setText(courseList.getRoomNo());
		buildingCodeLabel.setText(courseList.getBuildingCode());
		instructorlastNameLabel.setText(courseList.getInstructor());
		
		

	}

}

