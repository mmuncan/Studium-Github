package bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankTest {
    private Bank b1;
    private Bank b2;
    private static final double EPS = 1e-6;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Bank("b1");
        b2 = new Bank("b2");
    }
    
    @Test
    public void testToString() {
        assertEquals("b1", b1.toString());
        assertEquals("b2", b2.toString());
    }

    @Test
    public void testNeuesKonto() {
        int k1 = b1.neuesKonto();
        assertEquals(0., b1.kontostand(k1), EPS);
    }

    @Test
    public void testBarEinzahlen() {
        int k1 = b1.neuesKonto();
        b1.barEinzahlen(k1, 120);
        assertEquals(120., b1.kontostand(k1), EPS);
    }

    @Test
    public void testBarAuszahlen() {
        int k1 = b1.neuesKonto();
        b1.barEinzahlen(k1, 120);
        b1.barAuszahlen(k1, 100);
        assertEquals(20., b1.kontostand(k1), EPS);
    }

    @Test
    public void testUeberweisen() {
        int k1 = b1.neuesKonto();
        int k2 = b1.neuesKonto();
        int k3 = b2.neuesKonto();
        b1.barEinzahlen(k1, 120);
        b1.ueberweisen(k1, b1, k2, 40);
        b1.ueberweisen(k2, b2, k3, 20);
        b2.ueberweisen(k3, b1, k2, 10);
        assertEquals(80., b1.kontostand(k1), EPS);
        assertEquals(30., b1.kontostand(k2), EPS);
        assertEquals(10., b2.kontostand(k3), EPS);
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void testFalscheKontoNummer() {
        int k1 = b1.neuesKonto();
        b1.barEinzahlen(k1, 100);
        int k2 = b2.neuesKonto();
        b1.ueberweisen(k1+1, b2, k2, 20);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void falschesZiel() {
        int k1 = b1.neuesKonto();
        b1.barEinzahlen(k1, 100);
        int k2 = b2.neuesKonto();
        b1.ueberweisen(k1, b2, k2+1, 20);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativerBetrag() {
        int k1 = b1.neuesKonto();
        int k2 = b2.neuesKonto();
        b1.barEinzahlen(k1, 100);
        b1.ueberweisen(k1, b2, k2, -20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNichtGedeckt() {
        int k1 = b1.neuesKonto();
        int k2 = b2.neuesKonto();
        b1.barEinzahlen(k1, 100);
        b1.ueberweisen(k1, b2, k2, 120);
    }
}
