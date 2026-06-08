package com.armrobot.system;

import com.armrobot.interfaces.Displayable;
import com.armrobot.interfaces.ItemSearchable;
import com.armrobot.interfaces.RobotSearchable;
import com.armrobot.models.*;
import java.util.ArrayList;

public class ArmRobotSystem implements Displayable, RobotSearchable, ItemSearchable {

    private String systemName;
    private ArrayList<User> users;
    private ArrayList<RobotArm> robots;
    private ArrayList<ObjectItem> items;
    private ArrayList<RobotTask> tasks;

    public ArmRobotSystem(String systemName) {
        this.systemName = (systemName == null || systemName.trim().isEmpty())
                ? "Arm Robot Control System" : systemName.trim();
        this.users = new ArrayList<>();
        this.robots = new ArrayList<>();
        this.items = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public boolean addUser(User user) { return user != null && users.add(user); }
    public boolean addRobot(RobotArm robot) { return robot != null && robots.add(robot); }
    public boolean addItem(ObjectItem item) { return item != null && items.add(item); }

    public User searchUserById(int userId) {
        for (User u : users) if (u.getUserId() == userId) return u;
        return null;
    }

    @Override
    public RobotArm searchRobotById(int robotId) {
        for (RobotArm r : robots) if (r.getRobotId() == robotId) return r;
        return null;
    }

    @Override
    public ObjectItem searchItemById(int itemId) {
        for (ObjectItem i : items) if (i.getItemId() == itemId) return i;
        return null;
    }

    // Week 10: Exception handling when parsing ID strings from user input
    public User searchUserByIdString(String input) {
        try {
            int userId = Integer.parseInt(input);
            return searchUserById(userId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid user ID format: \"" + input + "\". Please enter a number.");
            return null;
        }
    }

    public RobotArm searchRobotByIdString(String input) {
        try {
            int robotId = Integer.parseInt(input);
            return searchRobotById(robotId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid robot ID format: \"" + input + "\". Please enter a number.");
            return null;
        }
    }

    public ObjectItem searchItemByIdString(String input) {
        try {
            int itemId = Integer.parseInt(input);
            return searchItemById(itemId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid item ID format: \"" + input + "\". Please enter a number.");
            return null;
        }
    }

    public boolean assignUserToRobot(int userId, int robotId) {
        User user = searchUserById(userId);
        RobotArm robot = searchRobotById(robotId);
        if (user == null || robot == null) {
            System.out.println("Assignment failed: User or Robot not found.");
            return false;
        }
        robot.assignController(user);
        System.out.println(user.getName() + " is now controlling " + robot.getRobotName() + ".");
        return true;
    }

    public RobotTask createTask(int taskId, int robotId, int itemId, String taskName) {
        RobotArm robot = searchRobotById(robotId);
        ObjectItem item = searchItemById(itemId);
        if (robot == null || item == null) {
            System.out.println("Cannot create task: Robot or Item not found.");
            return null;
        }
        RobotTask task = new RobotTask(taskId, robot, item, taskName);
        tasks.add(task);
        return task;
    }

    public void displayAllRobots() {
        System.out.println("\n========== Robot List ==========");
        if (robots.isEmpty()) System.out.println("No robots available.");
        else for (RobotArm r : robots) r.displayInfo();
        System.out.println("===============================");
    }

    public void displayAllItems() {
        System.out.println("\n========== Item List ==========");
        if (items.isEmpty()) System.out.println("No items available.");
        else for (ObjectItem i : items) i.displayInfo();
        System.out.println("==============================");
    }

    public void displayAllTasks() {
        System.out.println("\n========== Task List ==========");
        if (tasks.isEmpty()) System.out.println("No tasks created yet.");
        else for (RobotTask t : tasks) t.displayInfo();
        System.out.println("==============================");
    }

    public int getUserListSize() { return users.size(); }
    public int getRobotListSize() { return robots.size(); }
    public int getItemListSize() { return items.size(); }
    public int getTaskListSize() { return tasks.size(); }

    @Override
    public void displayInfo() {
        System.out.println("\n========== Arm Robot System Summary ==========");
        System.out.println("System Name: " + systemName);
        System.out.println("Users: " + users.size());
        System.out.println("Robots: " + robots.size());
        System.out.println("Items: " + items.size());
        System.out.println("Tasks: " + tasks.size());
        System.out.println("==============================================");
    }
}
