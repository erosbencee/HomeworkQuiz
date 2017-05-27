package hu.bence.jatek.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bence.jatek.Main;
import hu.bence.jatek.model.kerdes.Categories;
import hu.bence.jatek.model.kerdes.QuestionType;
import hu.bence.jatek.service.DBController;
import hu.bence.jatek.service.ServiceLoader;

//import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A játékos által aktuálisan kiválasztott kategória kérdéseit, és a lehetséges válaszokat megjelenítő felületet kezelő osztály.
 * 
 * @author erosbencee
 *
 */
public class QuestionController {
	
	final int ANWSER_COUNT = 4;
	
	final int MAX_TEXT_LINE_LENGTH = 88;
	
	final int NUMBER_OF_QUESTIONS_IN_CATEGORY = 5;
	
	final int EASY = 5;
	final int MEDIUM = 10;
	final int HARD = 15;
	
	@FXML
	private Text placeOfText;
	
	@FXML 
	private Button closeButton;
	
	
	@FXML 
	private Button awnserABt;
	@FXML 
	private Button awnserBBt;
	@FXML 
	private Button awnserCBt;
	@FXML 
	private Button awnserDBt;
	
	@FXML 
	private Button trueBt;
	@FXML 
	private Button falseBt;
	
	@FXML
	private Text A;
	
	@FXML
	private Text B;
	
	@FXML
	private Text C;
	
	@FXML
	private Text D;
	
	@FXML
	private Text textForShowResult;
	@FXML
	private TextField textFieldForUserGuess;
	@FXML
	private Button resultBt; 
	
	private int typeOfNextWindow;
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static int counting = 0;
	
	private CategoryOverviewController actualQuestions = new CategoryOverviewController();
	
	private ServiceLoader initLoader = new ServiceLoader();
	
	private Button [] awnserArray;

	@FXML
	private void initialize() {

		
		String question =actualQuestions.getAllQuestions().get(counting).getText();
		
		placeOfText.setText(handleIfTheQuestionTextIsTooLong(question));
		if(actualQuestions.getAllQuestions().get(counting).getType() == 1) {
			
			awnserArray = new  Button [ANWSER_COUNT];
			awnserArray[0] = awnserABt;
			awnserArray[1] = awnserBBt;
			awnserArray[2] = awnserCBt;
			awnserArray[3] = awnserDBt;

			A.setText(actualQuestions.getAllQuestions().get(counting).getAnswerOptions().get(0).getText());
			B.setText(actualQuestions.getAllQuestions().get(counting).getAnswerOptions().get(1).getText());
			C.setText(actualQuestions.getAllQuestions().get(counting).getAnswerOptions().get(2).getText());
			D.setText(actualQuestions.getAllQuestions().get(counting).getAnswerOptions().get(3).getText());
			
			logger.info("The initialize of the new question has been successful.");
		}
		
	}
	
	/**
	 * Ellenőrzi, hogy a beolvasott kérdésszöveg megfelelően kifér-e a képernyőre.
	 * A túl hosszű szövegeket két sorra tördeli.
	 * 
	 * @param textOfQuestion a beolvasott szöveg.
	 * @return az átalakított, megfelelően megjeleníthető kérdés.
	 */
	
	public String handleIfTheQuestionTextIsTooLong(String textOfQuestion){
		
		if(textOfQuestion.length() > MAX_TEXT_LINE_LENGTH) {
			
			for (int i = MAX_TEXT_LINE_LENGTH; i >= 0; i--) {
				if(textOfQuestion.charAt(i) == ' ') {
					StringBuilder builder = new StringBuilder(textOfQuestion);
					builder.setCharAt(i, (char)13);
				  return builder.toString();
				}
			}			
		}
		
		return textOfQuestion;
	}
	
	/**
	 * Alapértelmezett konstruktor.
	 */
	
	public QuestionController() {
		
	}
	

