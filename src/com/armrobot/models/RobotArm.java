package com.armrobot.models;

import com.armrobot.interfaces.Controllable;
import com.armrobot.interfaces.Displayable;

public class RobotArm implements Displayable, Controllable {
    private int robotId;
    private String robotName;
    private int posX;
    private int posY;
    private User controller;
    private ObjectItem heldItem;
    private String status;
    private static int robotCount = 0;

    public RobotArm(int robotId, String robotName) {
        setRobotId(robotId);
        this.robotName = cleanText(robotName, "Unnamed Robot");
        this.posX = 0;
        this.posY = 0;
        this.controller = null;
        this.heldItem = null;
        this.status = "Idle";
        robotCount++;
    }

    private String cleanText(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value.trim();
    }

    private void setRobotId(int robotId) { this.robotId = (robotId > 0) ? robotId : 0; }
    public int getRobotId() { return robotId; }
    public String getRobotName() { return robotName; }
    public User getController() { return controller; }
    public ObjectItem getHeldItem() { return heldItem; }
    public void assignController(User controller) { this.controller = controller; }

    @Override
    public void moveTo(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.status = "Moving";
        System.out.println(robotName + " moved to position (" + x + ", " + y + ").");
    }

    @Override
    public boolean pick(ObjectItem item) {
        if (controller == null) {
            System.out.println("Cannot pick item. No user is controlling the robot.");
            return false;
        }
        if (item == null) {
            System.out.println("Cannot pick null item.");
            return false;
        }
        if (heldItem != null) {
            System.out.println(robotName + " is already holding " + heldItem.getName() + ".");
            return false;
        }
        if (item.isPicked()) {
            System.out.println(item.getName() + " is already picked.");
            return false;
        }
        if (!"Cube".equalsIgnoreCase(item.getShape())) {
            System.out.println(robotName + " can only pick cube-shaped objects in this version.");
            return false;
        }
        heldItem = item;
        heldItem.markPicked();
        status = "Holding Item";
        System.out.println(robotName + " picked " + item.getName() + ".");
        return true;
    }

    @Override
    public boolean drop() {
        if (heldItem == null) {
            System.out.println(robotName + " is not holding any item.");
            return false;
        }
        System.out.println(robotName + " dropped " + heldItem.getName() + ".");
        heldItem.markDropped();
        heldItem = null;
        status = "Idle";
        return true;
    }

    @Override
    public void returnHome() {
        posX = 0;
        posY = 0;
        status = "Home";
        System.out.println(robotName + " returned home.");
    }

    @Override
    public String getStatus() {
        String controllerName = (controller != null) ? controller.getName() : "No Controller";
        String itemName = (heldItem != null) ? heldItem.getName() : "No Item";
        return String.format("Robot: %s | Position: (%d, %d) | Controller: %s | Holding: %s | Status: %s",
                robotName, posX, posY, controllerName, itemName, status);
    }

    @Override
    public void displayInfo() {
        System.out.println(getStatus());
    }

    public static int getRobotCount() { return robotCount; }
}
