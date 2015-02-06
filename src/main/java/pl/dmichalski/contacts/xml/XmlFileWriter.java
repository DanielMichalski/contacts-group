package pl.dmichalski.contacts.xml;

import pl.dmichalski.contacts.model.ContactGroup;

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

    public void writeContactsIntoFile(List<ContactGroup> groups, Path filename) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename.toFile());
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        XMLEncoder encoder = new XMLEncoder(bos);
        groups.forEach(encoder::writeObject);
        encoder.close();
    }

    public List<ContactGroup> readContractsFromFile(Path file) throws Exception {
        List<ContactGroup> groups = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        XMLDecoder decoder = new XMLDecoder(bis);

        try {
            Object obj = null;
            while ((obj = decoder.readObject()) != null) {
                if (obj instanceof ContactGroup) {
                    ContactGroup group = (ContactGroup) obj;
                    groups.add(group);
                }
            }
        } catch (Exception ignored) {
        }

        decoder.close();
        return groups;
    }

}
