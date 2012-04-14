package util;

/**
 * Klasse zum Speichern von Daten. (sich selbst vergroesserndes Array).
 * <p>
 * Neue Daten werden stets am Ende angehaengt..
 * Der Zugriff auf die Inhalte erfolgt ueber die Indexnummer von 0 bis
 * Anzahl der Elemente - 1.
 *
 * @param T Elementtyp
 */
public class Array<T> {
	/**
	 * Kennung "nicht gefunden".
	 */
	private static final int NOT_FOUND = -1;

	/**
	 * Anzahl der gespeicherten Datenelemente
	 */

    private int size = 0;
    /**
     * Array zum Speichern der Daten.
     */
    private T[] data = newArray(2);

    /**
     * Erzeugt ein Array der Groesse <tt>length</tt>.
     * Das Array hat den statischen Typ des Array-Objekts.
     *
     * @param length Groesse des Arrays
     * @return neues Array
     */
    @SuppressWarnings("unchecked")
	private T[] newArray(int length) {
    	/*
    	 * Hinweis zu Typparameter:
    	 * a) zum Erzeugen eines Arrays muss der Elementtyp bekannt sein.
    	 * b) wenn man beliebige Referenzen speichern will, muss dies
    	 *    Object sein.
    	 * c) fuer die Rueckgabe braucht man eine Typangabe.
    	 * d) diese fuehrt zu der Warnung, dass Typparameter zur Laufzeit
    	 *    nicht geprueft werden koennen.
    	 *
    	 * Tieferer Grund: Unstimmigkeiten im Java-Typsystem.
    	 */
    	return (T[]) new Object[length];
    }

    /**
     * Gibt die Anzahl der gespeicherten Inhalte zurueck.
     *
     * @return Anzahl der Inhalte
     */
    public int size() {
        return size;
    }

    /**
     * Gibt das index-te Element zurueck.
     * Nur erlaubt, wenn genuegend viele Elemente gespeichert sind.
     *
     * @param index Elementnummer
     * @return gewaehltes Element
     * @throws IndexOutOfBoundsException wenn <tt>index</tt> illegal ist.
     */
    public T get(int index) {
    	checkIndex(index);

        return data[index];
    }

    /**
     * Speichert Inhalt.
     * Das Objekt wird als letztes Element hinzugefuegt.
     *
     * @param neu zu speichernde Transkation.
     */
    public void add(T neu) {
       if (size == data.length)
    	   adjustArrayLength();

       data[size++] = neu;
    }

	/**
	 * Gibt ein Feld zurueck, dass die umgekehrte Elementreihenfolge hat.
	 * Das this-Objekt wird nicht veraendert!
	 * @return neues umgedrehtes Array
	 */
	public Array<T> reversed() {
		Array<T> reversedData = new Array<T>();

		for( int i = data.length - 1; i >= 0; i--)
			reversedData.add(data[i]);

		return reversedData;
	}

    /**
     * Gibt den Index des ersten Vorkommens von
     * <tt>gesucht</tt> zurueck.
     * @param gesucht Objekt das gesucht wird.
     * @return Index des ersten Vorkommens oder -1 wenn nicht gefunden.
     */
    public int indexOf(T gesucht) {
    	for ( int i = 0; i < size; i++ )
    		if ( ( gesucht == null && data[i] == null ) || data[i].equals( gesucht ) )
    			return i;

    	return NOT_FOUND;
    }

	/**
	 * Ersetzt das bestehende Array durch ein Array doppelter Groesse.
	 */
	private void adjustArrayLength() {
		int oldLength = data.length;
		T[] tmp = data;
		data = newArray( oldLength * 2 );

		for( int i = 0; i < oldLength; i++)
			data[i] = tmp[i];
	}

    /**
     * Prueft ob <tt>index</tt> im erlaubten Bereich
     * von 0 bis Anzahl der Elemente - 1 liegt.
     *
     * @param index zu pruefender Index
     * @throws IndexOutOfBoundsexception wenn <tt>index</tt> ausserhalb des
     *          erlaubten Bereichs liegt.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " nicht erlaubt");
    }
}
