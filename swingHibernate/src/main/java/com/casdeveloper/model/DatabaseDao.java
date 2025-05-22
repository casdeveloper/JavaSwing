package com.casdeveloper.model;

import com.casdeveloper.model.enumerator.DatabaseType;
import com.casdeveloper.model.util.FileUtilities;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Statement;
import java.util.List;

public class DatabaseDao {


    EntityManager entityManager;

    private FileUtilities fileUtilities;

    protected List<String> scripts;

    protected String path;

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

    public DatabaseDao(DatabaseType type, EntityManager entityManager) {

        this.entityManager = entityManager;
        this.fileUtilities = new FileUtilities();

        if (DatabaseType.h2.equals(type)) {
            setPath("h2");
        }

        try {
            setScripts(fileUtilities.getResourceFiles(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createDB() {

        URL resource = null;



        for (String script : getScripts()) {
            resource = GenericDao.class.getClassLoader().getResource(getPath() + "/" + script);
            File file = null;
            try {
                file = new File(resource.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            String sql = fileUtilities.loadTextFile(file);

            Session session = entityManager.unwrap(Session.class);

            session.doWork(connection -> {
                connection.createStatement().execute(sql);
            });
        }
    }



}
