package util;


import java.util.ArrayList;
import java.util.List;


/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach
 * unter einem Schl�ssel gespeicherten Daten gesucht werden
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
	private Entry<K,V>[] data = newArray( 4 );

	/**
	 * Umgeht die Probleme beim Anlegen eines neuen Array mit Typparameter.
	 *
	 * @param length Gr��e des Array
	 * @return neues Array
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V>[] newArray( int length ) {
		return new Entry[ length ];
	}

	/**
	 * Gibt die Anzahl der gespeicherten Eintragungen zur�ck.
	 *
	 * @return Anzahl der eingetragenen Datens�tze.
	 */
	public int size() {
		return size;
	}

	/**
	 * Tr�gt ein Objekt unter einem Suchbegriff ein.
	 * Wenn der Suchbegriff bereits vorhanden, wird die zugeh�rige
	 * Objektreferenz �berschrieben und das vorher dort gespeicherte
	 * Objekt wird zur�ckgegeben. Wenn bisher unter dem Schl�ssel kein
	 * Objekt gespeichert war, wird <code>null</code> zur�ckgegeben.
	 *
	 * @param key Suchschl�ssel.
	 * @param value Inhaltsobjekt.
	 * @return vorheriger Eintrag oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>
	 */
	public V put( K key, V value ) {
		// Key g�ltig?
		checkKey( key );

		// Arraygroesse anpassen
		if ( size == data.length )
			adjustArrayLength();

		// Position des Schl�sselwertes
		int keyPos = indexOf( key );

		// Schl�sselwert ist vorhanden
		if ( keyPos >= 0 ) {
			// Alten Wert mit neuem Wert �berschreiben
			// => alten Wert zur�ckgeben
			return data[ keyPos ].setValue( value );
		}

		// Schl�sselwert ist nicht vorhanden => neuer Eintrag
		data[ size++ ] = new Entry<K, V>( key, value );

		return null;
	}

	/**
	 * Gibt das unter einem Suchbegriff gespeicherte Objekt zurueck.
	 *
	 * @param key Suchschl�ssel.
	 * @return gefundenes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V get( K key) {
		// Key g�ltig?
		checkKey( key );

		// Position des Schl�esselwertes
		int keyPos = indexOf( key );

		// Schl�sselwert ist vorhanden
		if ( keyPos >= 0 )
			return data[ keyPos ].getValue();

		// Schl�sselwert ist nicht vorhanden
		return null;
	}

	/**
	 * Stellt fest, ob der Suchschl�ssel eingetragen ist.
	 *
	 * @param key Suchschl�ssel.
	 * @return <code>true</code> wenn vorhanden.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public boolean containsKey( K key ) {
		// Key g�ltig?
		checkKey( key );

		// Position des Schl�sselwertes
		int keyPos = indexOf( key );

		// Schl�sselwert ist vorhanden
		if ( keyPos >= 0 )
			return true;

		// Schl�sselwert ist nicht vorhanden
		return false;
	}

	/**
	 * Entfernt den Eintrag zu dem angegebenen Suchschl�ssel.
	 * Wenn kein Eintrag zu dem Schluessel gefunden wird, wird
	 * <code>null</code> zurueckgegeben.
	 *
	 * @param key Suchschl�ssel.
	 * @return bisher gespeichertes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V remove( K key ) {
		// Key g�ltig?
		checkKey( key );

		// Position des Schl�sselwertes
		int keyPos = indexOf( key );

		// Schl�sselwert ist vorhanden
		if ( keyPos >= 0 ) {
			// Eintrag mit null �berschreiben
			V tmp = data[ keyPos ].setValue( null );

			// Daten um eins nach links verschieben
			for ( int j = keyPos; j+1 < data.length; j++ )
					data[ j ] = data[ j+1 ];

			// Anzahl der gespeichterten Adressen anpassen
			size--;

			// Alten Wert zur�ckgeben
			return tmp;
		}

		// Schl�sselwert ist nicht vorhanden
		return null;
	}

	/**
	 * Gibt eine Liste mit allen Suchschl�sseln zur�ck.
	 *
	 * @return Liste aller Schl�ssel.
	 */
	public List<K> keys() {
		// ArrayList anlegen
		List<K> keys = new ArrayList<K>();

		// Aktuelle Daten durchlaufen
		for( int i = 0; i < size; i++ ) {
			// Leeren Werte �berspringen
			if ( null == data[i] )
				continue;

			// Schl�sselwert in ArrayList speichern
			keys.add( data[i].getKey() );
		}

		// ArrayList zur�ckgeben
		return keys;
	}

	/**
	 * Ersetzt das bestehende Array durch ein Array doppelter Gr��e.
	 */
	private void adjustArrayLength() {
		// Daten zwischenspeichern
		Entry<K,V>[] tmp = data;

		// Neues Array mit doppelter Gr��e anlegen
		data = newArray( size * 2 );

		// Alte Daten in neues Array �bertragen
		for( int i = 0; i < size; i++ )
			data[i] = tmp[i];
	}

	/**
	 * Pr�ft ob der Key Wert null ist.
	 *
	 * @param key Suchschl�ssel
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	private void checkKey( K key ) {
		if ( null == key )
			throw new NullPointerException( "Ein Key mit Wert null ist nicht erlaubt!" );
	}

	/**
	 * Gibt den Index des ersten Vorkommens eines Schl�sselwertes
	 * zur�ck.
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