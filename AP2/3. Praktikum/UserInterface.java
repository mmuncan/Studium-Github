import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Die Klasse beschreibt die Benutzerschnittstelle für eine
 * einfache email-Verwaltung.
 * Die Benutzeraktionen werden umgesetzt durch Aufrufe
 * des im Konstruktor angegebenen Adressbuch-Objekts.
 */
@SuppressWarnings("serial")
public final class UserInterface extends JFrame {
    private static final int TEXT_SIZE = 15;
    private JList selector;
    private PersonListModel model;

    private JTextField keyField = new JTextField(TEXT_SIZE);
    private JTextField firstnameField = new JTextField(TEXT_SIZE);
    private JTextField lastnameField = new JTextField(TEXT_SIZE);
    private JTextField addressField = new JTextField(TEXT_SIZE);

    /**
     * Erzeugt ein GUI-Fenster für ein Adressbuch.
     * 
     * @param addressBook Datenstruktur für das Adressbuch.
     */
    public UserInterface(IAddressbook addressBook) {
        super("Addressbuch");
        JPanel p = new JPanel(new BorderLayout());
        p.add(createSelectionPanel(addressBook), BorderLayout.WEST);
        p.add(createAddressPanel(), BorderLayout.EAST);
        getContentPane().add(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * Linke Seite der GUI.
     * Enthält die Liste zur Auswahl der Person
     * und Schalter zum Löschen und beenden.
     * 
     * @param addressbook das Adressbuch.
     * @return das erzeugte <code>JPanel</code>.
     */
    private JPanel createSelectionPanel(IAddressbook addressbook) {
        LabelledItemPanel p = new LabelledItemPanel();
        p.setBorder(BorderFactory.createEtchedBorder());
        p.addItem("Personen", createPersonList(addressbook));
        JPanel p1 = new JPanel(new GridLayout(0, 2));
        p1.setBorder(BorderFactory.createEtchedBorder());
        p1.add(new JButton(new Exiter()));
        p1.add(new JButton(new Remover()));
        p.addItem("", p1);
        return p;
    }

    /**
     * Erstellt die <code>JList</code> mit allen Personen
     * des Adressbuchs.
     * 
     * @param addressBook das Adressbuch.
     * @return eine scrollbare Liste der Personen.
     */
    private JScrollPane createPersonList(IAddressbook addressBook) {
        model = new PersonListModel(addressBook);
        selector = new JList(model);
        selector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selector.addListSelectionListener(new EntrySelector());
        return new JScrollPane(selector);
    }

    /**
     * Erzeugt die rechte Seite der GUI.
     * Enthält die Ein-/Ausgabefelder für Adressdaten und
     * einen Knopf zur Übernahme neuer/geänderter Daten.
     * 
     * @return <code>JPanel</code> für die rechte Seite.
     */
    private JPanel createAddressPanel() {
        LabelledItemPanel p = new LabelledItemPanel();
        p.setBorder(BorderFactory.createEtchedBorder());
        p.addItem("Suchname", keyField);
        p.addItem("Vorname", firstnameField);
        p.addItem("Nachname", lastnameField);
        p.addItem("Mail-Adresse", addressField);
        p.addItem("", new JButton(new EntryReader()));
        return p;
    }

    /**
     * Diese Klasse beschreibt den Endeknopf und die
     * Endeaktion.
     */
    private class Exiter extends AbstractAction {
        public Exiter() {
            putValue(Action.NAME, "Ende");
        }
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Diese Klasse beschreibt den Löschknopf und die 
     * Löschaktion.
     */
    private class Remover extends AbstractAction {
        public Remover() {
            putValue(Action.NAME, "Löschen");
        }
        public void actionPerformed(ActionEvent e) {
            model.removeAddress(selector.getSelectedIndex());
        }
    }

    /**
     * Diese Klasse beschreibt den Übernahmeknopf
     * und die Übernahme der eingegebenen Daten.
     */
    private class EntryReader extends AbstractAction {
        private static final long serialVersionUID = -2545453603498416939L;

        public EntryReader() {
            putValue(Action.NAME, "Übernehmen");
        }
        public void actionPerformed(ActionEvent e) {
            if (!keyField.getText().trim().equals("")) {
                model.addAddress(
                    keyField.getText().trim(),
                    firstnameField.getText().trim(),
                    lastnameField.getText().trim(),
                    addressField.getText().trim());
            }
        }
    }

    /**
     * Diese Klasse beschreibt die Aktion bei der Auswahl einer
     * Person, d.h. die Ausgabe der Personendaten.
     */
    private class EntrySelector implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            int index = selector.getSelectedIndex();
            if (index >= 0) {
                IAddress a = model.getAddress(index);
                keyField.setText(a.nickname());
                firstnameField.setText(a.firstname());
                lastnameField.setText(a.lastname());
                addressField.setText(a.mailAddress());
            }
        }
    }
}
