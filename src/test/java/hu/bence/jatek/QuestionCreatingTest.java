package hu.bence.jatek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hu.bence.jatek.model.kerdes.AnswerText;
import hu.bence.jatek.model.kerdes.Categories;
import hu.bence.jatek.model.kerdes.CorrectAnswer;
import hu.bence.jatek.model.kerdes.QuestionText;
import hu.bence.jatek.model.kerdes.QuestionType;
import hu.bence.jatek.view.QuestionController;

public class QuestionCreatingTest {

	
	
	@Test
	public void connectAnswerToQuestionTest() throws Exception {
		
		QuestionText question = new QuestionText(1, 2, "Ez a teszt sikeres lesz.", 10);
		CorrectAnswer answer = new CorrectAnswer(1, "igaz");
	
		question.setCorrectAnswer(answer);
		
		assertEquals("Can not connect to answer to question", question.getCorrectAnswer(), answer);
	}
	
	@Test
	public void addAnswerOptionsToQuestionTest() throws Exception {
		
		List<AnswerText> options = new ArrayList<AnswerText>();
		
		QuestionText question = new QuestionText(2, 1,"Melyik évben koronázták meg I. (Szent) István királyt?", 1);
		
		AnswerText a = new AnswerText(2, "990");
		options.add(a);
		
		AnswerText b = new AnswerText(3, "1000");
		options.add(b);
		
		AnswerText c = new AnswerText(4, "1190");
		options.add(c);
		
		AnswerText d = new AnswerText(5, "2000");
		options.add(d);
		
		question.setAnswerOptions(options);
	
		assertEquals("Can not connect answer options to question", question.getAnswerOptions(), options);
	}
	
	@Test
	public void findOptionTest() throws Exception {
		
		List<AnswerText> options = new ArrayList<AnswerText>();
		
		QuestionText question = new QuestionText(2, 1,"Melyik évben koronázták meg I. (Szent) István királyt?", 1);
		
		AnswerText a = new AnswerText(2, "990");
		options.add(a);
		
		AnswerText b = new AnswerText(3, "1000");
		options.add(b);
		
		AnswerText c = new AnswerText(4, "1190");
		options.add(c);
		
		AnswerText d = new AnswerText(5, "2000");
		options.add(d);
		
		question.setAnswerOptions(options);
	
		assertEquals("Can not connect answer options to question", question.getAnswerOptions().get(3).getText(), "2000");
	}
	
	@Test
	public void testShortLengthQuestion() throws Exception{
		
		QuestionController testController = new QuestionController();
		
		assertEquals("Mennyi 121 négyzetgyöke?", testController.handleIfTheQuestionTextIsTooLong("Mennyi 121 négyzetgyöke?"));
	}
	
	@Test
	public void testIfTheQuestionTextIsTooLong() throws Exception{
		
		QuestionController testController = new QuestionController();
		
		assertEquals("Kinek a művéből való az alábbi idézet: ''Ég a napmelegtõl a kopár szík sarja, Tikkadt\rszöcskenyájak legelésznek rajta;'' ?",
				testController.handleIfTheQuestionTextIsTooLong("Kinek a művéből való az alábbi idézet: ''Ég a napmelegtõl a kopár szík sarja, Tikkadt szöcskenyájak legelésznek rajta;'' ?"));
	}
	
	@Test
	public void typeTest() {
		QuestionText question = new QuestionText(2, 2,"Mátyás királyt 1450-ben koronázták királlyá.", 1);
		
		int type = question.getType();
	
		assertEquals(QuestionType.TRUE_OR_FALSE, QuestionType.valueOf(type));
		
	}

	@Test
	public void categoryTest() {
		
		QuestionText question = new QuestionText(2, 1,"Melyik a legkisebb kétjegyű prímszám?", 8);
		
		int category = question.getCategory();
		
		assertEquals(Categories.MATH_HARD, Categories.getValueOf(category));
	}


}
