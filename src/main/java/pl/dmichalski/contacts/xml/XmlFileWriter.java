package pl.dmichalski.contacts.xml;

import pl.dmichalski.contacts.model.Contact;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel
 */
public class XmlFileWriter {

    public void writeContactsIntoFile(List<Contact> contacts, Path filename) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename.toFile());
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        XMLEncoder encoder = new XMLEncoder(bos);
        contacts.forEach(encoder::writeObject);
        encoder.close();
    }

    public List<Contact> readContractsFromFile(Path file) throws Exception {
        List<Contact> contacts = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        XMLDecoder decoder = new XMLDecoder(bis);

        try {
            Object obj = null;
            while ((obj = decoder.readObject()) != null) {
                if (obj instanceof Contact) {
                    Contact contract = (Contact) obj;
                    contacts.add(contract);
                }
            }
        } catch (Exception ignored) {
        }

        decoder.close();
        return contacts;
    }

}
