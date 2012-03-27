


import org.junit.Before;
import org.junit.Test;

public class FinancialHistoryTest {
	private FinancialHistory initially0;
	private FinancialHistory initially100;
	
	private void assertEquals(double expected, double actual) {
		org.junit.Assert.assertEquals(expected, actual, 1e-10);
	}

	@Before
	public void setUp() throws Exception {
		initially0 = new FinancialHistory();
		initially100 = FinancialHistory.initialBalance(100);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, initially0.cashOnHand());
		assertEquals(0, initially0.totalReceivedFrom("any"));
		assertEquals(0, initially0.totalSpentFor("any"));
	}
	
	@Test
	public void testInitialBalance() {
		assertEquals(100.0, initially100.cashOnHand());
		assertEquals(0, initially0.totalReceivedFrom("any"));
		assertEquals(0, initially0.totalSpentFor("any"));
	}
	
	@Test 
	public void income() {
		initially0.receiveFrom("Company", 50);
		initially0.receiveFrom("Friend", 30);
		initially0.receiveFrom("Company", 10);
		initially100.receiveFrom("Other", 34);
		assertEquals(60, initially0.totalReceivedFrom("Company"));
		assertEquals(30, initially0.totalReceivedFrom("Friend"));
		assertEquals(0, initially0.totalReceivedFrom("Other Reason"));
		assertEquals(90, initially0.cashOnHand());
		assertEquals(90, initially0.totalReceived());
		assertEquals(90, initially0.totalReceivedFrom(""));
		assertEquals(34, initially100.totalReceivedFrom("Other"));
	}
	
	@Test 
	public void expenditure() {
		initially100.spendFor("Food", 50);
		initially100.spendFor("Drink", 30);
		initially100.spendFor("Food", 10);
		assertEquals(0, initially100.totalSpentFor("Other Reason"));
		assertEquals(30, initially100.totalSpentFor("Drink"));
		assertEquals(60, initially100.totalSpentFor("Food"));
		assertEquals(10, initially100.cashOnHand());
		assertEquals(90, initially100.totalSpent());
		assertEquals(90, initially100.totalSpentFor(""));
	}

	@Test
	public void mixed() {
		initially0.receiveFrom("friend", 1000);
		initially0.spendFor("Food", 450);
		assertEquals(550, initially0.cashOnHand());
		assertEquals(1000, initially0.totalReceivedFrom("friend"));
		assertEquals(450, initially0.totalSpentFor("Food"));
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void negativeIncome() {
    	initially0.receiveFrom("someone", -30);
    }
	
	@Test(expected=IllegalArgumentException.class)
    public void negativeCash() {
    	FinancialHistory.initialBalance(-10);
	}
	
	@Test(expected=IllegalArgumentException.class)
    public void negativeExpenditure() {
    	initially0.spendFor("something", -30);
	}

	@Test(expected=IllegalArgumentException.class)
    public void unbalanced() {
    	initially0.spendFor("something", 10);
	}
}
