package com.armrobot.models;

import com.armrobot.interfaces.Displayable;

public class RobotTask implements Displayable {
    private int taskId;
    private RobotArm robot;
    private ObjectItem item;
    private String taskName;
    private String taskStatus;
    private static int taskCount = 0;

    public RobotTask(int taskId, RobotArm robot, ObjectItem item, String taskName) {
        setTaskId(taskId);
        this.robot = robot;
        this.item = item;
        this.taskName = cleanText(taskName, "No Task Name");
        this.taskStatus = "Pending";
        taskCount++;
    }

    private String cleanText(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value.trim();
    }

    private void setTaskId(int taskId) { this.taskId = (taskId > 0) ? taskId : 0; }
    public int getTaskId() { return taskId; }
    public RobotArm getRobot() { return robot; }
    public ObjectItem getItem() { return item; }
    public String getTaskName() { return taskName; }
    public String getTaskStatus() { return taskStatus; }

    public boolean startTask() {
        if (robot == null || item == null) {
            System.out.println("Cannot start task without robot or item.");
            taskStatus = "Failed";
            return false;
        }
        boolean picked = robot.pick(item);
        if (picked) {
            taskStatus = "In Progress";
            return true;
        }
        taskStatus = "Failed";
        return false;
    }

    public boolean completeTask() {
        if (robot == null) {
            System.out.println("Cannot complete task without robot.");
            taskStatus = "Failed";
            return false;
        }
        boolean dropped = robot.drop();
        if (dropped) {
            taskStatus = "Completed";
            return true;
        }
        taskStatus = "Failed";
        return false;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n========== Robot Task Detail ==========");
        System.out.println("Task ID: " + taskId);
        System.out.println("Task Name: " + taskName);
        if (robot != null) System.out.println("Robot: " + robot.getRobotName());
        if (item != null) System.out.println("Item: " + item.getName());
        System.out.println("Task Status: " + taskStatus);
        System.out.println("=======================================");
    }

    public static int getTaskCount() { return taskCount; }
}
