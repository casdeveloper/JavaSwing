package com.casdeveloper;

import com.casdeveloper.model.DatabaseDao;
import com.casdeveloper.model.enumerator.DatabaseType;
import com.casdeveloper.view.MainFrame;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        DatabaseDao databaseDao = new DatabaseDao(DatabaseType.h2);
        databaseDao.createDB();

        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}