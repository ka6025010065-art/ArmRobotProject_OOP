package com.armrobot.models;

import com.armrobot.interfaces.Displayable;

public class Person implements Displayable {
    protected int id;
    protected String name;
    protected String phone;

    public Person(int id, String name, String phone) {
        setId(id);
        this.name = cleanText(name, "Unknown Name");
        this.phone = cleanText(phone, "No Phone");
    }

    private String cleanText(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value.trim();
    }

    private void setId(int id) {
        this.id = (id > 0) ? id : 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name.trim();
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) this.phone = phone.trim();
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }
}
