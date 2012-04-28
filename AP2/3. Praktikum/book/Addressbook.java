package book;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import util.*;

/**
 * Verwaltet ein Adressbuch.
 */
public final class Addressbook implements IAddressbook {

	/**
	 * Speicherplatz für die Adressen.
	 */
	private IMap<String, IAddress> addresses = new LinearMap<String, IAddress>();

	/**
	 * Stellt ein vorhandenes Adressbuch aus den in einer Datei
	 * gespeicherten Daten wieder her.
	 * Wenn die Eingabedatei nicht vorhanden ist, wird ein
	 * leeres Adressbuch angelegt.
	 *
	 * @param inFile Name der Eingabedatei.
	 */
	public Addressbook(String inFile) {
		restoreData(inFile);
		saveOnExit(inFile);
	}

	/**
	 * Stellt ein Adressbuch zur Verfuegung, das ausschliesslich im
	 * Hauptspeicher gehalten wird.
	 * <p>
	 * Dieser Konstruktor ist vorwiegend fuer Testzwecke da.
	 */
	Addressbook() {
	}

	/**
	 * Diese Methode liest vorher abgelegte Daten
	 * aus der angegebenen Datei.
	 *
	 * @param inFile Dateiname.
	 */
	private void restoreData(String inFile) {
		try {
			DataInputStream in =
				new DataInputStream(
					new BufferedInputStream(new FileInputStream(inFile)));
			int n = in.readInt();
			for (int i = 0; i < n; i++)
				addAddress(new Address(in));
			in.close();
		}
		catch (FileNotFoundException ignore) {
			// ignored: we start with an empty table
		}
		catch (IOException fatal) {
			fatal.printStackTrace();
			System.err.println("\nVermutlich ist file.dat defekt!");
			System.err.println("Am besten wird file.dat geloescht.");
			throw new RuntimeException(fatal);
		}
	}

	/**
	 * Diese Methode sorgt dafür, dass alle Dateninhalte in der
	 * angegebenen Datei gespeichert werden.
	 *
	 * @param fileName Dateiname.
	 */
	private void saveOnExit(final String fileName) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					DataOutputStream out =
						new DataOutputStream(
							new BufferedOutputStream(
								new FileOutputStream(fileName)));
					out.writeInt(size());
					for (String key : nicknames())
						addressOf(key).save(out);
					out.close();
				}
				catch (IOException fatal) {
					fatal.printStackTrace();
				}
			}
		});
	}

	public void addAddress(
		String nickname,
		String firstname,
		String lastname,
		String mailAddress) {
		addresses.put(nickname, new Address(nickname, firstname, lastname, mailAddress));
	}

	/**
	 * Fügt eine Email-Adresse in das Verzeichnis ein.
	 * Adress-Objekte sind hinsichlich ihrem Kurznamen (nickname)
	 * eindeutig.
	 *
	 * @param address Adressobjekt.
	 */
	public void addAddress( IAddress address ) {
		// Key = address.nickname()
		// value = address
		addresses.put(address.nickname(), address );
	}

	/**
	 * Sucht nach den Adressdaten.
	 * Wenn nicht gefunden, wird <code>null</code> zurückgegeben.
	 *
	 * @param name Kurzname.
	 * @return Adressobjekt.
	 */
	public IAddress addressOf( String name ) {
		// Key ist vorhanden
		if ( contains( name) )
			return addresses.get( name );

		// Key ist nicht vorhanden
		return null;
	}

	/**
	 * Überprüft ob der Kurzname bereits als Suchschlüssel verwendet wurde.
	 *
	 * @param name Kurzname
	 * @return <code>true</code> wenn Kurzname vorhanden ist.
	 */
	public boolean contains( String name ) {
		// Key ist vorhanden
		if ( addresses.containsKey( name) )
			return true;

		// Key ist nicht vorhanden
		return false;
	}

	/**
	 * Entfernt Adressdaten aus dem Adressbuch.
	 * Wenn keine Daten zu <code>name</code> gefunden werden,
	 * bewirkt der Aufruf nichts.
	 *
	 * @param name Kurzname der Adressdaten.
	 */
	public void remove( String name ) {
		addresses.remove( name );
	}

	/**
	 * Erzeugt eine Liste mit allen Kurznamen.
	 *
	 * @return Liste der Kurznamen.
	 */
	public List<String> nicknames() {
		return addresses.keys();
	}

	/**
	 * Gibt die Anzahl der gespeicherten Adressen zurueck.
	 *
	 * @return Anzahl der Adressen
	 */
	public int size() {
		return addresses.size();
	}
}
