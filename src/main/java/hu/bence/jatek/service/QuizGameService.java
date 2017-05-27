package hu.bence.jatek.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bence.jatek.Main;
import hu.bence.jatek.model.Player;
import hu.bence.jatek.model.kerdes.AnswerText;
import hu.bence.jatek.model.kerdes.CorrectAnswer;
import hu.bence.jatek.model.kerdes.QuestionText;

/**
 * Az adatbázisból való lekérdezéseket és az adatbázis módosítását biztosító osztály.
 * 
 * @author erosbencee
 *
 */

public class QuizGameService {

	private EntityManager entityManager;
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * Konstrktor egy entitás kezelő létrehozásához.
	 * 
	 * @param entityManager egy EntityManager objektum.
	 */
	
	public QuizGameService(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	/**
	 * Visszaadja az EntityManager objektumot.
	 * 
	 * @return EntityManager.
	 */
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Beállítja az EntityManager objektumot.
	 * 
	 * @param entityManager EntityManager.
	 */
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Létrehoz az adatbázisban egy új játékost.
	 * 
	 * @param id 	a játékos azonosítója.
	 * @param name	a játékos neve.
	 * @param score	a játékos pontszáma.
	 * @param historyScore a játékos történelem kategóriában szerzett pontjai.
	 * @param scienceScore a játékos természettudomány kategóriában szerzett pontjai.
	 * @return a lértehozott játékos.
	 */
	
	public Player createPlayer(int id, String name, int score, int historyScore, int scienceScore){
		Player player = new Player(id, name, score, historyScore, scienceScore);
		entityManager.persist(player);
		logger.info("A new player has created in the database.");
		
		return player; 
	}
	
	/**
	 * Visszaadja az adott azonosítóval rendelkező játékost.
	 * 
	 * @param id a jtátékos azonosítója.
	 * @return a keresett játékos.
	 */
	
	public Player findPlayerByid(int id){
		return entityManager.find(Player.class, id);
	}
	
	/**
	 * Visszaadja az adatbázisban szereplő összes játékost.
	 * 
	 * @return	az adatbázisból lekérdezett játékosokat tartalmazó lista.
	 */
	
	public List<Player> findAllPlayers(){
		TypedQuery<Player> q = entityManager.createQuery(
				"SELECT r FROM Player r", 
				Player.class);
		
		return q.getResultList();
	}
	
	/**
	 * Beállítja az adatbázisban a játékos aktuális pontszámát.
	 * 
	 * @param userId a módosítandó játékos azonosítója. 
	 * @param pont	a pontszám módosítandó értéke.
	 */
	
	public void updatePlayerScore(int userId,int pont){
		 entityManager
		     .createQuery("update Player set score= "+pont+" where id= " +userId)
		    .executeUpdate();
		logger.info("The scores of the player has updated in the database.");
	}
	
	/**
	 * Beállítja az adatbázisban a játékos történelemből szerzett aktuális pontszámát.
	 * 
	 * @param userId a módosítandó játékos azonosítója.
	 * @param pont a pontszám módosítandó értéke.
	 */
	
	public void updatePlayerHistoryScore(int userId,int pont){
		 entityManager
		     .createQuery("update Player set history_score= "+pont+" where id= " +userId)
		    .executeUpdate();
		 logger.info("The scores of the Player from history category has updated in the database.");
	}
	
	/**
	 * Beállítja az adatbázisban a játékos természettudományból szerzett aktuális pontszámát.
	 * 
	 * @param userId a módosítandó játékos azonosítója.
	 * @param pont a pontszám módosítandó értéke.
	 */
	
	public void updatePlayerScienceScore(int userId,int pont){
		 entityManager
		     .createQuery("update Player set science_score= "+pont+" where id= " +userId)
		    .executeUpdate();
		 logger.info("The scores of the Player from science category has updated in the database.");
	}
	
	/**
	 * Eltávolít egy játékost az adatbázisból.
	 * 
	 * @param id az eltávolítandó játékos azonosítója.
	 */
	
	public void removePlayer(int id){
		Player player = findPlayerByid(id);
		if (player != null) {
			entityManager.remove(player);
		}
		logger.info("A player has removed from the databse.");
	}
	
	/**
	 * Létrehoz egy új feladványszöveget az adatbázisban.
	 * 
	 * @param id		a feladvány azonosítója.
	 * @param type		a feladvány típusának megfelelő számérték, melyet a {@link hu.bence.jatek.model.kerdes.QuestionType} tesz egyértelművé.
	 * @param text		a feladvány tartalma.
	 * @param category	a feldavány kategóriáját jelző számérték, melyet a {@link hu.bence.jatek.model.kerdes.Categories} tesz egyértelművé.
	 * @return	a létrehozott feladvány.
	 */
	
	public QuestionText createNewQuestion(int id, int type, String text, int category){
		QuestionText kerdes = new QuestionText(id, type, text, category);
		entityManager.persist(kerdes);
		return kerdes; 
	}
	
	/**
	 * Létrehoz egy új válaszlehetőséget.
	 * 
	 * @param id	a válaszlehetőség azonosítója.
	 * @param text	a válaszlehetőség tartalma.
	 * @return	az új válaszlehetsőég.
	 */
	
	public AnswerText createNewAnswerText(int id, String text) {
		AnswerText answer = new AnswerText(id, text);
		entityManager.persist(answer);
		return answer;
	}
	
	/**
	 * Létrehoz egy új helyes választ.
	 * 
	 * @param id	a helyes válasz azonosítója.
	 * @param text	a helyes válasz tartalma.
	 * @return	az új helyes válasz.
	 */
	
	public CorrectAnswer createNewCorrectAnswer(int id, String text) {
		CorrectAnswer correct = new CorrectAnswer(id, text);
		entityManager.persist(correct);
		return correct;
	}
	
	/**
	 * Visszaadja a keresett azonosítóval rendelkező feladványt.
	 * 
	 * @param id	a keresett feladvány azonosítója.
	 * @return	az azonosítóhoz tartozó feladvány.
	 */
	
	public QuestionText findQuestionById(int id){
		return entityManager.find(QuestionText.class, id);
	}
	
	/**
	 * Visszaadja az azonosítóhoz tartozó válaszlehetőséget.
	 * 
	 * @param id	a keresett válaszlehetőség azonosítója.
	 * @return	az azonosítóhoz tartozó válaszlehetőség.
	 */
	
	public AnswerText findAnswerById(int id) {
		return entityManager.find(AnswerText.class, id);
	}
	
	/**
	 * Visszaadja a megadott azonosítóhoz tartozó helyes választ.
	 * 
	 * @param id	a keresett helyes válasz azonosítója.
	 * @return	az azonosítóhoz tartozó helyes válasz.
	 */
	
	public CorrectAnswer findCorrectAnswerById(int id) {
		return entityManager.find(CorrectAnswer.class, id);
	}
	
	/**
	 * Visszaadja a megadott azonosítóhoz tartozó játékost.
	 * 
	 * @param id	a keresett játékos azonosítója.
	 * @return	az azonosítóhoz tartozó játékos.
	 */
	
	public Player findPlayerById(int id) {
		return entityManager.find(Player.class, id);
	}
	
	/**
	 * Hozzáad egy megadott válaszlehetőséget egy megadott kérdéshez.
	 * 
	 * @param questionId	a kérdés azonosítója.
	 * @param answerId		a válaszlehetőség azonosítója.
	 * @return	a kérdéshez adott válaszlehetőség.
	 */
	
	public AnswerText setAnswerToQuestion(int questionId, int answerId) {
		AnswerText answer = findAnswerById(answerId);
		QuestionText question = entityManager.find(QuestionText.class, questionId);
		answer.setQuestion(question);
		question.getAnswerOptions().add(answer);
		
		return answer;
	}
	
	/**
	 * Beállítja a megadott helyes választ a megadott kérdéshez.
	 * 
	 * @param questionId	a kérdés azonosítója.
	 * @param correctId		a helyes válasz azonosítója.
	 * @return	a kérdéshez rendelt helyes válasz.
	 */
	
	public CorrectAnswer setTheCorrectAnswerOfQuestion(int questionId, int correctId) {
		CorrectAnswer correctAnswer = findCorrectAnswerById(correctId);
		QuestionText question = entityManager.find(QuestionText.class, questionId);
		correctAnswer.setAnswerOfQuestion(question);
		question.setCorrectAnswer(correctAnswer);
		
		return correctAnswer;
	}
	
	/**
	 * Lekérdezi az adatbázisban szereplő összes kérdést.
	 * 
	 * @return	a feladványokat tartalmazó lista.
	 */
	
	public List<QuestionText> listAllQuestions(){
		TypedQuery<QuestionText> q = entityManager.createQuery(
				"SELECT r FROM QuestionText r", 
				QuestionText.class);
		
		return q.getResultList();
	}
	
	/**
	 * Lekérdezi az adatbázisban szereplő összes válaszlehetőséget.
	 * 
	 * @return a válaszlehetőségeket tartalmazó lista.
	 */
	public List<AnswerText> listTheAnswers(){
		TypedQuery<AnswerText> q = entityManager.createQuery(
				"SELECT a FROM AnswerText a", 
				AnswerText.class);
		
		return q.getResultList();
	}
	
	/**
	 * Lekérdezi az adatbázisból az összes kérdést, amely a megadott {@code category} érték által jelzett kategóriába tartozik.
	 * 
	 * @param category	a lekérdezni kívánt kategóriát azonosító érték.
	 * @return	a megadott kategóriába tartozó kérdéseket tartalmazó lista.
	 */
	
	public List<QuestionText> listCategoryQuestions(int category){
		TypedQuery<QuestionText> q = entityManager.createQuery(
				"SELECT r FROM QuestionText r WHERE r.category = "+category, 
				QuestionText.class);
		return q.getResultList();
	}
	
	/**
	 * Eltávolít egy feladványt.
	 * 
	 * @param id	az eltávolítandó kérdés azonosítója.
	 */
	
	public void removeQuestion(int id){
		QuestionText kerdes = findQuestionById(id);
		if (kerdes != null) {
			entityManager.remove(kerdes);
		}
		logger.info("A question has removed from the database.");
	}
	
}