package hu.bence.jatek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import hu.bence.jatek.Main;
import hu.bence.jatek.model.Player;



/**
 * A játék megkezdéséhez szükséges információk lekérdezését megvalósító osztály.
 * 
 * @author erosbencee
 *
 */

public class ServiceLoader {

	/**
	 * Az adatbázisban tárolt játékoslista.
	 */
	public static List<Player> thePlayers = new ArrayList<Player>();

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * Alapértelmezett konstruktor.
	 * 
	 */
	
	public ServiceLoader() {

	}

	/**
	 * Az adatbázishoz való kezdő kapcsolódást végrehajtó metódus, mely betölti a játékos adatait.
	 * Amennyiben nem sikerül létrehozni a kapcsolatot az adatbázissal, egy figyelmeztető üzenetben értesíti a felhasználót.
	 */
	
	public static void firstConnectToDatabase() {
		try{
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("QuizGameService");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
		
			QuizGameService gameService = new QuizGameService(entityManager);
		
			entityManager.getTransaction().begin();
		
			thePlayers = gameService.findAllPlayers();
			logger.info("The list of the players has downloaded from database.");
			
			entityManager.getTransaction().commit();
			entityManager.close();
			entityManagerFactory.close();
			
		} catch(Exception e){
			Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("::Homework Quiz:: Nincs internetkapcsolat");
            alert.setHeaderText("A kérdések letöltéséhez internetkapcsolat szükséges. Megpróbál újracsatlakozni?");
            alert.initOwner( Main.getPrimaryStage());

            logger.info("The connection to the database could not created.");
            
            ButtonType refresh = new ButtonType("Próbálja újra");
            ButtonType exit= new ButtonType("Kilépés");
          
          

            alert.getButtonTypes().setAll(refresh,exit);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == refresh){
            	 Main.getPrimaryStage().close();
            	 logger.info("Try again to connect to database.");
            	 Platform.runLater(() -> new Main().start(new Stage()));
            }
            if (result.get() == exit){
           	 Main.getPrimaryStage().close();
           	  
           }
          
		}
		
		//return  null;
	}

	/**
	 * Visszaadja a játékosok listáját.
	 * 
	 * @return a játékosok listája.
	 */
	public List<Player> getThePlayers() {
		return thePlayers;
	}

	/**
	 * Beállítja a játékosok listáját.
	 * 
	 * @param thePlayers a játékosokat tartalmazó lista.
	 */
	
	public static void setThePlayers(List<Player> thePlayers) {
		ServiceLoader.thePlayers = thePlayers;
	}
	
	
}

