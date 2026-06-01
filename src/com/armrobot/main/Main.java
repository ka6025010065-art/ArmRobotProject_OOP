package com.armrobot.main;

import com.armrobot.interfaces.Displayable;
import com.armrobot.models.*;
import com.armrobot.system.ArmRobotSystem;

public class Main {
    public static void main(String[] args) {
        ArmRobotSystem system = new ArmRobotSystem("CAM Arm Robot System");

        User user1 = new User(1, "Dara", "012345678", "Operator");
        User user2 = new User(2, "Sokha", "098765432", "Technician");
        system.addUser(user1);
        system.addUser(user2);

        RobotArm robot1 = new RobotArm(1, "Robot Arm A");
        RobotArm robot2 = new RobotArm(2, "Robot Arm B");
        system.addRobot(robot1);
        system.addRobot(robot2);

        ObjectItem item1 = new ObjectItem(101, "Small Box", "Cube", 2.5);
        ObjectItem item2 = new ObjectItem(102, "Ball", "Sphere", 1.2);
        ObjectItem item3 = new ObjectItem(103, "Package", "Cube", 3.0);
        system.addItem(item1);
        system.addItem(item2);
        system.addItem(item3);

        System.out.println("Before task:");
        system.displayAllRobots();
        system.displayAllItems();

        RobotArm wrongRobot = system.searchRobotById(99);
        if (wrongRobot == null) System.out.println("\nInvalid robot selection: robot 99 does not exist.");

        system.assignUserToRobot(1, 1);
        robot1.moveTo(3, 4);

        RobotTask task1 = system.createTask(1, 1, 101, "Pick and move small box");
        if (task1 != null) {
            task1.startTask();
            robot1.moveTo(5, 6);
            task1.completeTask();
            robot1.returnHome();
        }

        RobotTask task2 = system.createTask(2, 1, 102, "Try to pick sphere object");
        if (task2 != null) task2.startTask();

        System.out.println("\nAfter task:");
        system.displayAllRobots();
        system.displayAllItems();
        system.displayAllTasks();
        system.displayInfo();

        System.out.println("\nStatic counters vs collection size:");
        System.out.println("User.getUserCount(): " + User.getUserCount());
        System.out.println("system.getUserListSize(): " + system.getUserListSize());
        System.out.println("RobotArm.getRobotCount(): " + RobotArm.getRobotCount());
        System.out.println("system.getRobotListSize(): " + system.getRobotListSize());
        System.out.println("ObjectItem.getItemCount(): " + ObjectItem.getItemCount());
        System.out.println("system.getItemListSize(): " + system.getItemListSize());
        System.out.println("RobotTask.getTaskCount(): " + RobotTask.getTaskCount());
        System.out.println("system.getTaskListSize(): " + system.getTaskListSize());

        System.out.println("\n=== Polymorphism Demo ===");
        Displayable[] displayables = { user1, robot1, item1, system };
        for (Displayable d : displayables) {
            d.displayInfo();
            System.out.println("---------------------------");
        }
    }
}
