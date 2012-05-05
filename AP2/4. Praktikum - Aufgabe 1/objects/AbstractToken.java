package objects;

public abstract class AbstractToken implements Token {
	/**
	 * Zaehlt die Zahl der schwarzen Nachbarfelder.
	 *
	 * @param neighbours Nachbarfelder (im Uhrzeigersinn)
	 * @return Anzahl der schwarzen Nachbarn
	 */
	private int blackNeighbours(Token[] neighbours) {
		int count = 0;

		// Durchlaufe jeden Nachbarn
		for( Token neighbour : neighbours) {
			// Ist Nachbar ein schwarzes Token?
			if ( neighbour.isBlack() )
				count++;
		}

		// Anzahl der schwarzen Nachbarn zurückgeben
		return count;
	}

	/**
	 * Bestimmt den Nachfolger des aktuellen Objekts.
	 * @return Objekt der naechsten Generation
	 */
	public Token nextGeneration(Token[] neighbours) {
		return nextGeneration(blackNeighbours(neighbours));
	}

	/**
	 * Die Spielregen werden durch Black-/WhiteToken festgelegt.
	 *
	 * @param n Anzahl der schwarzen Nachbarfelder
	 * @return Objekt der naechsten Generation
	 */
	protected abstract Token nextGeneration(int n);
}
