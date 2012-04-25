import java.util.List;

/**
 * Diese Schnittstelle beschreibt die Operationen von
 * Klassen zur Verwaltung von Email-Adressb�chern.
 */
public interface IAddressbook {
    
    /**
     * Tr�gt eine neue Adresse in das Adressbuch ein.
     * Der Parameter <code>nickname</code> ist der Schl�sselbegriff.
     * Wenn der Schl�ssel bereits in dem Adressbuch vorhanden ist,
     * werden die Inhalte mit den neuen Werten �berschrieben.
     * 
     * @param nickname Kurzname.
     * @param firstname Vorname.
     * @param lastname Familienname.
     * @param mailAddress Email-Adresse.
     */
    public void addAddress(
        String nickname,
        String firstname,
        String lastname,
        String mailAddress);

    /**
     * F�gt eine Email-Adresse in das Verzeichnis ein.
     * Adress-Objekte sind hinsichlich ihrem Kurznamen (nickname)
     * eindeutig.
     * 
     * @param address Adressobjekt.
     */
    public void addAddress(IAddress address);

    /**
     * Sucht nach den Adressdaten.
     * Wenn nicht gefunden, wird <code>null</code> zur�ckgegeben.
     * 
     * @param name Kurzname.
     * @return Adressobjekt.
     */
    public IAddress addressOf(String name);

    /**
     * �berpr�ft ob der Kurzname bereits als Suchschl�ssel verwendet wurde.
     * 
     * @param name Kurzname
     * @return <code>true</code> wenn Kurzname vorhanden ist.
     */
    public boolean contains(String name);
    
    /**
     * Entfernt Adressdaten aus dem Adressbuch.
     * Wenn keine Daten zu <code>name</code> gefunden werden,
     * bewirkt der Aufruf nichts.
     * 
     * @param name Kurzname der Adressdaten.
     */
    public void remove(String name);
    
    /**
     * Erzeugt eine Liste mit allen Kurznamen.
     * 
     * @return Liste der Kurznamen.
     */
    public List<String> nicknames();
    
    /**
     * Gibt die Anzahl der gespeicherten Adressen zurueck.
     * 
     * @return Anzahl der Adressen
     */
    public int size();
}