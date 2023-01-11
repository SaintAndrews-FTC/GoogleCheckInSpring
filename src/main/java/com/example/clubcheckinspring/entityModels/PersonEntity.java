package com.example.clubcheckinspring.entityModels;

public class PersonEntity {
    private String name;
    private String email;
    boolean isCheckedIn;

    public PersonEntity(String name, String email) {
        this.name = name;
        this.email = email;
        isCheckedIn = false;
    }

    public PersonEntity(String name, String email, boolean isCheckedIn) {
        this.name = name;
        this.email = email;
        this.isCheckedIn = isCheckedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }
}
