package objects;

interface Token {

	/**
	 * Bestimmt den Nachkommen dieses Objekts. Der Nachkomme haengt von der
	 * Anzahl der schwarzen Nachbarn ab. 
	 * @param neighbours Anzahl der schwarzen Nachbarfelder
	 * @return dieses Objekt oder ein neuer Nachbar.
	 */
	public Token nextGeneration(Token[] neighbours);

	/**
	 * Ermittelt ob dieses Objekt schwarz ist.
	 * @return <tt>true</tt> wenn das Objekt schwarz ist
	 */
	public boolean isBlack();

}