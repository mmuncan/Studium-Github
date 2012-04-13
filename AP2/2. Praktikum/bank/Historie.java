package bank;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Ein Objekt dieser Klasse speichert einen Umsatzvorgang fuer ein Konto.
 * Gespeichert werden die Aktion, der Geldbetrag (immer positiv) und
 * gegebenenfalls eine Angabe ueber das Zielkonto.
 * 
 * @author Erich Ehses, 2008
 */
final class Historie {
    private final Aktion aktion;
    private final String ziel;
    private final double betrag;

    /**
     * Ersatz fuer auf advm1 unbrauchbares CurrencyFormat
     */
    private static final NumberFormat numberFormat;
    static {
        numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
    }

    /**
     * Formatiert einen Geldbetragt gemaess den deutschen Regeln.
     * 
     * @param betrag Geldbetrag
     * @return formatierter String
     */
    static String format(double betrag) {
       return numberFormat.format(betrag) + " Euro ";
    }

    
    /**
     * Erzeugt das Transaktionsobjekt fuer das Abheben von Bargeld.
     * @param betrag Geldbetrag
     */
    static Historie barAbgehoben(double betrag) {
        return new Historie(Aktion.BAR_AUS, "", betrag);
    }
    
    /**
     * Erzeugt das Transaktionsobjekt fuer das Einzahlen von Bargeld.
     * @param betrag Geldbetrag
     */
    static Historie barEingezahlt(double betrag) {
        return new Historie(Aktion.BAR_EIN, "", betrag);
    }
    
    /**
     * Erzeugt das Transaktionsobjekt fuer das Abbuchen einer Ueberweisung.
     * @paran ziel Zielkonto
     * @param betrag Geldbetrag
     */
    static Historie abgebuchtFuer(String ziel, double betrag) {
        return new Historie(Aktion.UEBERW_AUS, ziel, betrag);
    }
    
    /**
     * Erzeugt das Transaktionsobjekt fuer das Buchen einer Ueberweisung.
     * @param quelle einzahlendes Konto
     * @param double betrag Geldbetrag
     */
    static Historie gebuchtVon(String quelle, double betrag) {
        return new Historie(Aktion.UEBERW_EIN, quelle, betrag);
    }

    /**
     * Erzeugt eine neue Transaktion. Transaktionen werden nur in der Klasse
     * Konto verwendet.
     * 
     * @param aktion Art des Umsatzes
     * @param ziel Partnerkonto (bei Ueberweisung)
     * @param betrag Geldbetrag
     * @param vorherige vorhergehende Transaktion in der Transaktionsliste
     */
    private Historie(Aktion aktion, String ziel, double betrag) {
        this.aktion = aktion;
        this.ziel = ziel;
        this.betrag = betrag;
    }

    /**
     * Ueberschreibt <code>toString</code> aus der Klasse <code>Object</code>.
     * 
     * @return verstaendlicher Text ueber die Kontobewegung
     */
    public String toString() {
        String anderesKonto = "";
        if (aktion == Aktion.UEBERW_AUS)
            anderesKonto = " nach " + ziel;
        else if (aktion == Aktion.UEBERW_EIN)
            anderesKonto = " von " + ziel;
        return aktion + anderesKonto + ", Betrag: " + format(betrag);
    }
}
