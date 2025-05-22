package com.casdeveloper.model;

import com.casdeveloper.model.enumerator.DatabaseType;
import com.casdeveloper.model.util.FileUtilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DatabaseDao {

    private ConnectionDatabase connection;

    private FileUtilities fileUtilities;

    protected List<String> scripts;

    protected String path;

    GenericDao genericDao;

    public DatabaseDao(DatabaseType type) {

        this.fileUtilities = new FileUtilities();

        if (DatabaseType.h2.equals(type)) {
            ConnectionDatabaseH2 connectionDatabaseH2 = new ConnectionDatabaseH2();
            setPath("h2");
            this.connection = connectionDatabaseH2;
        }

        try {
            setScripts(fileUtilities.getResourceFiles(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public ConnectionDatabase getConnection() {
        return this.connection;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createDB(){
        GenericDao dao = new GenericDao(getConnection());
        try {
            System.out.println("Creating database...");
            dao.craeteDb(getScripts(), getPath());
        } catch (SQLException e) {
            System.out.println("Error creating database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
