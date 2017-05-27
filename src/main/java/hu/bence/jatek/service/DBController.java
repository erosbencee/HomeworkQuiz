package hu.bence.jatek.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Entitás objektumok kezelését megvalósító osztály.
 * 
 * @author erosbencee
 *
 */

public class DBController {

	private QuizGameService controller;
	
	/**
	 * Visszaad egy adatbáziskezelést biztosító objektumpéldányt.
	 * 
	 * @return az adatbázison végzett műveleteket biztosító @code QuizGameService objektum.
	 */
	
	public QuizGameService getController() {
		return controller;
	}

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;

	/**
	 * 
	 * EntityManager-t létrehozó függvény.
	 */
	
	public  void open(){
		
	    entityManagerFactory = Persistence.createEntityManagerFactory("QuizGameService");
		entityManager = entityManagerFactory.createEntityManager();
		
	    controller = new QuizGameService(entityManager);
	}
	
	/**
	 * 
	 * Egy tranzakció megkezdését végrehajtó függvény.
	 */
	
	public void beginTransaction(){
	entityManager.getTransaction().begin();
	}
	
	/**
	 * 
	 * Egy tranzakció befejezését végrehajtó függvény.
	 */
	
	public void commitTransaction(){
	entityManager.getTransaction().commit();
	}
	
	/**
	 * 
	 * EntityManager-t lezáró függvény.
	 */
	
	public void close(){
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
