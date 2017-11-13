import static org.junit.Assert.*;

import org.junit.Test;

import bank.domain.Account;

public class DBTests {

	@Test
	public void Assignment1Test() {
		Account account = new Account(111L);
		em.getTransaction().begin();
		em.persist(account);
		//TODO: verklaar en pas eventueel aan
		assertNull(account.getId());
		em.getTransaction().commit();
		System.out.println(“AccountId: “ + account.getId());
		//TODO: verklaar en pas eventueel aan
		assertTrue(account.getId() > 0L);

	}

}
