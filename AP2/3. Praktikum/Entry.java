/**
 * Hilfsklasse für einzelne Einträge.
 * @param K Typ der Schluesselobjekte
 * @param V Typ der zugeordneten Daten
 */
class Entry<K,V> {
    private final K key;
    private V value;

    /**
     * Konstruktor.
     * 
     * @param key Schluesselbegriff.
     * @param value eigentlicher Inhalt.
     */
    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Gibt den Schluesselbegriff des Eintrags zurück.
     * 
     * @return Schluessel.
     */
    K getKey() {
        return key;
    }
    
    /**
     * Gibt den Inhalt des Eintrags zurueck.
     * 
     * @return Inhalt.
     */
    V getValue() {
        return value;
    }
    
    /**
     * Veraendert den Inhalt des Eintrags und gibt den überschriebenen
     * Inhalt zurück.
     * 
     * @param value neuer Inhalt.
     * @return vorhergehender Inhalt.
     */
    V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}