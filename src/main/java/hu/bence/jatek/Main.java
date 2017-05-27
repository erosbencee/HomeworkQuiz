package hu.bence.jatek;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A program main függvényét tartalmazó osztály.
 * 
 * @author erosbencee
 *
 */

public class Main extends Application {

	private static Stage primaryStage;
	private BorderPane rootLayout;
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * A start metódus elkészíti az induláskor megjelenítendő ablakot.
	 * 
	 */
	
	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("::Homework Quiz::");
		
		Main.primaryStage.getIcons().add(new Image("/images/university.png"));
		
		initRootLayout();
		
		showCategoryOverview();
		
		logger.info("The window of the application has created.");
	}
	
	/**
	 * Elkészíti az ablak menüsorát.
	 * 
	 */

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/FXMLs/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Elkészíti a játék indítóképernyőjét, ahol a játékos választhat a megadott 10 kategória közül.
	 * 
	 */
	
	public void showCategoryOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/FXMLs/CategoryOverview.fxml"));
			AnchorPane categoryOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(categoryOverview);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Visszaadja az ablakhoz tartozó Stage objektumot.
	 * 
	 * @return az ablakhoz tartozó Stage objektum.
	 */
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Beállítja az ablakhoz tartozó Stage objektumot.
	 * 
	 * @param primaryStage az ablakhoz beállítani kívánt Stage objektum.
	 */
	
	public void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}

	/**
	 * A program main függvénye.
	 * 
	 * @param args az estleges parancssori argomentumok.
	 */
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
