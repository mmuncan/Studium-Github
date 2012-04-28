package util;


import java.util.ArrayList;
import java.util.List;


/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach
 * unter einem Schlüssel gespeicherten Daten gesucht werden
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
	 * @param length Größe des Array
	 * @return neues Array
	 */
	@SuppressWarnings("unchecked")
	private Entry<K,V>[] newArray( int length ) {
		return new Entry[ length ];
	}

	/**
	 * Gibt die Anzahl der gespeicherten Eintragungen zurück.
	 *
	 * @return Anzahl der eingetragenen Datensätze.
	 */
	public int size() {
		return size;
	}

	/**
	 * Trägt ein Objekt unter einem Suchbegriff ein.
	 * Wenn der Suchbegriff bereits vorhanden, wird die zugehörige
	 * Objektreferenz überschrieben und das vorher dort gespeicherte
	 * Objekt wird zurückgegeben. Wenn bisher unter dem Schlüssel kein
	 * Objekt gespeichert war, wird <code>null</code> zurückgegeben.
	 *
	 * @param key Suchschlüssel.
	 * @param value Inhaltsobjekt.
	 * @return vorheriger Eintrag oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>
	 */
	public V put( K key, V value ) {
		// Key gültig?
		checkKey( key );

		// Arraygroesse anpassen
		if ( size == data.length )
			adjustArrayLength();

		// Position des Schlüsselwertes
		int keyPos = indexOf( key );

		// Schlüsselwert ist vorhanden
		if ( keyPos >= 0 ) {
			// Alten Wert mit neuem Wert überschreiben
			// => alten Wert zurückgeben
			return data[ keyPos ].setValue( value );
		}

		// Schlüsselwert ist nicht vorhanden => neuer Eintrag
		data[ size++ ] = new Entry<K, V>( key, value );

		return null;
	}

	/**
	 * Gibt das unter einem Suchbegriff gespeicherte Objekt zurueck.
	 *
	 * @param key Suchschlüssel.
	 * @return gefundenes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V get( K key) {
		// Key gültig?
		checkKey( key );

		// Position des Schlüesselwertes
		int keyPos = indexOf( key );

		// Schlüsselwert ist vorhanden
		if ( keyPos >= 0 )
			return data[ keyPos ].getValue();

		// Schlüsselwert ist nicht vorhanden
		return null;
	}

	/**
	 * Stellt fest, ob der Suchschlüssel eingetragen ist.
	 *
	 * @param key Suchschlüssel.
	 * @return <code>true</code> wenn vorhanden.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public boolean containsKey( K key ) {
		// Key gültig?
		checkKey( key );

		// Position des Schlüsselwertes
		int keyPos = indexOf( key );

		// Schlüsselwert ist vorhanden
		if ( keyPos >= 0 )
			return true;

		// Schlüsselwert ist nicht vorhanden
		return false;
	}

	/**
	 * Entfernt den Eintrag zu dem angegebenen Suchschlüssel.
	 * Wenn kein Eintrag zu dem Schluessel gefunden wird, wird
	 * <code>null</code> zurueckgegeben.
	 *
	 * @param key Suchschlüssel.
	 * @return bisher gespeichertes Objekt oder <code>null</code>.
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	public V remove( K key ) {
		// Key gültig?
		checkKey( key );

		// Position des Schlüsselwertes
		int keyPos = indexOf( key );

		// Schlüsselwert ist vorhanden
		if ( keyPos >= 0 ) {
			// Eintrag mit null überschreiben
			V tmp = data[ keyPos ].setValue( null );

			// Daten um eins nach links verschieben
			for ( int j = keyPos; j+1 < data.length; j++ )
					data[ j ] = data[ j+1 ];

			// Anzahl der gespeichterten Adressen anpassen
			size--;

			// Alten Wert zurückgeben
			return tmp;
		}

		// Schlüsselwert ist nicht vorhanden
		return null;
	}

	/**
	 * Gibt eine Liste mit allen Suchschlüsseln zurück.
	 *
	 * @return Liste aller Schlüssel.
	 */
	public List<K> keys() {
		// ArrayList anlegen
		List<K> keys = new ArrayList<K>();

		// Aktuelle Daten durchlaufen
		for( int i = 0; i < size; i++ ) {
			// Leeren Werte überspringen
			if ( null == data[i] )
				continue;

			// Schlüsselwert in ArrayList speichern
			keys.add( data[i].getKey() );
		}

		// ArrayList zurückgeben
		return keys;
	}

	/**
	 * Ersetzt das bestehende Array durch ein Array doppelter Größe.
	 */
	private void adjustArrayLength() {
		// Daten zwischenspeichern
		Entry<K,V>[] tmp = data;

		// Neues Array mit doppelter Größe anlegen
		data = newArray( size * 2 );

		// Alte Daten in neues Array übertragen
		for( int i = 0; i < size; i++ )
			data[i] = tmp[i];
	}

	/**
	 * Prüft ob der Key Wert null ist.
	 *
	 * @param key Suchschlüssel
	 * @throws NullPointerException wenn <code>key == null</code>.
	 */
	private void checkKey( K key ) {
		if ( null == key )
			throw new NullPointerException( "Ein Key mit Wert null ist nicht erlaubt!" );
	}

	/**
	 * Gibt den Index des ersten Vorkommens eines Schlüsselwertes
	 * zurück.
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