package com.armrobot.models;

import com.armrobot.interfaces.Controllable;
import com.armrobot.interfaces.Displayable;

// Week 9: Abstract class
// AbstractRobot is too general to create directly.
// All robot types must extend this and implement work().
public abstract class AbstractRobot implements Displayable, Controllable {

    protected int robotId;
    protected String robotName;
    protected int posX;
    protected int posY;
    protected String status;
    protected static int robotCount = 0;

    public AbstractRobot(int robotId, String robotName) {
        this.robotId = (robotId > 0) ? robotId : 0;
        this.robotName = (robotName == null || robotName.trim().isEmpty()) ? "Unnamed Robot" : robotName.trim();
        this.posX = 0;
        this.posY = 0;
        this.status = "Idle";
        robotCount++;
    }

    public int getRobotId() { return robotId; }
    public String getRobotName() { return robotName; }
    public String getCurrentStatus() { return status; }

    // Abstract method: every robot subclass must define what it does as work
    public abstract void work();

    // Shared normal method: all robots can display their base info
    public void displayBaseInfo() {
        System.out.println("Robot ID: " + robotId);
        System.out.println("Robot Name: " + robotName);
        System.out.println("Position: (" + posX + ", " + posY + ")");
        System.out.println("Status: " + status);
    }

    public static int getRobotCount() { return robotCount; }
}
