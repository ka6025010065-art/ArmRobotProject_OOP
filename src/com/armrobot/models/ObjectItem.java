package com.armrobot.models;

import com.armrobot.interfaces.Displayable;

public class ObjectItem implements Displayable {
    private int itemId;
    private String name;
    private String shape;
    private double weight;
    private boolean picked;
    private static int itemCount = 0;

    public ObjectItem(int itemId, String name, String shape, double weight) {
        setItemId(itemId);
        this.name = cleanText(name, "Unknown Item");
        setShape(shape);
        setWeight(weight);
        this.picked = false;
        itemCount++;
    }

    private String cleanText(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value.trim();
    }

    private void setItemId(int itemId) { this.itemId = (itemId > 0) ? itemId : 0; }
    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public String getShape() { return shape; }
    public double getWeight() { return weight; }
    public boolean isPicked() { return picked; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name.trim();
    }

    public void setShape(String shape) {
        if (shape == null || shape.trim().isEmpty()) {
            this.shape = "Unknown";
            return;
        }
        String trimmed = shape.trim();
        this.shape = (trimmed.equalsIgnoreCase("Cube") || trimmed.equalsIgnoreCase("Sphere")) ? trimmed : "Unknown";
    }

    public void setWeight(double weight) { this.weight = (weight >= 0) ? weight : 0; }
    public void markPicked() { picked = true; }
    public void markDropped() { picked = false; }

    @Override
    public void displayInfo() {
        System.out.printf("Item ID: %d | Name: %s | Shape: %s | Weight: %.2f | Picked: %s%n",
                itemId, name, shape, weight, picked);
    }

    public static int getItemCount() { return itemCount; }
}
