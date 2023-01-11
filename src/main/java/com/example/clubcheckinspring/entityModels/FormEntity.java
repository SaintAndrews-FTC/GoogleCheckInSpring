package com.example.clubcheckinspring.entityModels;

public class FormEntity {
     String formData;
     boolean isCheckingIn;

    public String getFormData() {
        return formData;
    }

    public boolean isCheckingIn() {
        return isCheckingIn;
    }

    public void setCheckingIn(boolean isCheckingIn) {
        this.isCheckingIn = isCheckingIn;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
}
