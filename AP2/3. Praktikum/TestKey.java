

/**
 * Diese Klasse erzeugt Suchschl�ssel als Mockobjekte f�r das Testes von
 * LinearMap-Implementierungen. <code>TestKey</code> -Objekte k�nnen ganz normal
 * als Suchschl�ssel verwendet werden. Gleichzeitig wird die Anzahl der Aufrufe
 * der Methode <code>equals</code> gez�hlt, so dass am Ende �der die Aufrufe
 * <code>getTotalCompares</code> und <code>getCompares</code>
 * deren Zahl abgefragt werden kann.
 */
class TestKey implements Comparable<TestKey> {
    private static int totalCompares = 0;
    private int compares = 0;

    private final int value;
    private boolean calledCompareTo = false;

    /**
     * Erzeugt eine Schl�ssel mit definiertem Hashcode.
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
     * Damit eine vern�nftige Ausgabe erscheint.
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
     * Gibt die intern gespeicherte Identit�t zur�ck.
     * @return interne Identit�t
     */
    public int value() {
    	return value;
    }

    /**
     * Gibt die Gesamtzahl der Vergleiche aller Schl�ssel zur�ck,
     * Gez�hlt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Gesamtzahl der Vergleiche.
     */
    public static int getTotalCompares() {
        return totalCompares;
    }

    /**
     * Gibt die Anzahl der Vergleiche f�r diesen Schl�ssel zur�ck,
     * Gez�hlt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Anzahl der Vergleiche.
     */
    public int getCompares() {
        return compares;
    }

    /**
     * L�scht die Gesamtzahl der Vergleiche.
     * Die Anzahl der Vergleich pro Objekt ist hiervon nicht
     * betroffen.
     */
    public static void clearTotalCompares() {
        totalCompares = 0;
    }

    /**
     * L�scht die Anzahl der Vergleiche f�r dieses Objekt.
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