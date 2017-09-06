package dbms.controller;
import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    private BorderPane rootLayout;
    private Stage primaryStage;
   
    @FXML
    private SplitMenuButton splitButton;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    
    public MainApp getMainApp() {
		return mainApp;
	}

	@FXML
    private void handleClassList(ActionEvent  event) throws ClassNotFoundException, SQLException, IOException {    
       
        primaryStage = (Stage) splitButton.getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/dbms/view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        FXMLLoader classloader = new FXMLLoader(MainApp.class.getResource("/dbms/view/ClassListOverview.fxml"));
        AnchorPane overviewPage = (AnchorPane) classloader.load();
        rootLayout.setCenter(overviewPage);
        
        ClassListOverviewController controller = classloader.getController();
        controller.setMainApp(getMainApp());
   
    }
	
	@FXML
    private void handleStudentRegistration(ActionEvent  event) throws ClassNotFoundException, SQLException, IOException {    
       
        primaryStage = (Stage) splitButton.getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/dbms/view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        FXMLLoader classloader = new FXMLLoader(MainApp.class.getResource("/dbms/view/StudentRegistration.fxml"));
        AnchorPane overviewPage = (AnchorPane) classloader.load();
        rootLayout.setCenter(overviewPage);
        
        StudentRegistrationController controller = classloader.getController();
        controller.setMainApp(getMainApp());
   
    }



    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("Author: Parth Patel");
    	alert.setTitle("About");
    	alert.setHeaderText("CS631App");
    	alert.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
