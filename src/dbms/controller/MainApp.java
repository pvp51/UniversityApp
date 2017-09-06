package dbms.controller;

import java.io.IOException;
import java.sql.SQLException;

import dbms.model.Student;
import dbms.util.ClassListDOA;
import dbms.util.StudentRegistrationDOA;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Student> studentData = FXCollections.observableArrayList();
	private ObservableList<String> sectionListData = FXCollections.observableArrayList();
	private ObservableList<String> courseIdListData = FXCollections.observableArrayList();
	private ClassListDOA sectionListDoa;
	private StudentRegistrationDOA courseListDoa;

	public MainApp() throws ClassNotFoundException, SQLException {
		sectionListDoa = new ClassListDOA();
		sectionListData = sectionListDoa.searchAll();
		
		courseListDoa = new StudentRegistrationDOA();
		courseIdListData = courseListDoa.searchAll();
	}
	public ObservableList<Student> getStudentData() throws ClassNotFoundException, SQLException {

		return studentData;
	}

	public ObservableList<String> getSectionListData() throws ClassNotFoundException, SQLException {

		return sectionListData;
	}

	public ObservableList<String> getCourseIdListData() {
		return courseIdListData;
	}
	
	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("CS631App");

		onIt(primaryStage);
		//showStudentOverview();

	}	

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	private void onIt(Stage primaryStage) {
		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/dbms/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
