package objects;

/**
 * Repraesentiert ein schwarzes Token.
 */
final class BlackToken extends AbstractToken {
	private static final BlackToken instance = new BlackToken();
	private BlackToken() {	}

	/**
	 * Liefert ein schwarzes Objekt. Es gibt genau eine Instanz.
	 * @return ein schwarzes Objekt
	 */
	static BlackToken instance() {
		return instance;
	}

	/**
	 * Wenn die Anzahl der schwarzen Nachbarn 2 oder 3 ist, ueberlebt dieses
	 * schwarze Token. Andernfalls wird eine Instanz von WhiteToken
	 * zurueckgegeben.
	 * @param neighbours Anzahl der schwarzen Nachbarfelder
	 * @return Token der naechsten Generation.
	 */
	protected Token nextGeneration(int neighbours) {
		// TODO: richtige Regel einsetzen
		if ( 3 == neighbours || 2 == neighbours )
			return instance;

		return WhiteToken.instance();
	}

	/**
	 * Diese Token ist schwarz.
	 * @return immer <tt>true</tt>
	 */
	public boolean isBlack() {
		return true;
	}
}
