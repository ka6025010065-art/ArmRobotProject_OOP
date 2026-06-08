package com.armrobot.models;

// Week 9: RobotArm extends AbstractRobot (abstract class)
// Week 8: RobotArm is a concrete subclass; polymorphism works via AbstractRobot references
public class RobotArm extends AbstractRobot {

    private User controller;
    private ObjectItem heldItem;

    public RobotArm(int robotId, String robotName) {
        super(robotId, robotName);
        this.controller = null;
        this.heldItem = null;
    }

    public User getController() { return controller; }
    public ObjectItem getHeldItem() { return heldItem; }
    public void assignController(User controller) { this.controller = controller; }

    // Week 9: Implements abstract method from AbstractRobot
    @Override
    public void work() {
        System.out.println(robotName + " is performing pick-and-place operations.");
    }

    @Override
    public void moveTo(int x, int y) {
        // Week 10: Exception handling for invalid coordinates
        try {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("Coordinates cannot be negative.");
            }
            this.posX = x;
            this.posY = y;
            this.status = "Moving";
            System.out.println(robotName + " moved to position (" + x + ", " + y + ").");
        } catch (IllegalArgumentException e) {
            System.out.println("Move error for " + robotName + ": " + e.getMessage());
        }
    }

    @Override
    public boolean pick(ObjectItem item) {
        // Week 10: Exception handling for invalid pick operations
        try {
            if (controller == null) {
                throw new IllegalStateException("No user is controlling this robot.");
            }
            if (item == null) {
                throw new IllegalArgumentException("Item cannot be null.");
            }
            if (heldItem != null) {
                throw new IllegalStateException(robotName + " is already holding " + heldItem.getName() + ".");
            }
            if (item.isPicked()) {
                throw new IllegalStateException(item.getName() + " is already picked by another robot.");
            }
            if (!"Cube".equalsIgnoreCase(item.getShape())) {
                throw new IllegalArgumentException(robotName + " can only pick cube-shaped objects in this version.");
            }
            heldItem = item;
            heldItem.markPicked();
            status = "Holding Item";
            System.out.println(robotName + " picked " + item.getName() + ".");
            return true;

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Pick error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean drop() {
        // Week 10: Exception handling for invalid drop operations
        try {
            if (heldItem == null) {
                throw new IllegalStateException(robotName + " is not holding any item.");
            }
            System.out.println(robotName + " dropped " + heldItem.getName() + ".");
            heldItem.markDropped();
            heldItem = null;
            status = "Idle";
            return true;

        } catch (IllegalStateException e) {
            System.out.println("Drop error: " + e.getMessage());
            return false;
        }
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
}
