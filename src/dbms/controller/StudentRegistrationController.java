package dbms.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbms.model.CourseInfo;
import dbms.model.Student;
import dbms.model.StudentRegistration;
import dbms.util.StudentRegistrationDOA;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentRegistrationController {
	@SuppressWarnings("unused")
	private MainApp mainApp;
	@FXML
	private TextField sidField;
	@FXML 
	private ChoiceBox<String> courseChoiceBox;
	@FXML 
	private ChoiceBox<String> sectionChoiceBox;
	@FXML
	private TableView<CourseInfo> courseTable;
	@FXML
	private TableColumn<Student, String> courseCodeColumn;
	@FXML
	private TableColumn<Student, String> courseNameColumn;
	
	private StudentRegistrationDOA studentRegDoa;
	private  ObservableList<String> secNoListData = FXCollections.observableArrayList();

	public void setMainApp(MainApp mainApp) throws ClassNotFoundException, SQLException {
		this.mainApp = mainApp;
		courseChoiceBox.setItems(mainApp.getCourseIdListData());			
	}

	public StudentRegistrationController(){		
	}

	@FXML
	private void initialize() throws ClassNotFoundException, SQLException {
		studentRegDoa = new StudentRegistrationDOA();
		courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courseCode"));
		courseNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("courseName"));
		
		// Auto resize columns
		courseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//CourseList courseList = studentRegDoa.searchCourses();

		courseTable.setItems(studentRegDoa.searchCourses());
	

		courseChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String oldValue, String newValue) {
				System.out.println(ov);
				System.out.println(oldValue);
				System.out.println(newValue);
				try {
					if(newValue != null || newValue != ""){
						studentRegDoa = new StudentRegistrationDOA();
						secNoListData = studentRegDoa.searchSection(newValue);
						sectionChoiceBox.setItems(secNoListData);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}    
		});

		sectionChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@SuppressWarnings("rawtypes")
			@Override public void changed(ObservableValue ov, String oldValue, String newValue) {
				System.out.println(ov);
				System.out.println(oldValue);
				System.out.println(newValue);
			}    
		});

	}

	@FXML
	private void handleRegister() throws ClassNotFoundException, SQLException{

		if(isInputValid()){
			StudentRegistration studentReg = new StudentRegistration();
			studentReg.setSid(sidField.getText());
			studentReg.setSectionNumber(sectionChoiceBox.getSelectionModel().getSelectedItem());

			studentRegDoa = new StudentRegistrationDOA();
			studentRegDoa.insert(studentReg);		

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Class Registered");
			alert.setTitle("Student Class Registration");
			alert.setHeaderText("You've successfully enrolled in this class");
			alert.show();

			clearData();	

		}

	}

	private void clearData() {
		sidField.clear();	
	}

	private boolean isInputValid() throws ClassNotFoundException, SQLException {
		String errorMessage = "";


		if (sidField.getText() == null || sidField.getText().length() == 0) {
			errorMessage += "No valid sid!\n"; 
		}
		else{
			studentRegDoa = new StudentRegistrationDOA();
			List<String> studentList = new ArrayList<>();
			studentList = studentRegDoa.searchStudent(sidField.getText());
			String count="";
			count = studentRegDoa.searchStudentinSection(sidField.getText());
			if(studentList == null || studentList.size()==0){
				System.out.println("SID: "+studentList);
				errorMessage += "No valid sid!\n";
			}
			if(Integer.parseInt(count) == 3){
				errorMessage += "Cannot register more than 3 classes!\n";
			}
			if(sectionChoiceBox.getItems()!= null && sidField.getText()!=null){
				count = studentRegDoa.searchStudentandSection(sidField.getText(),sectionChoiceBox.getSelectionModel().getSelectedItem());
				if(Integer.parseInt(count) == 1){
					errorMessage += "Class already Registered!\n";
				}
			}

		}
		if (courseChoiceBox.getItems() == null || courseChoiceBox.getItems().size() == 0) {
			errorMessage += "No valid course code!\n"; 
		}
		if (sectionChoiceBox.getItems() == null || sectionChoiceBox.getItems().size() == 0) {
			errorMessage += "No valid sectionChoiceBox!\n"; 
		} 

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(errorMessage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.show();
			clearData();
			return false;
		}
	}

}
