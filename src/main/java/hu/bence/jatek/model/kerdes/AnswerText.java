package hu.bence.jatek.model.kerdes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Egy kérdésre vonatkozó válaszlehetőséget reprezentáló osztály.
 * 
 * @author erosbencee
 *
 */

@Entity
@Table(name = "answer")
public class AnswerText {

	@Id
	@Column(name="answer_id")
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(name="answer_text")
	private String text ;

	@ManyToOne(fetch=FetchType.LAZY)
	private QuestionText question;
	
	/**
	 * Alapértelmezett konstruktor.
	 */
	
	public AnswerText() {
		
	}

	/**
	 * Konstruktor egy válaszlehetőség létrehozására.
	 * 
	 * @param id	a válaszlehetőség egyedi azonosítója.
	 * @param text	a válaszlehetőség tartalma.
	 */
	
	public AnswerText(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	/**
	 * Visszaadja azt a {@code QuestionText} objektumot, mellyel a válaszlehetőség kapcsolatba hozható.
	 * 
	 * @return a válaszlehetőséghez tartozó feladvány.
	 */
	
	public QuestionText getQuestion() {
		return question;
	}
	
	/**
	 * Beállítja a válaszlehetőséghez tartozó feladványt.
	 * 
	 * @param question a válaszlehetőséghez tartozó feladvány.
	 */

	public void setQuestion(QuestionText question) {
		this.question = question;
	}
	
	/**
	 * Visszaadja a válaszlehetőség azonosítóját.
	 * 
	 * @return a válaszlehetőség azonosítója.
	 */

	public int getId() {
		return id;
	}

	/**
	 * Beállítja a válaszlehetőség azonosítóját.
	 * 
	 * @param id a válaszlehetőség azonosítója.
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Visszaadja a válaszlehetőség tartalmát.
	 * 
	 * @return a válaszlehetsőég tartalma.
	 */
	
	public String getText() {
		return text;
	}

	/**
	 * Beállítja a válaszlehetőség tartalmát.
	 * 
	 * @param text a válaszlehetsőég tartalma.
	 */
	
	public void setText(String text) {
		this.text = text;
	}
		
}
