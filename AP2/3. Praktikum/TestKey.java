

/**
 * Diese Klasse erzeugt Suchschlüssel als Mockobjekte für das Testes von
 * LinearMap-Implementierungen. <code>TestKey</code> -Objekte können ganz normal
 * als Suchschlüssel verwendet werden. Gleichzeitig wird die Anzahl der Aufrufe
 * der Methode <code>equals</code> gezählt, so dass am Ende üder die Aufrufe
 * <code>getTotalCompares</code> und <code>getCompares</code>
 * deren Zahl abgefragt werden kann.
 */
class TestKey implements Comparable<TestKey> {
    private static int totalCompares = 0;
    private int compares = 0;

    private final int value;
    private boolean calledCompareTo = false;

    /**
     * Erzeugt eine Schlüssel mit definiertem Hashcode.
     * 
     * @param value der Hashcode.
     */
    public TestKey(int value) {
        this.value = value;
    }

   /**
    * Damit der HashCode nicht anstelle von equals verwendet werden kann,
    * wird er bewusst mehrdeutig definiert.
    */
    @Override
    public int hashCode() {
        return Math.abs(value % 2);
    }
    
    /**
     * Damit eine vernünftige Ausgabe erscheint.
     */
    @Override
    public String toString() {
    	return "TestKey-"+value;
    }

    @Override
    public boolean equals(Object x) {
        compares++;
        totalCompares++;
        if (!(x instanceof TestKey))
            return false;
        return ((TestKey) x).value == value;
    }

    public int compareTo(TestKey x) {
    	calledCompareTo = true;
        return value - x.value;
    }
    
    /**
     * Gibt die intern gespeicherte Identität zurück.
     * @return interne Identität
     */
    public int value() {
    	return value;
    }

    /**
     * Gibt die Gesamtzahl der Vergleiche aller Schlüssel zurück,
     * Gezählt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Gesamtzahl der Vergleiche.
     */
    public static int getTotalCompares() {
        return totalCompares;
    }

    /**
     * Gibt die Anzahl der Vergleiche für diesen Schlüssel zurück,
     * Gezählt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Anzahl der Vergleiche.
     */
    public int getCompares() {
        return compares;
    }

    /**
     * Löscht die Gesamtzahl der Vergleiche.
     * Die Anzahl der Vergleich pro Objekt ist hiervon nicht
     * betroffen.
     */
    public static void clearTotalCompares() {
        totalCompares = 0;
    }

    /**
     * Löscht die Anzahl der Vergleiche für dieses Objekt.
     * Die Gesamtzahl der Vergleiche ist hiervon nicht betroffen.
     */
    public void clearCompares() {
        compares = 0;
    }
    
    /**
     * Wurde compareTo aufgerufen?
     * @return true wenn compareTo aufgerufen wurde.
     */
    public boolean calledCompareTo() {
    	return calledCompareTo;
    }
}