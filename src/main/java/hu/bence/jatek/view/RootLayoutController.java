package hu.bence.jatek.view;

import java.io.IOException;

import hu.bence.jatek.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A játék képernyőjének gombsorát kezelő osztály.
 * 
 * @author erosbencee
 *
 */
public class RootLayoutController {
	
	@FXML
	private void handleNewGame() throws IOException {
		Stage stage = new Stage();
		
		stage.setTitle("::Homework Quiz:: Új játék");
		stage.getIcons().add(new Image("/images/university.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/FXMLs/NewGameOverview.fxml"));
		Parent root =(AnchorPane) loader.load();
		stage.setScene(new Scene(root));
		
		stage.initOwner(Main.getPrimaryStage());
		stage.showAndWait();
	}
	
	@FXML
	private void handleMilestones() throws IOException{
		Stage stage = new Stage();
		
		stage.setTitle("::Homework Quiz:: Eredmények és mérföldkövek");
		stage.getIcons().add(new Image("/images/university.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/FXMLs/MilestonesOverview.fxml"));
		Parent root =(AnchorPane) loader.load();
		stage.setScene(new Scene(root));
		
		stage.initOwner(Main.getPrimaryStage());
		stage.showAndWait();
	}
	
	@FXML
	private void handleNevjegy() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("::Homework Quiz::");
		alert.setHeaderText("Author: Bence Dániel Erős\nWebsite: https://github.com/erosbencee");
		alert.setContentText("University of Debrecen, 2017");
		alert.initOwner( Main.getPrimaryStage());
		alert.showAndWait();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
	
}
