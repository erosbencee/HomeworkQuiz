package hu.bence.jatek.model.kerdes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Egy feladvány megoldását jelentő helyes választ reprezentáló osztály.
 * 
 * @author erosbencee
 *
 */

@Entity
@Table(name = "correct_answer")
public class CorrectAnswer {

	@Id
	@Column(name="c_answer_id")
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(name="c_answer_text")
	private String text ;

	@OneToOne(fetch=FetchType.LAZY)
	private QuestionText answerOfQuestion;

	
	/**
	 * Alapértelmezett konstruktor.
	 */
	public CorrectAnswer() {

	}
	
	/**
	 * Konstruktor egy helyes válasz létrehozására.
	 * 
	 * @param id	a helyes válasz egyedi azonosítója.
	 * @param text	a helyes válasz tartalma.
	 */
	
	public CorrectAnswer(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
	/**
	 * Visszaadja a helyes válaszhoz tartozó feladványt.
	 * 
	 * @return a helyes válasz feladványa.
	 */
	
	public QuestionText getAnswerOfQuestion() {
		return answerOfQuestion;
	}
	
	/**
	 * Beállítja a helyes válasz feladványát.
	 * 
	 * @param answerOfQuestion a helyes válasz feladványa.
	 */

	public void setAnswerOfQuestion(QuestionText answerOfQuestion) {
		this.answerOfQuestion = answerOfQuestion;
	}
	
	/**
	 * Visszaadja a helyes válasz egyedi azonosítóját.
	 * 
	 * @return a helyes válasz egyedi azonosítója.
	 */

	public int getId() {
		return id;
	}
	
	/**
	 * Beállítja a helyes válasz egyedi azonosítóját.
	 * 
	 * @param id a helyes válasz egyedi azonosítója.
	 */

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Visszaadja a helyes válasz tartalmát.
	 * 
	 * @return a helyes válasz tartalma.
	 */

	public String getText() {
		return text;
	}
	
	/**
	 * Beállítja a helyes válasz tartalmát.
	 * 
	 * @param text a helyes válasz tartalma.
	 */

	public void setText(String text) {
		this.text = text;
	}


	
	
}
