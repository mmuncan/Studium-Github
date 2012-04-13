package bank;

import util.Array;

/**
 * Die Klasse stellt die einzige oeffentliche Schnittstelle fuer den Umgang mit einem Konto
 * dar.
 * Konten werden generell ueber die von der Bank vergebene Kontonummer angesprochen.
 * Alle Methoden verlangen, dass die angegebenen Konten existieren, dass Geldbetraege groesser als 0 sind
 * und dass das Konto eine eventuelle Auszahlung deckt.
 *
 * Wenn eine der Voraussetzungen nicht zutrifft, wird eine <code>IllegalArgumentException</code> geworfen.
 */
public class Bank {
    /**
     * Name der Bank
     */
    private String name;

    /**
     * Anzahl der Konten.
     */
    private int anzahlKonten = 0;

    /**
     * Array aller Konten
     */
    private Array<Konto> konten = new Array<Konto>();


    /**
     * Erzeugt eine neue Bank.
     * Die neue Bank hat noch keine Bankkonten.
     *
     * @param name Name der Bank.
     */
    public Bank(String name) {
        this.name = name;
    }

    /**
     * Legt ein neues leeres Konto an. Konten werden intern
     * gespeichert und koennen spaeter durch die von der Methode
     * zurueckgegebene Kontonummer angesprochen werden.
     *
     * @return Kontonummer
     */
    public int neuesKonto() {
        int kontoNr = anzahlKonten + 1;
        konten.add( new Konto(name + ": " + kontoNr) );
        anzahlKonten++;

        return kontoNr;
    }

    /**
     * Fuehrt eine Bareinzahlung aus. Das angegebene Konto muss existieren
     * und der Geldbetrag muss groesser als 0 sein.
     *
     * @param nr Kontonummer
     * @param betrag Geldbetrag
     * @throws IllegalArgumentException bei falscher Eingabe
     */
    public void barEinzahlen(int nr, double betrag) {
        getKonto(nr).barEinzahlen(betrag);
    }

    /**
     * Fuehrt eine Barauszahlung aus. Neben der Forderung, dass der
     * Geldbetrag positiv ist muss auch das Konto gedeckt sein.
     *
     * @param nr Kontonummer
     * @param betrag Geldbetrag
     * @throws IllegalArgumentException bei falscher Eingabe
     */
    public void barAuszahlen(int nr, double betrag) {
        getKonto(nr).barAuszahlen(betrag);
    }

    /**
     * Fuehrt eine Ueberweiung aus. Die Ueberweisung ist nur moeglich,
     * wenn sie durch das Ausgangskonto (quelle) gedeckt ist.
     * Es ist nicht erlaubt, negative Betraege anzugeben.
     *
     * @param quelle Kontonummer von der aus ueberwiesen wird
     * @param zielBank Bank zu der ueberwiesen wird
     * @param zielKonto Kontonummer zu der ueberwiesen wird
     * @param betrag Geldbetrag
     * @throws IllegalArgumentException bei falscher Eingabe
     */
    public void ueberweisen(int quelle, Bank zielBank, int zielKonto, double betrag) {
        getKonto(quelle).ueberweisen(betrag, zielBank.getKonto(zielKonto));
    }

    /**
     * Gibt den aktuellen Kontostand des angegebenen Kontos zurueck.
     *
     * @param nr Kontonummer
     * @return aktueller Kontostand
     * @throws IllegalArgumentException wenn das Konto nicht existiert.
     */
    public double kontostand(int nr) {
        return getKonto(nr).kontostand();
    }

    /**
     * Gibt die Kontobewegungen fuer das angegebene Konto auf Standardausgabe
     * aus.
     *
     * @param kontoNr Nummer des auszugebenden Kontos
     * @throws IllegalArgumentException wenn das Konto nicht existiert
     */
    public void kontobewegungenAusgeben(int kontoNr) {
        getKonto(kontoNr).ausgabeVonAllenBuchungen();
    }

    /**
     * Gibt den Namen der Bank zurueck.
     * @return Name der Bank.
     */
    public String toString() {
        return name;
    }

    /**
     * Sucht das Kontoobjekt bei bekannter Kontonummer.
     *
     * @param kontoNr Kontonummer
     * @return Kontoobjekt
     * @throws IndexOutOfBoundsException wenn das Konto nicht existiert
     */
    private Konto getKonto(int kontoNr) {
        if (kontoNr <= 0 || kontoNr > anzahlKonten)
            throw new IllegalArgumentException(
            		"illegale Kontonummer: " + kontoNr);

        return konten.get(kontoNr-1);
    }
}
