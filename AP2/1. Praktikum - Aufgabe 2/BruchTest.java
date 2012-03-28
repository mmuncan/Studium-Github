import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Testet die Klasse <code>Bruch</code>
 * 
 * @author Erich Ehses
 */
public class BruchTest {

    /**
     * <code>EPS</code> wird als Genauigkeitsschranke beim Vergleich von
     * Zahlen benutzt.
     */
    private static final double EPS = 1E-12;

    /**
     * Testet den Konstruktor <code>Bruch(zahl)</code>. 
     * <p>
     * Es wird vorausgesetzt, dass die Methoden <code>zaehler()</code>
     * und <code>nenner()</code> funktionieren.
     */
    @Test
    public void einfacherKonstruktoraufruf() {
        Bruch r = new Bruch(4);
        assertEquals(4, r.zaehler());
        assertEquals(1, r.nenner());

        r = new Bruch(-4);
        assertEquals(-4, r.zaehler());
        assertEquals(1, r.nenner());

        r = new Bruch(0);
        assertEquals(0, r.zaehler());
        assertEquals(1, r.nenner());

        r = new Bruch(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, r.zaehler());
        assertEquals(1, r.nenner());
    }
    
    /**
     * Testet ob die beiden Konstanten ONE und ZERO
     * korrekt initialisiert wurden.
     */
    @Test
    public void konstanten() {
        assertEquals(1, Bruch.ONE.zaehler());
        assertEquals(1, Bruch.ONE.nenner());
        assertEquals(0, Bruch.ZERO.zaehler());
        assertEquals(1, Bruch.ZERO.nenner());
    }

    /**
     * Testet dem Konstruktor <code>Bruch(zaehler, nenner)</code>. Es wird
     * besonders auf das richtige Kuerzen geachtet.
     */
    @Test
    public void normalerKonstruktoraufruf() {
        Bruch r = new Bruch(100, 24);
        assertEquals(25, r.zaehler());
        assertEquals(6, r.nenner());

        r = new Bruch(-100, -24);
        assertEquals(25, r.zaehler());
        assertEquals(6, r.nenner());

        r = new Bruch(-100, 24);
        assertEquals(-25, r.zaehler());
        assertEquals(6, r.nenner());

        r = new Bruch(100, -24);
        assertEquals(-25, r.zaehler());
        assertEquals(6, r.nenner());
        
        r = new Bruch(3000000000L, 4000000000L);
        assertEquals(3, r.zaehler());
        assertEquals(4, r.nenner());
        
        r = new Bruch(3, 3000000001L);
        assertEquals(3, r.zaehler());
        assertEquals(3000000001L, r.nenner());
    }
    

    /**
     * Testet, ob bei einem Konstruktoraufruf mit Zaehler = 0 auch eine
     * <code>ArithmeticException</code> ausgeloest wird.
     */
    @Test(expected = ArithmeticException.class)
    public void nennerIst0() {
        new Bruch(10, 0);
    }
    
    @Test(expected = ArithmeticException.class)
    public void nullDurchNull() {
        new Bruch(0, 0);
    }
   
    /**
     * Testet das richtige Verhalten von equals(). Dazu gehoert auch der
     * Vergleich mit <code>null</code> und mit einem Objekt eines anderen
     * Typs.
     */
    @Test
    public void korrektesEquals() {
        Bruch r1 = new Bruch(-2, 3);
        Bruch r2 = new Bruch(2, 3);
        Bruch r3 = new Bruch(20, -30);
        assertTrue("Die Brueche muessten gleich sein", r1.equals(r3));
        assertTrue("equals ist nicht symmetrisch", r3.equals(r1));
        assertFalse("Die Brueche muessten verschieden sein", r1.equals(r2));
        assertFalse("equals ist nicht symmetrisch", r2.equals(r1));
        assertFalse("Kein Objekt kann gleich null sein", r1.equals(null));
        assertFalse("Ein Bruch ist kein String", r1.equals("-2/3"));
    }
    
