

import java.util.List;

/**
 * Schnittstelle fuer Klassen, die assoziativ einem Schluesselobjekt eine anderes
 * Objekt zuordnen.
 * <p>
 * Grundsaetzlich duerfen null-Referenzen nicht als Schluessel auftauchen.
 * Sie koennen dagegen als Werte verwendet werden. Allerdings sagt dann die
 * Rueckgabe von <code>null</code> durch die Methode <code>get</code> nichts
 * ueber das Vorhandenseins des Eintrags aus (in diesem Fall ist man auf
 * <code>containsKey</code> angewiesen.
 *
 * @param K Typ des Schluesselobjekts.
 * @param V Typ des zugeordneten Inhalts.
 */
public interface IMap<K,V> {

    /**
     * Gibt die Anzahl der gespeicherten Eintragungen zurueck.
     *
     * @return Anzahl der eingetragenen Datensaetze.
     */
    public int size();

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
    public V put(K key, V value);

    /**
     * Gibt das unter einem Suchbegriff gespeicherte Objekt zurueck.
     *
     * @param key Suchschluessel.
     * @return gefundenes Objekt oder <code>null</code>.
     * @throws NullPointerException wenn <code>key == null</code>.
     */
    public V get(K key);

    /**
     * Stellt fest, ob der Suchschluessel eingetragen ist.
     *
     * @param key Suchschluessel.
     * @return <code>true</code> wenn vorhanden.
     * @throws NullPointerException wenn <code>key == null</code>.
     */
    public boolean containsKey(K key);

    /**
     * Entfernt den Eintrag zu dem angegebenen Suchschluessel.
     * Wenn kein Eintrag zu dem Schluessel gefunden wird, wird
     * <code>null</code> zurueckgegeben.
     *
     * @param key Suchschluessel.
     * @return bisher gespeichertes Objekt oder <code>null</code>.
     * @throws NullPointerException wenn <code>key == null</code>.
     */
    public V remove(K key);

    /**
     * Gibt eine Liste mit allen Suchschluesseln zurueck.
     *
     * @return Liste aller Schluessel.
     */
    public List<K> keys();
}
