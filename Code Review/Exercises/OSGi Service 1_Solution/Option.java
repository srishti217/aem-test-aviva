package com.aviva.aem.test;

/**
 * Represents an option with a name and a value.
 */
public class Option {
    private String name;
    private String value;

    // Parameterized constructor
    public Option(String value, String name) {
        this.name = name;
        this.value = value;
    }

    // Getter for name
    public String getName() {
        return name;
    }
    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Setter for value
    public void setValue(String value) {
        this.value = value;
    }
}
