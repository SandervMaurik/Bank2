import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import bank.domain.Account;

public class DBTests {
	EntityManagerFactory emf;
	EntityManager em;
	@Before
	public void SetUp() {
		emf = Persistence.createEntityManagerFactory("BankPU"); 
		em = emf.createEntityManager();
	}
	
	@Test
	public void Assignment1Test() {
		Account account = new Account(111L);
		em.getTransaction().begin();
		em.persist(account);
		//TODO: verklaar en pas eventueel aan
		assertNull(account.getId());
		em.getTransaction().commit();
		System.out.println("AccountId: " + account.getId());
		//TODO: verklaar en pas eventueel aan
		assertTrue(account.getId() > 0L);
		// 1. 	assertNull(account.getId()) geeft true omdat getId() null returned.
		// 		assertTrue(account.getId() > 0L) returned true omdat account.getId() hier nu 1 returned.
		// 2. 	--CREATE TABLE ACCOUNT (ID BIGINT AUTO_INCREMENT NOT NULL, ACCOUNTNR BIGINT UNIQUE, BALANCE BIGINT, THRESHOLD BIGINT, PRIMARY KEY (ID))
		//		--INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)
		//		--SELECT LAST_INSERT_ID()
		// 3.	Er is een tabel aangemaakt: Account. Verder is er een regel toegevoegd.

	}
	@Test
	public void Assignment2Test() {
		Account account = new Account(111L);
		em.getTransaction().begin();
		em.persist(account);
		assertNull(account.getId());
		em.getTransaction().rollback();
		// TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl

	}
	

}
