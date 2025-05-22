package com.casdeveloper.model;

import com.casdeveloper.model.entity.Contact;

import javax.persistence.EntityManager;
import java.util.List;

public class ContactDao extends GenericDao {

    public ContactDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        setClass(Contact.class);
    }

    public void save(Contact contact) {
        super.save(contact);
    }
    public void update(Contact contact) {
        super.update(contact);
    }

    public void delete(Contact contact) {
        super.delete(contact);
    }
    public Contact findById(long id) {
        return (Contact) super.findById(id);
    }

    public Contact findByName(String name) {
        return (Contact) super.findByName(name);
    }

    public List<Contact> findAll() throws Exception {
        return (List<Contact>) super.findAll();
    }
}
