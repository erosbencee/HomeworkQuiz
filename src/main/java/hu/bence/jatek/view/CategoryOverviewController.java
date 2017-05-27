package hu.bence.jatek.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bence.jatek.Main;
import hu.bence.jatek.model.kerdes.Categories;
import hu.bence.jatek.model.kerdes.QuestionText;
import hu.bence.jatek.service.DBController;
import hu.bence.jatek.service.QuizGameService;
import hu.bence.jatek.service.ServiceLoader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * A játék kezdőképernyőjét megjelenítő osztály, amely elvégzi játékos által kiválaszott kategória kérdéseinek előkészítését.
 * 
 * @author erosbencee
 *
 */
public class CategoryOverviewController {

	static Stage stage;
	
    private static FXMLLoader selectedLoader = null;
    
    QuizGameService gameService;
    
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	private ServiceLoader categoryLoader = new ServiceLoader();
	
	private static List<QuestionText> actualQuestionsByCategory = new ArrayList<QuestionText>();
	
	@FXML
	private Text userName;
	@FXML
	private Text userScore;
	
	/**
	 * Alapértelmezett konstruktor.
	 */
	
	public CategoryOverviewController() {
		
	}
	
	/**
	 * Visszaadja a képernyőn megjelenő játékosnevet.
	 * 
	 * @return a játékos neve.
	 */

	public Text getUserName() {
		return userName;
	}
	
	/**
	 * Beállítja a képernyőn megjelenő játékosnevet.
	 * 
	 * @param userName a játékos neve.
	 */

	public void setUserName(Text userName) {
		this.userName = userName;
	}

	/**
	 * Visszaadja a képernyőn szereplő pontszámot.
	 * 
	 * @return a játékos pontszáma.
	 */
	
	public Text getUserScore() {
		return userScore;
	}
	
	/**
	 * Beállítja a képernyőn megjelenő pontszámot.
	 * 
	 * @param userScore a játékos pontszáma.
	 */

	public void setUserScore(Text userScore) {
		this.userScore = userScore;
	}

	List<QuestionText> getAllQuestions() {
		return actualQuestionsByCategory;
	}

	void setAllQuestions(List<QuestionText> allQuestions) {
		CategoryOverviewController.actualQuestionsByCategory = allQuestions;
	}
	
	@FXML
	private void initialize() {
		//gameService=ServiceLoader.firstConnectToDatabase();	
		ServiceLoader.firstConnectToDatabase();
		userName.setText(categoryLoader.getThePlayers().get(1).getUserName());
		IntegerStringConverter conv = new IntegerStringConverter();
		userScore.setText(conv.toString(categoryLoader.getThePlayers().get(1).getScore()));
	}

	private void openQuestion(String name, int category){

		DBController controller = new DBController ();
		controller.open();
		controller.beginTransaction();
		actualQuestionsByCategory = controller.getController().listCategoryQuestions(category);
	    controller.commitTransaction();
	    controller.close();
		
		int typeOfWindow = getAllQuestions().get(0).getType();

		    if(typeOfWindow == 1) {
		    	selectedLoader = new FXMLLoader(getClass().getResource("/FXMLs/QuestionOverview.fxml"));
		    } else if (typeOfWindow == 2) {
		    	selectedLoader = new FXMLLoader(getClass().getResource("/FXMLs/TrueOrFalseOverview.fxml"));
		    } else if (typeOfWindow == 3) {
		    	selectedLoader = new FXMLLoader(getClass().getResource("/FXMLs/ScanAnswerOverview.fxml"));
		    } else {
		    	logger.error("Can not find the correct view for the selected type of question!");
		    }

		    Parent root1;
           
			try {
				root1 = (Parent) selectedLoader.load();
			        stage = new Stage();
		            stage.initModality(Modality.WINDOW_MODAL);
		            stage.setTitle("::Homework Quiz:: " + name);
		            stage.getIcons().add(new Image("/images/university.png"));
		            stage.setScene(new Scene(root1));  
		            stage.initOwner(Main.getPrimaryStage());
		            stage.show();
		            
		            stage.focusedProperty().addListener(new ChangeListener<Boolean>()
		            {
		              @Override
		              public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1)
		              {
		            		IntegerStringConverter conv = new IntegerStringConverter();
		            		userScore.setText(conv.toString(categoryLoader.getThePlayers().get(1).getScore()));	
		              }
		            });
		            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		                public void handle(WindowEvent event) {
		                QuestionController savePoints = new QuestionController();
		                savePoints.updateGamePoint();
		                logger.info("The scores of the player is updating.");
		                
		                }
		            });   
		            
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	private void lockedCategory(String name, int minimum) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("::Homework Quiz:: " + name);
		alert.setHeaderText("A kategória eléréséhez minimum " + minimum +" pont összegyűjtése szükséges.");
		alert.initOwner( Main.getPrimaryStage());
		alert.showAndWait();
		
	}

	@FXML
	private void handleCategorySelector(Event event) {

		QuestionController.setCounting(0);
		
		
	    Button clickedBtn = (Button) event.getSource();
	    logger.info("Player has selected " + clickedBtn.getId() + " button."); 
	    
	    Categories category = Categories.getValueOf(clickedBtn.getId());

	    switch( category ){
	    case HISTORY_EASY:
	    	  openQuestion("Történelem (Könnyű)",category.getValue().getValue());
	    	break;

	    case HISTORY_MEDIUM:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 25) {
	    		lockedCategory("Történelem (Közepes)", 25);
	    	} else {

	    	  openQuestion("Történelem (Közepes)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case HISTORY_HARD:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 150) {
	    		lockedCategory("Történelem (Nehéz)", 150);
	    	} else {
	    	  openQuestion("Történelem (Nehéz)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case SCIENCE_EASY:
	    	  openQuestion("Természettudomány (Könnyű)",category.getValue().getValue());
	    	break;
	    	
	    case SCIENCE_MEDIUM:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 25) {
	    		lockedCategory("Természettudomány (Közepes)", 25);
	    	} else {
	    	  openQuestion("Természettudomány (Közepes)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case SCIENCE_HARD:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 150) {
	    		lockedCategory("Természettudomány (Nehéz)", 150);
	    	} else {
	    	  openQuestion("Természettudomány (Nehéz)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case LITERATURE:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 300) {
	    		lockedCategory("Irodalom", 300);
	    	} else {
	    	  openQuestion("Irodalom",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case MATH_MEDIUM:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 50) {
	    		lockedCategory("Matematika (Közepes)", 50);
	    	} else {
	    	  openQuestion("Matematika (Közepes)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case MATH_HARD:
	       	if(categoryLoader.getThePlayers().get(1).getScore() < 250) {
	    		lockedCategory("Matematika (Nehéz)", 250);
	    	} else {
	    	  openQuestion("Matematika (Nehéz)",category.getValue().getValue());
	    	}
	    	break;
	    	
	    case IT:
	    	if(categoryLoader.getThePlayers().get(1).getScore() < 300) {
	    		lockedCategory("Informatika", 300);
	    	} else {
	    	  openQuestion("Informatika",category.getValue().getValue());
	    	}
	    	break;
	    	
	    default:
	    	break;
		}
		
	}	
}