    /**
     * Testet die Funktionsweise von parseBruch.
     * Macht erst Sinn, wenn equals funktioniert.
     */
    @Test
    public void stringNachBruch() {
        assertEquals(Bruch.ZERO, Bruch.parseBruch("0"));
        assertEquals(Bruch.ONE, Bruch.parseBruch("1"));
        assertEquals(Bruch.ONE, Bruch.parseBruch("1/1"));
        assertEquals(new Bruch(-1), Bruch.parseBruch("-1/1"));
        assertEquals(new Bruch(-1,2), Bruch.parseBruch("-10/20"));
        assertEquals(new Bruch(-20), Bruch.parseBruch("-20"));
    }
    
    @Test(expected = NumberFormatException.class)
    public void illegalerBruchString() {
        Bruch.parseBruch("1.2");
    }

    /**
     * Testet die Addition. Die beteiligten Objekte duerfen nicht veraendert
     * werden.
     * <p>
     * Die Methode equals() muss funktionieren! 
     */
    @Test
    public void addition() {
        Bruch r1 = new Bruch(2, 3);
        Bruch r2 = new Bruch(5, 6);
        Bruch r3 = r1.addiere(r2);
        assertEquals(3, r3.zaehler());
        assertEquals(2, r3.nenner());
        assertEquals(new Bruch(2, 3), r1);
        assertEquals(new Bruch(5, 6), r2);
        assertEquals(new Bruch(9, 6), r3);

        r1 = new Bruch(1, 2);
        r2 = new Bruch(2, -4);
        r3 = r1.addiere(r2);
        assertEquals(new Bruch(0), r3);
    } 

    /**
     * Testet die Subtraktion. Die beteiligten Objekte duerfen nicht veraendert
     * werden.
     * <p>
     * Die Methode equals() muss funktionieren! 
     */
    @Test
    public void subraktion() {
        Bruch r1 = new Bruch(2, 3);
        Bruch r2 = new Bruch(5, 6);
        Bruch r3 = r1.subtrahiere(r2);
        assertEquals(-1, r3.zaehler());
        assertEquals(6, r3.nenner());
        assertEquals(new Bruch(2, 3), r1);
        assertEquals(new Bruch(5, 6), r2);

        r1 = new Bruch(1, 2);
        r2 = new Bruch(2, -4);
        r3 = r1.subtrahiere(r2);
        assertEquals(new Bruch(1), r3);
    }

    /**
     * Testet die Multiplikation. Die beteiligten Objekte duerfen nicht
     * veraendert werden.
     * <p>
     * Die Methode equals() muss funktionieren! 
     */
    @Test
    public void multiplikation() {
        Bruch r1 = new Bruch(2, 3);
        Bruch r2 = new Bruch(5, 6);
        Bruch r3 = r1.multipliziere(r2);
        assertEquals(new Bruch(2, 3), r1);
        assertEquals(new Bruch(5, 6), r2);
        assertEquals(new Bruch(5, 9), r3);

        r1 = new Bruch(1, 2);
        r2 = new Bruch(2, -4);
        r3 = r1.multipliziere(r2);
        assertEquals(new Bruch(-1, 4), r3);
    }

    /**
     * Testet die Division. Die beteiligten Objekte duerfen nicht veraendert
     * werden. Die Divison durch 0 muss zu einer
     * <code>ArithmeticException</code> fuehren.
     * <p>
     * Die Methode equals() muss funktionieren! 
     */
    @Test
    public void division() {
        Bruch r1 = new Bruch(2, 3);
        Bruch r2 = new Bruch(5, 6);
        Bruch r3 = r1.dividiere(r2);
        assertEquals(new Bruch(2, 3), r1);
        assertEquals(new Bruch(5, 6), r2);
        assertEquals(new Bruch(4, 5), r3);
    }
    
    @Test(expected = ArithmeticException.class)
    public void divisionDurch0() {
        new Bruch(1, 2).dividiere(Bruch.ZERO);
    }