	private FXMLLoader setTypeOfWindowForNextQuestion(FXMLLoader selectedNextLoader){
		typeOfNextWindow = actualQuestions.getAllQuestions().get(counting).getType();
		
		 if(typeOfNextWindow == 1) {
			 selectedNextLoader = new FXMLLoader(getClass().getResource("/FXMLs/QuestionOverview.fxml"));
		 } else if (typeOfNextWindow == 2) {
			 selectedNextLoader = new FXMLLoader(getClass().getResource("/FXMLs/TrueOrFalseOverview.fxml"));
		 } else if (typeOfNextWindow == 3) {
			 selectedNextLoader = new FXMLLoader(getClass().getResource("/FXMLs/ScanAnswerOverview.fxml"));
		 } else {
			 logger.error("Can not find the correct view for the selected type of question!");
		 }
		 
		 return selectedNextLoader;
	}
	

	private static int getCounting() {
		return counting;
	}

	static void setCounting(int counting) {
		QuestionController.counting = counting;
	}

	@FXML
	private void handleSendAnswer() {

		System.out.println("I have send my answer");
	}
	
	@FXML
	private void exitApplication(Event event) {
		event.consume();
	  // Platform.exit();
	}
	
	@FXML
	private void handleBackToMain() {

		updateGamePoint();
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
		
	@FXML
	private void handleCorrectAnswerHasClicked(Event event) {

		int type =actualQuestions.getAllQuestions().get(counting).getType();

		switch(QuestionType.valueOf(type)){
		
		case CHOOSER:
			chooserQuestion(event);
			break;
		case SCAN_ANSWER:
			 scanQuestion(event);
		     break;
		case TRUE_OR_FALSE:
			trueOrFalseQuestion(event);
		     break;
		}
	}
	
	@FXML
	void updateGamePoint(){
		int category = actualQuestions.getAllQuestions().get(counting).getCategory();
		
		DBController controller = new DBController ();
		controller.open();
		controller.beginTransaction();
		controller.getController().updatePlayerScore(initLoader.getThePlayers().get(1).getPlayerId(), initLoader.getThePlayers().get(1).getScore());
		if(category == Categories.HISTORY_EASY.getValue().getValue() || 
		   category == Categories.HISTORY_MEDIUM.getValue().getValue() ||
		   category == Categories.HISTORY_HARD.getValue().getValue()) {
				controller.getController().updatePlayerHistoryScore(initLoader.getThePlayers().get(1).getPlayerId(), 
					initLoader.getThePlayers().get(1).getHistoryScore());
		}
		if(category == Categories.SCIENCE_EASY.getValue().getValue() || 
		   category == Categories.SCIENCE_MEDIUM.getValue().getValue() ||
		   category == Categories.SCIENCE_HARD.getValue().getValue()) {
				controller.getController().updatePlayerScienceScore(initLoader.getThePlayers().get(1).getPlayerId(), 
					initLoader.getThePlayers().get(1).getScienceScore());
		}
		controller.commitTransaction();
	    controller.close();
	}
	
	private void addGamePoint(int point){
		int category = actualQuestions.getAllQuestions().get(counting).getCategory();
		
		initLoader.getThePlayers().get(1).setScore(initLoader.getThePlayers().get(1).getScore() +  point);
		
		if(category == Categories.HISTORY_EASY.getValue().getValue() || 
		   category == Categories.HISTORY_MEDIUM.getValue().getValue() ||
		   category == Categories.HISTORY_HARD.getValue().getValue()) {
				initLoader.getThePlayers().get(1).setHistoryScore(initLoader.getThePlayers().get(1).getHistoryScore() + point);
		}
		if(category == Categories.SCIENCE_EASY.getValue().getValue() || 
		   category == Categories.SCIENCE_MEDIUM.getValue().getValue() ||
		   category == Categories.SCIENCE_HARD.getValue().getValue()) {
				initLoader.getThePlayers().get(1).setScienceScore(initLoader.getThePlayers().get(1).getScienceScore() + point);
				}
	}
	
	@FXML
	private void trueOrFalseQuestion(Event event){
		String correctAnswer = actualQuestions.getAllQuestions().get(counting).getCorrectAnswer().getText();
		Button bt =(Button)event.getSource();
		if(correctAnswer.equals(bt.getText().toLowerCase())){
			
	  
	     trueBt.setDisable(true);
	     falseBt.setDisable(true);
		
		 bt.setDisable(false);
		 bt.setStyle("-fx-border-color:green; -fx-background-color: green;");
		 
		 if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 1 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 2) {
			 addGamePoint(EASY);
		 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 3 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 5){
			 addGamePoint(MEDIUM);
		 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 6 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 10){
			 addGamePoint(HARD);
		 } 
		 logger.info("The player has answered correct!");	
		} else {
			
			  	trueBt.setStyle("-fx-border-color:green; -fx-background-color: green;");
			    trueBt.setDisable(true);
			    falseBt.setDisable(true);
				falseBt.setStyle("-fx-border-color:green; -fx-background-color: green;");
				bt.setDisable(false);
				bt.setStyle("-fx-border-color:red; -fx-background-color: red;");
				logger.info("The player has answered wrong.");
		}		
	}
	
