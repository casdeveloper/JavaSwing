package com.casdeveloper;

import com.casdeveloper.model.ConnectionDatabase;
import com.casdeveloper.model.DatabaseDao;
import com.casdeveloper.model.enumerator.DatabaseType;
import com.casdeveloper.view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        System.out.println("Hello World!");
        DatabaseDao databaseDao = new DatabaseDao(DatabaseType.h2, connectionDatabase.getEntityManager());
        databaseDao.createDB();

        MainFrame mainFrame = new MainFrame(connectionDatabase.getEntityManager());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}