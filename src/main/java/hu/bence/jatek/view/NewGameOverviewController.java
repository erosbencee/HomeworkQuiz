package hu.bence.jatek.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bence.jatek.Main;
import hu.bence.jatek.service.DBController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A játék újrakezdését biztosító felület.
 * 
 * @author erosbencee
 *
 */
public class NewGameOverviewController {

	@FXML
	private Button startOfGame;
	
	@FXML
	private TextField newUserName;

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	@FXML
	private void handleStartOfNewGame() {
		
		logger.info("The player has restared the game. The points has setted up to zero.");
		
		DBController uploadNewPlayer = new DBController();
		if( !newUserName.getText().isEmpty()) {
		uploadNewPlayer.open();
		uploadNewPlayer.beginTransaction();
		uploadNewPlayer.getController().removePlayer(1);
		uploadNewPlayer.getController().createPlayer(1, newUserName.getText(), 0, 0, 0);
		uploadNewPlayer.commitTransaction();
		uploadNewPlayer.close();
		}

		Stage stage = (Stage) startOfGame.getScene().getWindow();
	    stage.close();
	    Main.getPrimaryStage().close();
	    Platform.runLater(() -> new Main().start(new Stage()));
		
	}
	
	@FXML
	private void handleNewUsername() {
		


	}
	
	
}
