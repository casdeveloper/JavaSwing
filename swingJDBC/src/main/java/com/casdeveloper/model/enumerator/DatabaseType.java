package com.casdeveloper.model.enumerator;

import java.io.Serializable;

public enum DatabaseType implements Serializable {

    h2("H2 Database"),
    sqlLite("SQLLite Database");

    public final String type;

    DatabaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static DatabaseType fromValue(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Database type name cannot be null");
        }
        for (DatabaseType type : DatabaseType.values()) {
            if (type.getType().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Database type " + name + " does not exist");
    }
}
