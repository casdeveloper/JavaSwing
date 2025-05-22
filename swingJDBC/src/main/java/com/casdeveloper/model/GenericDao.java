package com.casdeveloper.model;

import com.casdeveloper.model.util.FileUtilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GenericDao {

    private ConnectionDatabase connection;
    private FileUtilities fileUtilities;

    public GenericDao(ConnectionDatabase connection) {
        this.connection = connection;
        this.fileUtilities = new FileUtilities();
    }

    protected void save(String insertSql, Object... parametros) throws SQLException {
        PreparedStatement pstmt =
                connection.getConnection().prepareStatement(insertSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }

        pstmt.execute();
        pstmt.close();
        connection.getConnection().close();
    }

    protected void update(String updateSql, Object id, Object... parametros) throws SQLException {
        PreparedStatement pstmt =
                connection.getConnection().prepareStatement(updateSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        pstmt.setObject(parametros.length + 1, id);
        pstmt.execute();
        pstmt.close();
        connection.getConnection().close();
    }

    protected void delete(String deleteSql, Object... parametros) throws SQLException {
        PreparedStatement pstmt =
                connection.getConnection().prepareStatement(deleteSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }

        pstmt.execute();
        pstmt.close();
        connection.getConnection().close();
    }

    public void craeteDb(List<String> scripts, String path) throws SQLException {
        PreparedStatement pstmt;
        URL resource = null;
        for (String script : scripts) {
            URL scriptUrl = GenericDao.class.getClassLoader().getResource(path + "/" + script);
            File file = null;
            try {
                file = new File(scriptUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            String sql = fileUtilities.loadTextFile(file);
            pstmt = connection.getConnection().prepareStatement(sql);
            pstmt.execute();
            pstmt.close();
            connection.getConnection().close();
        }
    }

    public ConnectionDatabase getConnection() {
        return connection;
    }

    public void setConnection(ConnectionDatabase connection) {
        this.connection = connection;
    }
}
