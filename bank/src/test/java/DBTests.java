import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bank.dao.AccountDAO;
import bank.dao.AccountDAOJPAImpl;
import bank.domain.Account;
import util.DatabaseCleaner;

public class DBTests {
	EntityManagerFactory emf;
	EntityManager em;
	@Before
	public void SetUp() {
		emf = Persistence.createEntityManagerFactory("BankPU"); 
		em = emf.createEntityManager();
		DatabaseCleaner dbcleaner = new DatabaseCleaner(em);
		try {
			dbcleaner.clean();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		AccountDAO adao = new AccountDAOJPAImpl(em);
		Account account = new Account(111L);
		em.getTransaction().begin();
		em.persist(account);
		assertNull(account.getId());
		em.getTransaction().rollback();
		// TODO code om te testen dat table account geen records bevat. Hint: bestudeer/gebruik AccountDAOJPAImpl
		assertEquals(adao.count(), 0);
		// 1. 	assertNull(account.getId()) returned true omdat account.getId() null returned.
		// 		assertEquals(adao.count(), 0) returned true omdat na de rollback de database geen regel meer bevat.
		// 2. 	--DELETE FROM ACCOUNT
		// 		--INSERT INTO ACCOUNT (ACCOUNTNR, BALANCE, THRESHOLD) VALUES (?, ?, ?)
		//		--SELECT LAST_INSERT_ID()
		//		--DELETE FROM ACCOUNT
		//		--SELECT COUNT(ID) FROM ACCOUNT
		// 3.	Er is niks gebeurt in de database, omdat er een rollback is uitgevoerd.

	}
	@Test
	public void Assignment3Test() {
		Long expected = -100L;
		Account account = new Account(111L);
		account.setId(expected);
		em.getTransaction().begin();
		em.persist(account);
		//TODO: verklaar en pas eventueel aan
		assertEquals(expected, account.getId());
		em.flush();
		assertNotEquals(expected, account.getId());
		//TODO: verklaar en pas eventueel aan
		em.getTransaction().commit();
		//TODO: verklaar en pas eventueel aan
		
		// 1. 	assertEquals(expected, account.getId()) returned true, omdat account.ID gelijk is aan -100L.
		// 		assertNotEquals(expected, account.getId()) returned true, omdat de flush is uitgevoerd nu. De flush zorgt ervoor dat de het account
		// 		wordt gecommit.
		// 2. 	

	}
	@After
	public void closeEM() {
		em.close();
	}
	

}