	@FXML
	private void scanQuestion(Event event){
		
		String correctAnswer=actualQuestions.getAllQuestions().get(counting).getCorrectAnswer().getText();
		
		if(!textFieldForUserGuess.getText().isEmpty()){
			if(correctAnswer.equals(textFieldForUserGuess.getText().toLowerCase())){
				
			logger.info("The player has answered correct!");
			textForShowResult.setText("Helyes válasz!");
			textForShowResult.setFill(Color.GREEN);
			//addGamePoint(5);
			 if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 1 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 2) {
				 addGamePoint(EASY);
			 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 3 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 5){
				 addGamePoint(MEDIUM);
			 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 6 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 10){
				 addGamePoint(HARD);
			 } 
			
			} else {
				logger.info("The player has answered wrong.");
				textForShowResult.setText("Nem helyes válasz! A megoldás: "+ correctAnswer);
				textForShowResult.setFill(Color.RED);			
			}
			
			textFieldForUserGuess.setDisable(true);
		
		} else {
			logger.info("The player has pressed check button without write a guess in the textfield.");
			textForShowResult.setText("Először írd be a helyesnek gondolt választ!");
		}	
	}
	
	@FXML
	private void chooserQuestion(Event event){

		Button bt =(Button)event.getSource();
		
		if(hasThePlayerSelectedTheCorrectFromFourPossibleOptions(bt)) {
			 //addGamePoint(5);
			 if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 1 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 2) {
				 addGamePoint(EASY);
			 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 3 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 5){
				 addGamePoint(MEDIUM);
			 } else if(actualQuestions.getAllQuestions().get(counting).getCategory() >= 6 && actualQuestions.getAllQuestions().get(counting).getCategory() <= 10){
				 addGamePoint(HARD);
			 } 
		}
	}
	

	private boolean hasThePlayerSelectedTheCorrectFromFourPossibleOptions(Button userGuess){
		
		String correctAnswer = actualQuestions.getAllQuestions().get(counting).getCorrectAnswer().getText();
		
		Text textBt = (Text) userGuess.getGraphic();
		
		if(correctAnswer.equals(textBt.getText())){
			userGuess.setStyle("-fx-border-color:green; -fx-background-color: green;");
			
			for (Button button : awnserArray) {
				if(button != userGuess){
					button.setDisable(true);
				}
			}
			logger.info("The player has answered correct!");
			return true;
				
		} else {
			userGuess.setStyle("-fx-border-color:red; -fx-background-color: red;");
			for (Button button : awnserArray) {
				
				Text otherBtText =(Text) button.getGraphic();
				if(button != userGuess) {
					button.setDisable(true);
				}
				
				if(correctAnswer.equals(otherBtText.getText())) {		
					button.setStyle("-fx-border-color:green; -fx-background-color: green;");
				}
			}
			logger.info("The player has answered wrong.");
			return false;
		}	
	}
	
	
	@FXML
	private void handleShowTheNextQuestion(Event e) throws Exception {
		
		FXMLLoader settingNewLoader = new FXMLLoader();
		setCounting(getCounting() + 1);
		if(getCounting() < NUMBER_OF_QUESTIONS_IN_CATEGORY) {
				logger.info("The player has switched to the next question");
				Parent root2 = (Parent) setTypeOfWindowForNextQuestion(settingNewLoader).load();
				CategoryOverviewController.stage.setScene(new Scene(root2));
		} else {
			setCounting(getCounting() - 1);
			logger.error("The selected category does not contain more questions");
			Button b = (Button) e.getSource();
			b.setDisable(true);
		}
	}
}
