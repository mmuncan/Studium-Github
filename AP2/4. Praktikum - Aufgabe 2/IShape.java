/**
 * Allgemeine Schnittstelle zur Beschreibung geometrischer Figuren
 * (AP-Praktikum)
 */
public interface IShape extends Comparable<IShape> { 
    /**
     * Gibt den Objektnamen als String zurueck.
     * 
     * @return Objektname.
     */
    public String getName();
    
    /**
     * Berechnet die Flaeche einer geometrischen Figur.
     * Die abgeleiteten Klassen Rectangle und Circle sollen die jeweils
     * richtige Flaechenformel enthalten.
     * 
     * @return Flaeche der geometrischen Figur.
     */
    public double getArea();
    
    /**
     * Vergleicht die Groesse (Flaeche) zweier Shapes.
     * 
     * @param obj Objekt mit dem verglichen wird.
     * @return
     * <ul>
     *     <li> 0: wenn das Empfaengerobjekt gleich <code>obj</code>;</li>
     *     <li> negative Zahl: wenn das Empfaengerobjekt kleiner als 
     *          <code>obj</code ist></li>
     *     <li> positive Zahl: sonst.</li>
     * </ul>
     * @throws ClassCastException wenn obj kein AbstractShape ist.
     */
    public int compareTo(IShape obj);
    
    
    /**
     * Gibt den Namen der Klasse, gefolgt von einem Punkt (".") und
     * dem Namen des Objekts zurück. Für das Objekt <code>k1</code>
     * der Klasse <code>Circle</code> ergibt sich zum Beipiel
     * <code>Circle.k1</code>.
     * 
     * @return Stringdarstellung der Figur.
     */
    public String toString();
}