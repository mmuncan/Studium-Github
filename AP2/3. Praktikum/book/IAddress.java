package book;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Diese Schnittstelle beschreibt die Eigenschaften einer
 * Klasse zum Speichern von Email-Adressen.
 */
public interface IAddress {
    
    /**
     * Der Kurzname einer Person.
     * 
     * @return Kurzname.
     */
    public String nickname();
    
    /**
     * Der Vorname einer Person.
     * @return Vorname.
     */
    public String firstname();
    
    /**
     * Der Familienname einer Person.
     * 
     * @return Familienname.
     */
    public String lastname();
    
    /**
     * Die Email-Adresse einer Person.
     * 
     * @return Email-Adresse.
     */
    public String mailAddress();

    /**
     * Ausgabe der Adressdaten über einen Binärdatenstrom.
     * 
     * @param out Ausgabestrom.
     * @throws IOException Ausnahme bei der Ausgabe.
     */
    public void save(DataOutputStream out) throws IOException;
}