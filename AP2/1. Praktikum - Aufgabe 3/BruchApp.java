import java.util.Scanner;
import java.util.Arrays;

/**
 * Anwendungsklasse fuer die Klasse <code>Bruch</code>.
 *
 * @author Dominik Schilling
 */
public class BruchApp {
	/**
	 * Speichert das Eingabe Objekt.
	 */
	private static Scanner in;

	/**
	 * Applikation, die Brueche einlesen kann.
	 * Berechnet anschliessend die Summe und den Mittelwert.
	 */
	public static void main( String[] args ) {
		in = new Scanner( System.in );

		System.out.print( "Anzahl Brueche: " );
		int groesse = in.nextInt();

		Bruch[] brueche = new Bruch[groesse];

		for( int i = 0; i < groesse; i++ ) {
			System.out.printf( "%d. Bruch: ", i + 1 );
			try {
				brueche[i] = Bruch.parseBruch( in.next() );
			} catch ( NumberFormatException e ) {
				System.out.println( "\tUngueltiges Format! Richtig: Z/N" );
				i--;
			} catch ( ArithmeticException e ) {
				System.out.println( "\tUngueltiger Bruch!" );
				i--;
			}
		}

		Arrays.sort( brueche );
		System.out.println( "Sortierte Ausgabe: " + Arrays.toString( brueche ) );

		Bruch summe = Bruch.ZERO;
		for( Bruch einBruch : brueche )
			summe = summe.addiere( einBruch );

		System.out.printf( "Die Summe lautet %s.\n", summe );
		System.out.printf( "Der Mittelwert betraegt %s.", summe.dividiere( new Bruch( groesse ) ) );
	}
}
