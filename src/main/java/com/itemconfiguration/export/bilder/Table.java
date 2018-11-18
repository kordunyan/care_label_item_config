package com.itemconfiguration.export.bilder;

public enum Table {
    MANDATORY_FIELD("mandatory_field"), MANDATORY_TRANSLATION("mandatory_translation");

     private String name;

    Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
