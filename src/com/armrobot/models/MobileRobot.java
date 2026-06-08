package com.armrobot.models;

// Week 8: Polymorphism demo
// MobileRobot is a second concrete subclass of AbstractRobot.
// Both RobotArm and MobileRobot can be stored as AbstractRobot references.
// Calling work() on each gives different output depending on the real object type.
public class MobileRobot extends AbstractRobot {

    private String zone;

    public MobileRobot(int robotId, String robotName, String zone) {
        super(robotId, robotName);
        this.zone = (zone == null || zone.trim().isEmpty()) ? "Unassigned" : zone.trim();
    }

    public String getZone() { return zone; }

    // Week 9: Implements the abstract method differently from RobotArm
    @Override
    public void work() {
        System.out.println(robotName + " is transporting items across zone: " + zone + ".");
    }

    @Override
    public void moveTo(int x, int y) {
        try {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Coordinates cannot be negative.");
            }
            this.posX = x;
            this.posY = y;
            this.status = "Moving";
            System.out.println(robotName + " (Mobile) moved to (" + x + ", " + y + ") in zone " + zone + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Move error for " + robotName + ": " + e.getMessage());
        }
    }

    @Override
    public boolean pick(ObjectItem item) {
        System.out.println(robotName + " (MobileRobot) does not support pick operations.");
        return false;
    }

    @Override
    public boolean drop() {
        System.out.println(robotName + " (MobileRobot) does not support drop operations.");
        return false;
    }

    @Override
    public void returnHome() {
        posX = 0;
        posY = 0;
        status = "Home";
        System.out.println(robotName + " returned to home zone: " + zone + ".");
    }

    @Override
    public String getStatus() {
        return String.format("MobileRobot: %s | Zone: %s | Position: (%d, %d) | Status: %s",
                robotName, zone, posX, posY, status);
    }

    @Override
    public void displayInfo() {
        System.out.println(getStatus());
    }
}