    /**
     * Testet den Kehrwert. Der Kehrwert von 0 muss eine
     * <code>ArithmeticException</code> erzeugen.
     * <p>
     * Die Methode equals() muss funktionieren! 
     */
    @Test
    public void kehrwert() {
    	assertEquals(new Bruch(2,5), new Bruch(5,2).kehrwert());
    	assertEquals(new Bruch(2,-5), new Bruch(-5,2).kehrwert());
    	assertEquals(new Bruch(10,1), new Bruch(2,20).kehrwert());
    	assertEquals(new Bruch(10,1), new Bruch(2,20).kehrwert());
    }
    
    @Test(expected = ArithmeticException.class)
    public void kehrwertVon0() {
        Bruch.ZERO.kehrwert();
    }

    /**
     * Testet die Methode <code>doubleValue()</code>, die den Wert des Bruchs
     * als Zahl zurueck gibt.
     */
    @Test
    public void nachDouble() {
        Bruch r1 = new Bruch(2, 3);
        Bruch r2 = new Bruch(-3, 2);
        assertEquals(2. / 3., r1.doubleValue(), EPS);
        assertEquals(-1.5, r2.doubleValue(), EPS);
    }

    /**
     * Testet die richtige Implementierung von <code>compareTo()</code>. Das
     * Ergebnis muss kleiner 0, gleich 0 oder groesser 0 sein, je nachdem ob das
     * linke Objekt (das Empfaengerobjekt) kleiner, gleich oder groesser als das
     * rechte Objekt (das Argument) ist.
     * <P>
     * HINWEIS: Wenn bei 10 mal groesserer Zahl riesig der Test immer noch
     *          funktioniert, ist <tt>compareTo</tt> ganz gut
     *          programmiert!
     */
    @Test
    public void vergleichsoperation() {
    	long riesig = 3000000000000000L;
        Bruch r1 = new Bruch(-2, 3);
        Bruch r2 = new Bruch(2, 3);
        assertTrue("-2/3 muss < 0 sein",
        		r1.compareTo(r2) < 0);
        assertTrue("+2/3 muss > 0 sein",
        		r2.compareTo(r1) > 0);
        assertTrue("2/3 muss == 20/30 sein",
        		r2.compareTo(new Bruch(20, 30)) == 0);
        Bruch big = new Bruch(riesig);
        assertTrue("es hat einen Zahlenueberlauf gegeben",
        		Bruch.ZERO.compareTo(big) < 0);
        assertTrue("es hat einen Zahlenueberlauf gegeben",
        		big.compareTo(Bruch.ZERO) > 0);
        assertTrue("compareTo ist zu ungenau",
        		big.compareTo(big.subtrahiere(Bruch.ONE)) > 0);
        assertTrue("die Vergleichsformel ist falsch", 
        		new Bruch(2,3).compareTo(new Bruch(7,30)) > 0);
    }

    /**
     * Testet toString. Die Ausgabe soll lauten <tt>zaehler/nenner</tt>. Wenn
     * der Nenner gleich 1 ist, wird nur der String-Wert des Zaehlers
     * zurueckgegeben.
     */
    @Test
    public void lesbarerString() {
        assertEquals("17", new Bruch(17).toString());
        assertEquals("-17", new Bruch(-17).toString());
        assertEquals("0", Bruch.ZERO.toString());
        assertEquals("1", Bruch.ONE.toString());

        assertEquals("2/3", new Bruch(-4, -6).toString());
        assertEquals("-2/3", new Bruch(4, -6).toString());
    }
    
    /**
     * Testet die <tt>hashCode</tt>-Operation. Wenn zwei Brueche gleich sind,
     * muessen auch die HashCodes gleich sein. Mehr kann man allerdings
     * auch nicht mehr verlangen.
     */
    @Test
    public void korrekterHashCode() {
        Bruch r1 = new Bruch(20, 30);
        Bruch r2 = new Bruch(-2, -3);
        assertEquals(r1.hashCode(), r2.hashCode());
        
        r1 = Bruch.ONE;
        r2 = Bruch.ZERO.addiere(Bruch.ONE);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}