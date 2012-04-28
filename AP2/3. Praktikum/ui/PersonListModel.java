package ui;


import java.util.List;

import javax.swing.AbstractListModel;

import book.IAddress;
import book.IAddressbook;

/**
 * Die Klasse implementiert ein Listmodell für eine Adressverwaltung.
 * 
 * Zusätzlich zur vorgegebenen Listenschnittstelle sind Aktionen zum Verändern
 * der Liste implementiert.
 */
@SuppressWarnings("serial")
final class PersonListModel extends AbstractListModel {
    private List<String> names;
    private IAddressbook addresses;

    /**
     * Erzeugt ein neues Modellobjekt für das Adressbuch.
     * 
     * @param addresses das zugehörige Adressbuch.
     */
    PersonListModel(IAddressbook addresses) {
        this.addresses = addresses;
        this.names = addresses.nicknames();
    }

    public int getSize() {
        return names.size();
    }

    public Object getElementAt(int index) {
        return names.get(index);
    }

    /**
     * Fuegt eine neue/geaenderte Adresse dem Adressbuch hinzu.
     * 
     * @param nick Kurzname.
     * @param first Vorname.
     * @param name Nachname.
     * @param address Emailadresse.
     */
    void addAddress(String nick, String first, String name, String address) {
        if (!addresses.contains(nick)) {
            names.add(nick);
            fireIntervalAdded(this, names.size() - 1, names.size() - 1);
        }
        addresses.addAddress(nick, first, name, address);
    }

    /**
     * Gibt die Adresse zu einer Listenauswahl zurück.
     * 
     * @param index Nummer des ausgewaehlten Listenelements.
     * @return komplettes Adressenobjekt.
     */
    IAddress getAddress(int index) {
        return addresses.addressOf(names.get(index));
    }

    /**
     * Entfernt die ausgewaehlte Adresse.
     * 
     * @param index Nummer des ausgewaehlten Listenelements.
     */
    void removeAddress(int index) {
        if (index < 0) return;
        addresses.remove(names.get(index));
        names.remove(index);
        fireIntervalRemoved(this, 0, names.size());
    }
}