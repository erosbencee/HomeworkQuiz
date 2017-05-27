package hu.bence.jatek.model.kerdes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A játék feladványát reprezentáló osztály, melynek példánya lehet kérdés vagy egy kijelentő mondat.
 * 
 * @author eorsbencee
 *
 */

@Entity
@Table(name = "question")
public class QuestionText implements Serializable{


	@Id
	@Column(name="question_id")
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(name="type")
	private int type;
	
	@NotNull
	@NotEmpty
	@Column(name="text",nullable = false)
	private String text ;
	
	@NotNull
	@NotEmpty
	@Column(name="category",nullable = false)
	private int category;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="question", fetch=FetchType.EAGER)
	private List<AnswerText> answerOptions = new ArrayList<AnswerText>();
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="answerOfQuestion", fetch=FetchType.EAGER)
	private CorrectAnswer correctAnswer;
	
	
	/**
	 * Alapértelmezett konstruktor.
	 */
	public QuestionText() {

	}
	
	/**
	 * Egy feladvány szöveget létrehozó konstruktor.
	 * 
	 *@param id			a szöveg azonosítója.
	 *@param type		a szöveg típusa. A típus értékétől függően a feladvány lehet
	 *<ul>
	 *	<li>Egy kérdés, melyhez 4 válaszlehetőség tartozik, melyeket a(z) {@code answerOptions} elem ad meg.</li>
	 *	<li>Egy kijelentő mondat, melynek jelentése igaz vagy hamis.</li>
	 *	<li>Egy kérdés, melyhez nem tartozik kiválasztható válaszlehetőség. Ekkor a(z) {@code Player} maga adja meg a helyesnek vélt választ.</li>
	 *</ul>
	 *
	 *@param text		a szöveg tartalma.
	 *@param category	a szöveg témaköre.
	 */

	public QuestionText(int id, int type, String text, int category) {
		super();
		this.id = id;
		this.type = type;
		this.text = text;
		this.category = category;
	}

	/**
	 * Visszaadja a feladvány lehetséges megoldásait tartalmazó listát, melyből a {@code Player} kiválaszthatja a helyeset.
	 * 
	 * @return a feladvány lehetséges megoldásai.
	 */
	
	public List<AnswerText> getAnswerOptions() {
		return answerOptions;
	}
	
	/**
	 * Beállítja a feladvány lehetséges megoldásait tartalmazól listát, melyből a {@code Player} kiválaszthatja a helyeset.
	 * 
	 * @param answerOptions a feladvány lehetséges megoldásait tartalmazó lista.
	 */

	public void setAnswerOptions(List<AnswerText> answerOptions) {
		this.answerOptions = answerOptions;
	}
	
	/**
	 * Visszaadja a feladvány helyes megoldását.
	 * 
	 * @return a feladvány helyes megoldása.
	 */
	
	public CorrectAnswer getCorrectAnswer() {
		return correctAnswer;
	}
	
	/**
	 * Beállítja a feladvány helyes megoldását.
	 * 
	 * @param correctAnswer a feladvány helyes megoldása.
	 */

	public void setCorrectAnswer(CorrectAnswer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	/**
	 * Visszaadja a feladvány egyedi azonosítóját.
	 * 
	 * @return a feladvány egyedi azonosítója.
	 */

	public final int getId() {
		return id;
	}
	
	/**
	 * Beállítja a feladvány egyedi azonosítóját.
	 * 
	 * @param id a feladvány egyedi azonosítója.
	 */

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Visszaadja a feladvány típusát.
	 * 
	 * @return a feladvány típusa.
	 */

	public int getType() {
		return type;
	}

	/**
	 * Beállítja a feladvány típusát.
	 * 
	 * @param type a feladvány típusa.
	 */
	
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Visszaadja a feladvány szövegét.
	 * 
	 * @return a feladvány szövege.
	 */

	public final String getText() {
		return text;
	}
	
	/**
	 * Beállítja a feladvány szövegét.
	 * 
	 * @param text a feladvány szövege.
	 */
	
	public void setText(String text) {
		this.text=text;
	}
		
	/**
	 * Visszaadja a feladvány témakörét jelző értéket.
	 * 
	 * @return a feladvány témakörét jelző érték.
	 */
	
	public final int getCategory() {
		return category;
	}
	
	/**
	 * Beállítja a feladvány témakörét.
	 * 
	 * @param category a feladvány témakörét jelző érték, melyet a {@link hu.bence.jatek.model.kerdes.Categories} azonosít.
	 */

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kerdes [id=");
		builder.append(id);
		builder.append(" , Text= ");
		builder.append(text);
		builder.append(" ,Category= ");
		builder.append(category);
		builder.append(" ,Type: ");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}	
}