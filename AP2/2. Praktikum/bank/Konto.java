package bank;

import util.Array;

/**
 * Die Klasse Konto kapselt ein Bankkonto. Alle Kontobewungen werden in einer Transaktionsliste
 * gesammelt und auf Anforderung auf der Standardausgabe ausgegeben.
 */
class Konto {
    /**
     * Lesbare Beschreibung des Kontos.
     */
    private String beschreibung;
    
    /**
     * Der aktuelle Kontostand (ist immer groesser oder gleich 0).
     */
    private double kontostand;
    
    /**
     * Array aller Kontobewegungen.
     */
    private Array<Historie> verlauf = new Array<Historie>();

    /**
     * Erzeugt ein neues Konto mit dem Kontostand 0.
     * @param beschreibung Beschreibung des Kontos
     */
    Konto(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    /**
     * Gibt den aktuellen Kontostand zurueck.
     * 
     * @return aktueller Kontostand
     */
    double kontostand() {
        return kontostand;
    }
    
    /**
     * Fuehrt eine Bareinzahlung aus.
     * Der eingezahlte Betrag muss groesser als 0 sein.
     * 
     * @param betrag eingezahlter Betrag
     * @throws IllegalArgumentException wenn der Betrag negativ oder 0 ist. 
     */
    void barEinzahlen(double betrag) {
        pruefeObBetragPositiv(betrag);
        kontostand += betrag;
        verlauf.add(Historie.barEingezahlt(betrag));
    }
    
    /**
     * Fuehrt eine Bareausahlung aus./home/erich1/Daten/lehre/paradigmen/workspace
     * Der ausgezahlte Betrag muss groesser als 0 sein und durch das
     * Konto gedeckt sein.
     * 
     * @param betrag ausgezahlter Betrag
     * @throws IllegalArgumentException wenn die Auszahlung nicht moeglich ist. 
     */
    void barAuszahlen(double betrag) {
        pruefeObBetragPositiv(betrag);
        pruefeObAuszahlungGedeckt(betrag);
        kontostand -= betrag;
        verlauf.add(Historie.barAbgehoben(betrag));
    }
    
    /**
     * Fuehrt eine Ueberweisung auf ein anderes Konto aus.
     * Der ueberwiesene Betrag muss groesser als 0 sein und
     * die Ueberweisung muss durch das Konto gedeckt sein.
     * Das andere Konto muss existieren.
     * 
     * @param betrag ausgezahlter Betrag
     * @throws IllegalArgumentException wenn die Ueberweisung nicht moeglich ist. 
     */
    void ueberweisen(double betrag, Konto ziel) {
        ueberweisungAbheben(betrag, ziel);
        ziel.ueberweisungGutschreiben(betrag, this);
    }

    /**
     * Gibt auf der Standardausgabe eine komplette Uebersicht ueber alle
     * Kontobewegungen aus.
     */
    void ausgabeVonAllenBuchungen() {
        System.out.println();
        System.out.println("Kontobewegungen fuer " + this);
        System.out.println("Der Kontostand betraegt: " + Historie.format(kontostand));
        System.out.println("===================================");
        Array<Historie> umgekehrt = verlauf.reversed();
        for (int i =  0; i < umgekehrt.size(); i++) {
            System.out.println(umgekehrt.get(i));
        }
        System.out.println();
    }

    /**
     * Ueberschreibt <code>toString</code> aus der Klasse <code>Object</code>.
     *  @return Angabe ueber die Kontonummer.
     */
    public String toString() {
        return beschreibung;
    }

    /**
     * Hebt das Geld fuer eine Ueberweisung ab.
     * Der Geldbetrag muss positiv sein und durch
     * das Konto gedeckt sein.
     * 
     * @param betrag Geldbetrag 
     * @param ziel fremdes Konto
     * @throws IllegalArgumentException wenn die Ueberweisung nicht moeglich ist
     */
    private void ueberweisungAbheben(double betrag, Konto ziel) {
        pruefeObBetragPositiv(betrag);
        pruefeObAuszahlungGedeckt(betrag);
        kontostand -= betrag;
        verlauf.add(
                Historie.abgebuchtFuer(ziel.toString(), betrag));
    }

    /**
     * Fuehrt eine Ueberweisungsgutschrift aus.
     * 
     * @param betrag Geldbetrag (ist immer positiv)
     * @param quelle fremdes Konto (existiert)
     */
    private void ueberweisungGutschreiben(double betrag, Konto quelle) {
        kontostand += betrag;
        verlauf.add(
                Historie.gebuchtVon(quelle.toString(), betrag));
    }

    /**
     * Prueft ob das Konto eine Auszahlung erlaubt.
     * 
     * @param betrag auszuzahlender Geldbetrag (ist garantiert positiv).
     * @throws IllegalArgumentException wenn die Auszahlung nicht moeglich st.
     */
    private void pruefeObAuszahlungGedeckt(double betrag) {
        if (betrag > kontostand)
            throw new IllegalArgumentException("Abheben von " + betrag + " nicht moeglich, da nur " +
                                               kontostand + " vorhanden.");
    }

    /**
     * Prueft ob eine Geldbetrag, wie verlangt, groesser als 0 ist.
     * 
     * @param betrag zu pruefender Betrag
     * @throws IllegalArgumentException wenn der Betrag illegal ist
     */
    private void pruefeObBetragPositiv(double betrag) {
        if (betrag <= 0)
            throw new IllegalArgumentException("Betrag " + betrag + " ist nicht > 0");
    }
}