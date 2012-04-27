import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach
 * unter einem Schluessel gespeicherten Daten gesucht werden
 * kann.
 */
public class LinearMap<K,V> implements IMap<K,V> {

	/**
	 * Anzahl der gespeicherten Adressen.
	 */
	private int size = 0;

	/**
	 * Feld mit den Daten.
	 */
	private Entry<K,V>[] data = newArray(4);

	/**
	 * Umgeht die Probleme beim Anlegen eines neuen Array mit Typparameter.
	 *
	 * @param length Groesse des Array
	 * @return neues Array
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V>[] newArray(int length) {
		return new Entry[length];
	}

	/**
	 * Gibt die Anzahl der gespeicherten Eintragungen zurueck.
	 *
	 * @return Anzahl der eingetragenen Datensaetze.
	 */
	public int size() {
		return size;
	}

	/**
	 * Traegt ein Objekt unter einem Suchbegriff ein.
	 * Wenn der Suchbegriff bereits vorhanden, wird die zugehoerige
	 * Objektreferenz ueberschrieben und das vorher dort gespeicherte
	 * Objekt wird zurueckgegeben. Wenn bisher unter dem Schluessel kein
	 * Objekt gespeichert war, wird <code>null</code> zurueckgegeben.
	 *
	 * @param key Suchschluessel.
	 * @param value Inhaltsobjekt.
	 * @return vorheriger Eintrag oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>
	 */
	public V put( K key, V value ) {
		// Key gueltig?
		checkKey( key );

		// Arraygroesse anpassen
		if ( size == data.length )
			adjustArrayLength();

		// Position des Schlusselwertes
		int keyPos = indexOf( key );

		// Schluesselwert vorhanden
		if ( keyPos >= 0 ) {
			// Alten Wert mit neuem Wert ueberschreiben
			// Alten Wert zurueckgeben
			return data[keyPos].setValue( value );
		}

		// Schluesselwert ist nicht vorhanden => neuer Eintrag
		data[size++] = new Entry<K, V>( key, value );

		return null;
	}

	/**
	 * Gibt das unter einem Suchbegriff gespeicherte Objekt zurueck.
	 *
	 * @param key Suchschluessel.
	 * @return gefundenes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V get( K key) {
		// Key gueltig?
		checkKey( key );

		// Position des Schlusselwertes
		int keyPos = indexOf( key );

		// Schluesselwert vorhanden
		if ( keyPos >= 0 )
			return data[keyPos].getValue();

		// Schluesselwert nicht vorhanden
		return null;
	}

	/**
	 * Stellt fest, ob der Suchschluessel eingetragen ist.
	 *
	 * @param key Suchschluessel.
	 * @return <code>true</code> wenn vorhanden.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public boolean containsKey( K key ) {
		// Key gueltig?
		checkKey( key );

		// Position des Schlusselwertes
		int keyPos = indexOf( key );

		// Schluesselwert vorhanden
		if ( keyPos >= 0 )
			return true;

		// Schluesselwert nicht vorhanden
		return false;
	}

	/**
	 * Entfernt den Eintrag zu dem angegebenen Suchschluessel.
	 * Wenn kein Eintrag zu dem Schluessel gefunden wird, wird
	 * <code>null</code> zurueckgegeben.
	 *
	 * @param key Suchschluessel.
	 * @return bisher gespeichertes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V remove( K key ) {
		// Key gueltig?
		checkKey( key );

		// Position des Schlusselwertes
		int keyPos = indexOf( key );

		// Schluesselwert vorhanden
		if ( keyPos >= 0 ) {
			// Eintrag mit null ueberschreiben
			V tmp = data[keyPos].setValue( null );

			// Daten um eins nach links verschieben
			for ( int j = keyPos; j + 1 < data.length; j++ )
					data[j] = data[ j+1 ];

			// Anzahl der gespeichterten Adressen anpassen
			size--;

			// Alten Wert zurueckgeben
			return tmp;
		}

		// Schluesselwert nicht vorhanden
		return null;
	}

	/**
	 * Gibt eine Liste mit allen Suchschluesseln zurueck.
	 *
	 * @return Liste aller Schluessel.
	 */
	public List<K> keys() {
		// ArrayList anlegen
		List<K> keys = new ArrayList<K>();

		// Aktuelle Daten durchlaufen
		for( int i = 0; i < size; i++ ) {
			// Keine leeren Werte ausgeben
			if ( null != data[i] ) {
				// Schluesselwert in ArrayList speichern
				keys.add( data[i].getKey() );
			}
		}

		// ArrayList zurueckgeben
		return keys;
	}

	/**
	 * Ersetzt das bestehende Array durch ein Array doppelter Groesse.
	 */
	private void adjustArrayLength() {
		// Daten zwischenspeichern
		Entry<K,V>[] tmp = data;

		// Neues Array mit doppelter Groe§e anlegen
		data = newArray( size * 2 );

		// Alte Daten in neues Array uebertragen
		for( int i = 0; i < size; i++ )
			data[i] = tmp[i];
	}

	/**
	 * Prueft ob der Key Wert null ist.
	 *
	 * @param key Suchschluessel
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	private void checkKey( K key ) {
		if ( null == key )
			throw new NullPointerException( "Key Wert null ist nicht erlaubt!" );
	}

	/**
	 * Gibt den Index des ersten Vorkommens eines Schluesselwertes
	 * zurueck.
	 *
	 * @param key Objekt das gesucht wird.
	 * @return Index des ersten Vorkommens oder -1 wenn nicht gefunden.
	 */
	public int indexOf( K key ) {
		for ( int i = 0; i < size; i++ )
			if ( null != data[i] && data[i].getKey().equals( key ) )
				return i;

		return -1;
	}

}