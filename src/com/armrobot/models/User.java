package com.armrobot.models;

public class User extends Person {
    private String role;
    private static int userCount = 0;

    public User(int userId, String name, String phone, String role) {
        super(userId, name, phone);
        setRole(role);
        userCount++;
    }

    public int getUserId() { return id; }
    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = (role == null || role.trim().isEmpty()) ? "Operator" : role.trim();
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + role);
    }

    public static int getUserCount() { return userCount; }
}
