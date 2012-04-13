package bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Testet die Klasse Konto.
 * 
 * Die Tests sind nicht sehr vollstaendig!
 */
public class KontoTest {
	private Konto k1;
	static final double EPS = 1e-6;

	@Before
	public void setUp() throws Exception {
		k1 = new Konto("XBank : 234");
	}

	@Test
	public void neuesKonto() {
		assertEquals(0, k1.kontostand(), EPS);
	}

	@Test
	public void einzahlen() {
		k1.barEinzahlen(100);
		assertEquals(100, k1.kontostand(), EPS);
		k1.barEinzahlen(100);
		assertEquals(200, k1.kontostand(), EPS);
	}
	
	@Test
	public void ueberweisen() {
		k1.barEinzahlen(100);
		Konto k2 = new Konto("andere Bank");
		k1.ueberweisen(60, k2);
		assertEquals(40,k1.kontostand(), EPS);
		assertEquals(60,k2.kontostand(), EPS);
	}
	
	@Test
	public void abheben() {
		k1.barEinzahlen(100);
		k1.barAuszahlen(50);
		assertEquals(50, k1.kontostand(), EPS);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void kontoUeberzogen() {
		k1.barEinzahlen(4);
		k1.barAuszahlen(20);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void negativerBetragEin() {
		k1.barEinzahlen(-29);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void negativerBetragAus() {
		k1.barAuszahlen(-29);
	}
}
