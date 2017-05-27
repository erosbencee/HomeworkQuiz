package hu.bence.jatek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import hu.bence.jatek.model.kerdes.QuestionText;
import hu.bence.jatek.service.DBController;
import hu.bence.jatek.service.QuizGameService;
import hu.bence.jatek.service.ServiceLoader;

public class DatabaseTest {
	
	@Test
	public void playerIdTest() {
		
		ServiceLoader.firstConnectToDatabase();
		
		int findPlayer = ServiceLoader.thePlayers.get(1).getPlayerId();
		
		assertEquals(1, findPlayer);
	}
	
	@Test
	public void typeOfTheFirstQuestionTest() {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("QuizGameService");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		List<QuestionText> questionList = new ArrayList<QuestionText>();
		QuizGameService service = new QuizGameService(entityManager);
		
		DBController controller = new DBController();
		controller.open();
		controller.beginTransaction();
		questionList = service.listAllQuestions();
		controller.commitTransaction();
		controller.close();
		
		int typeOfFirstQuestion = questionList.get(0).getType();
		
		assertEquals(1, typeOfFirstQuestion);
	}
	
}
