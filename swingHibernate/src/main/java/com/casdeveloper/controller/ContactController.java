package com.casdeveloper.controller;

import com.casdeveloper.model.ContactDao;
import com.casdeveloper.model.entity.Contact;
import com.casdeveloper.model.enumerator.DatabaseType;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ContactController {

    EntityManager entityManager;
    private ContactDao contactDao;

    public ContactController(DatabaseType databaseType, EntityManager entityManager) {

        this.entityManager = entityManager;
        this.contactDao = new ContactDao(entityManager);

    }
    private Date formatarData(String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date( formatter.parse(data).getTime() );
    }

    public void save(String name, String nickName, String burth_day) throws SQLException, ParseException {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setNickName(nickName);
        contact.setBirth_Day(formatarData(burth_day));

        contactDao.save(contact);
    }

    public void update(Long id, String name, String nickName, String burth_day) throws SQLException, ParseException {
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setNickName(nickName);
        contact.setBirth_Day(formatarData(burth_day));

        contactDao.save(contact);
    }

    public void delete(Long id) throws SQLException, ParseException {
        Contact contact = new Contact();
        contact.setId(id);
        contactDao.delete(contact);
    }

    public Contact getContactByName(String name) throws SQLException, ParseException {
        return contactDao.findByName(name);
    }

    public List<Contact> getAllContacts() throws Exception {
        return contactDao.findAll();
    }
}
