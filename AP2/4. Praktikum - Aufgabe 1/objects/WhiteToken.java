package objects;

/**
 * Repraesentiert ein weisses Objekt.
 */
final class WhiteToken extends AbstractToken {
	private static final WhiteToken instance = new WhiteToken();
	private WhiteToken() { }

	/**
	 * Liefert ein weisses Objekt. Es gibt genau eine Instanz.
	 * @return ein weisses Objekt
	 */
	static WhiteToken instance() {
		return instance;
	}

	/**
	 * Wenn die Anzahl der schwarzen Nachbarn gleich 3 ist, wird ein schwarzes
	 * Token erzeugt. Andernfalls wird diese WhiteToken zurueckgegeben.
	 * @Token der naechsten Generation.
	 */
	protected Token nextGeneration(int neighbours) {
		// TODO: richtige Regel einsetzen.
		if ( 3 == neighbours )
			return BlackToken.instance();

		return instance;
	}

	public boolean isBlack() {
		return false;
	}
}
