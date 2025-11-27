package contact;

import exception.AlreadyExistsException;
import exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private final List<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        Contact founded = getContactByName(contact.getName());
        if (founded != null) {
            throw new AlreadyExistsException("Contact with name " + contact.getName() + " already exists!");
        }
        contacts.add(contact);
    }

    public void updateContact(Contact contact) {
        Contact founded = getContactByName(contact.getName());
        if (founded == null) {
            throw new NotFoundException("Contact with name " + contact.getName() + " not found");
        }
        contacts.remove(founded);
        contacts.add(contact);
    }

    public void removeContact(String name) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public Contact getContactByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
