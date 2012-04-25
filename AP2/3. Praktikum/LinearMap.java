import java.util.List;

/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach
 * unter einem Schluessel gespeicherten Daten gesucht werden
 * kann.
 */
public  class LinearMap<K,V> implements IMap<K,V> {

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

    /* TODO: Die Klasse soll richtig vervollstaendigt werden.
     */
    public int size() {
    	return size;
    }

    public V put(K key, V value) {
    	return null;
    }

    public V get(K key) {
    	return null;
    }

    public boolean containsKey(K key) {
    	return false;
    }

    public V remove(K key) {
    	return null;
    }

    public List<K> keys() {
    	return null;
    }

    /**
     * Gibt den Index des ersten Vorkommens von
     * <tt>gesucht</tt> zurueck.
     * @param gesucht Objekt das gesucht wird.
     * @return Index des ersten Vorkommens oder -1 wenn nicht gefunden.
     */
    public int indexOf(K gesucht) {
    	for ( int i = 0; i < size; i++ )
    		if (
    				( null == data[i] && null == gesucht ) ||
    				( null != data[i] && data[i].equals( gesucht ) )
    		)
    			return i;

    	return -1;
    }

}