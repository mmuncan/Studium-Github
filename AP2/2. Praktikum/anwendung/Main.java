package anwendung;

import bank.Bank;

/**
 * Fuehrt beispielhaft ein paar Kontobewegungen aus.
 *
 * Es wird ein neue Bank mit dem Namen "Sparbank" angelegt.
 * Bei dieser Bank werden dann 2 Konten k1 und k2 angelegt.
 * In k1 wird dann 100 Euro eingezahlt und 20 Euro wieder ausgezahlt.
 * Danach wird der Kontostand beider Konten ausgeben.
 * Anschliessend wird in k2 70 Euro eingezahlt.
 * Dann werden 20 Euro von Konto k1 auf Konto k2 ueberwiesen.
 * Zum Schluss werden die Kontobewegungen angezeigt.
 *
 */
public class Main {
    public static void main(String[] args) {
    	// Bank anlegen
    	Bank b = new Bank( "Sparbank" );

    	// Konten anlegen
    	int k1 = b.neuesKonto();
    	int k2 = b.neuesKonto();

    	// Einzahlung
    	b.barEinzahlen( k1, 100 );
    	// Auszahlung
    	b.barAuszahlen( k1, 20 );

    	// Ausgabe Kontostand
    	System.out.printf(
    			"Kontostand von k1 betraegt %.2f und von k2 %.2f.\n",
    			b.kontostand( k1 ),
    			b.kontostand( k2 )
    	);

    	// Einzahlung
    	b.barEinzahlen( k2, 70 );

    	// Ueberweisung
    	b.ueberweisen( k1, b, k2, 70 );

    	// Ausgabe Kontobewegungen
    	b.kontobewegungenAusgeben(k1);
    }
}
