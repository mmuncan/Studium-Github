package book;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Speichert die Mail-Adresse einer Person.
 */
public final class Address implements IAddress {
    private String nickname;
    private String firstname;
    private String lastname;
    private String mailAddress;

    /**
     * Dieser Konstruktor liest die Daten aus einer Datei.
     * 
     * @param in Eingabestrom
     * @throws IOException bei fatalem Eingabefehler.
     */
    public Address(DataInputStream in) throws IOException {
        this(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF());
    }

    /**
     * Mit diesem Konstruktor lässt sich eine neue Adresse
     * definieren.
     * 
     * @param nickname eindeutiger Kurzname
     * @param firstname Vorname
     * @param lastname Nachname
     * @param mailAddress Mail-Adresse
     */
    public Address(String nickname, String firstname, String lastname,
        String mailAddress)
    {
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mailAddress = mailAddress;
    }

    /**
     * Schreibt die Addressdaten in eine Datei.
     * 
     * @param out Ausgabestrom.
     * @throws IOException bei fatalem Ausgabefehler.
     */
    public void save(DataOutputStream out) throws IOException {
        out.writeUTF(nickname);
        out.writeUTF(firstname);
        out.writeUTF(lastname);
        out.writeUTF(mailAddress);
    }

    public String firstname() {
        return firstname;
    }

    public String lastname() {
        return lastname;
    }

    public String mailAddress() {
        return mailAddress;
    }

    public String nickname() {
        return nickname;
    }
}