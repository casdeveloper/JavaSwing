package com.casdeveloper.view;

import com.casdeveloper.controller.ContactController;
import com.casdeveloper.model.DatabaseDao;
import com.casdeveloper.model.entity.Contact;
import com.casdeveloper.model.enumerator.DatabaseType;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {


    private EntityManager entityManager;
    private JLabel lbName, lbNickName, lbBirthDay;
    private JTextField tfName, tfNickname, tfBirthDay, tfFind;
    private JButton btPrevius, btNext, btFirst, btLast;
    private JButton btSave, btUpdate, btDelete, btClear, btFind;


    private ContactController ctrl;
    private DatabaseDao databaseDao;
    private List<Contact> contactList = new ArrayList<>();
    private int actualRecord = 0;
    private Long key;

    public MainFrame(EntityManager entityManager) {

        super("Contacts");

        this.entityManager = entityManager;

        databaseDao = new DatabaseDao(DatabaseType.h2, entityManager);
        databaseDao.createDB();
        ctrl = new ContactController(DatabaseType.h2, entityManager);

        Container screen = getContentPane();
        setLayout(null);

        lbName = new JLabel("Name");
        lbNickName = new JLabel("Nickname");
        lbBirthDay = new JLabel("Birthday (dd/mm/yyyy)");

        lbName.setBounds(10, 10, 240, 15);
        lbNickName.setBounds(10, 50, 240, 15);
        lbBirthDay.setBounds(10, 90, 240, 15);

        lbName.setForeground(Color.BLACK);
        lbNickName.setForeground(Color.BLACK);
        lbBirthDay.setForeground(Color.BLACK);

        lbName.setFont(new Font("Courier New", Font.BOLD, 14));
        lbNickName.setFont(new Font("Courier New", Font.BOLD, 14));
        lbBirthDay.setFont(new Font("Courier New", Font.BOLD, 14));

        screen.add(lbName);
        screen.add(lbNickName);
        screen.add(lbBirthDay);

        tfName = new JTextField();
        tfNickname = new JTextField();
        tfBirthDay = new JTextField();

        tfName.setBounds(10, 25, 265, 20);
        tfNickname.setBounds(10, 65, 265, 20);
        tfBirthDay.setBounds(10, 105, 265, 20);

        screen.add(tfName);
        screen.add(tfNickname);
        screen.add(tfBirthDay);

        btSave = new JButton("Save");
        btUpdate = new JButton("Update");
        btDelete = new JButton("Delete");
        btClear = new JButton("Clear");
        btFind = new JButton("Find");
        btPrevius = new JButton("<<");
        btNext = new JButton(">>");
        btFirst = new JButton("|<");
        btLast = new JButton(">|");

        btSave.setBounds(280, 25, 80, 20);
        btUpdate.setBounds(280, 65, 80, 20);
        btDelete.setBounds(280, 105, 80, 20);

        screen.add(btSave);
        screen.add(btUpdate);
        screen.add(btDelete);

        btFirst.setBounds(10, 135, 50, 20);
        btPrevius.setBounds(60, 135, 50, 20);
        btClear.setBounds(110, 135, 75, 20);
        btNext.setBounds(185, 135, 50, 20);
        btLast.setBounds(235, 135, 50, 20);

        screen.add(btFirst);
        screen.add(btPrevius);
        screen.add(btClear);
        screen.add(btNext);
        screen.add(btLast);

        JLabel lbFind = new JLabel("Findk by name");
        lbFind.setBounds(10, 160, 220, 20);

        tfFind = new JTextField();
        tfFind.setBounds(10, 180, 220, 20);

        btFind = new JButton("Find");
        btFind.setBounds(230, 180, 55, 20);

        screen.add(btFind);
        screen.add(lbFind);
        screen.add(tfFind);

        setSize(400, 350);
        setVisible(true);
        setLocationRelativeTo(null);




        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickSave();
            }
        });

        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickUpdate();
            }
        });

        btDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickDelete();
            }
        });

        btClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
                actualRecord = 0;
            }
        });

        btFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickFind();
            }
        });

        btFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickFirst();
            }
        });

        btPrevius.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickPrevius();
            }
        });

        btNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickNext();
            }
        });

        btLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickLast();
            }
        });



    }

    private void getValues(int index){
        if (index <= contactList.size() - 1) {
            Contact contact = contactList.get(index);
            tfName.setText(contact.getName());
            tfNickname.setText(contact.getNickName());
            tfBirthDay.setText(new SimpleDateFormat("dd/MM/yyyy").format(contact.getBirth_Day()));
        }
    }

    private void onClickFirst(){
        actualRecord = 0;
        getValues(actualRecord);
    }

    private void onClickPrevius(){
        if (actualRecord != 0) {
            getValues(--actualRecord);
        }

    }

    private void onClickNext(){
        if (actualRecord != contactList.size() - 1) {
            getValues(++actualRecord);
        }
    }

    private void onClickLast(){
        actualRecord = contactList.size() - 1;
        getValues(actualRecord);
    }

    private void onClickUpdate(){

        Long id = 0L;

        if (key == null){
            id = contactList.get(actualRecord).getId();
        } else {
            id = key;
            key = null;
        }

        try {
            ctrl.update(id, tfName.getText(), tfNickname.getText(), tfBirthDay.getText());
            JOptionPane.showMessageDialog(this, "Record updated");
            clearFields();
            contactList = ctrl.getAllContacts();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error updating record - " + e.getLocalizedMessage());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format error - " + e.getLocalizedMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating record - " + e.getLocalizedMessage());
        }
    }


    private void onClickSave(){
        try {
            ctrl.save(tfName.getText(), tfNickname.getText(), tfBirthDay.getText());
            JOptionPane.showMessageDialog(this, "Record updated");
            clearFields();
            contactList = ctrl.getAllContacts();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, "Error updating record - " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, "Format error - " + e.getLocalizedMessage());
        }
    }

    private void onClickDelete(){
        Long id = contactList.get(actualRecord).getId();
        try {
            ctrl.delete(id);
            JOptionPane.showMessageDialog(this, "Record deleted");
            clearFields();
            contactList = ctrl.getAllContacts();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting record - " + e.getLocalizedMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Format error - " + e.getLocalizedMessage());
        }
    }

    private void onClickFind(){
        try {
            Contact contact = ctrl.getContactByName(tfFind.getText());
            if (contact != null) {
                tfName.setText(contact.getName());
                tfNickname.setText(contact.getNickName());
                tfBirthDay.setText(new SimpleDateFormat("dd/MM/yyyy").format(contact.getBirth_Day()));
            }
            key = contact.getId();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error finding record - " + e.getLocalizedMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Contact not found - " + e.getLocalizedMessage());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format error - " + e.getLocalizedMessage());
        }
    }

    private void clearFields() {
        tfName.setText("");
        tfNickname.setText("");
        tfBirthDay.setText("");
        tfFind.setText("");
    }
}
