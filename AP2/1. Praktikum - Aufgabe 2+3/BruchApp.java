import java.util.Scanner;
/**
 * Anwendungsklasse für die Klasse Bruch.
 */
public class BruchApp {
	private static Scanner in;

	public static void main( String[] args ) {
		in = new Scanner( System.in );
		System.out.print( "Anzahl Brüche: " );
		int groesse = in.nextInt();

		Bruch[] brueche = new Bruch[groesse];

		for( int i = 0; i < groesse; i++ ) {
			System.out.printf( "%d. Bruch: ", i + 1 );
			try {
				brueche[i] = Bruch.parseBruch( in.next() );
			} catch ( ArithmeticException e ) {
				System.err.printf( "\nUngültiger Bruch!\n" );
				i--;
			}
		}

		java.util.Arrays.sort( brueche );
		System.out.println( "Sortierte Ausgabe: " + java.util.Arrays.toString( brueche ) );

		Bruch summe = Bruch.ZERO;
		for( Bruch einBruch : brueche )
			summe = summe.addiere(einBruch);

		System.out.printf( "Die Summe lautet %s.\n", summe );
		System.out.printf( "Der Mittelwert beträgt %s.", summe.dividiere( new Bruch( groesse ) ) );
	}
}
