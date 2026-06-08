package com.armrobot.main;

import com.armrobot.interfaces.Displayable;
import com.armrobot.models.*;
import com.armrobot.system.ArmRobotSystem;

public class Main {
    public static void main(String[] args) {

        ArmRobotSystem system = new ArmRobotSystem("CAM Arm Robot System");

        // --- Setup ---
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

        // --- Original task flow ---
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
        System.out.println("RobotArm.getRobotCount(): " + AbstractRobot.getRobotCount());
        System.out.println("system.getRobotListSize(): " + system.getRobotListSize());
        System.out.println("ObjectItem.getItemCount(): " + ObjectItem.getItemCount());
        System.out.println("system.getItemListSize(): " + system.getItemListSize());
        System.out.println("RobotTask.getTaskCount(): " + RobotTask.getTaskCount());
        System.out.println("system.getTaskListSize(): " + system.getTaskListSize());

        // ==========================================================
        // Week 8: POLYMORPHISM DEMO
        // AbstractRobot reference holds both RobotArm and MobileRobot.
        // work() produces different output for each real object type.
        // ==========================================================
        System.out.println("\n=== Week 8: Polymorphism Demo ===");

        MobileRobot mobile1 = new MobileRobot(3, "Mobile Unit A", "Warehouse Zone B");

        AbstractRobot[] allRobots = { robot1, robot2, mobile1 };

        for (AbstractRobot r : allRobots) {
            r.work();   // same call, different behavior per subclass
        }

        // Displayable polymorphism across different classes
        System.out.println("\n=== Polymorphism via Displayable interface ===");
        Displayable[] displayables = { user1, robot1, item1, system };
        for (Displayable d : displayables) {
            d.displayInfo();
            System.out.println("---------------------------");
        }

        // ==========================================================
        // Week 9: ABSTRACT CLASS DEMO
        // AbstractRobot cannot be instantiated directly.
        // All subclasses must implement work().
        // displayBaseInfo() is a shared normal method.
        // ==========================================================
        System.out.println("\n=== Week 9: Abstract Class Demo ===");
        System.out.println("robot1.displayBaseInfo():");
        robot1.displayBaseInfo();
        System.out.println("\nmobile1.displayBaseInfo():");
        mobile1.displayBaseInfo();

        // ==========================================================
        // Week 10: EXCEPTION HANDLING DEMO
        // ==========================================================
        System.out.println("\n=== Week 10: Exception Handling Demo ===");

        // NumberFormatException: invalid ID string
        System.out.println("Searching robot by invalid string input 'abc':");
        system.searchRobotByIdString("abc");

        // IllegalArgumentException: negative coordinates
        System.out.println("\nMoving robot to negative coordinates:");
        robot1.moveTo(-5, 3);

        // IllegalStateException: drop when holding nothing
        System.out.println("\nDropping when robot is not holding anything:");
        robot2.drop();

        // IllegalStateException: picking without a controller
        System.out.println("\nPicking without a controller assigned:");
        robot2.pick(item3);

        // ArithmeticException example (general)
        System.out.println("\nArithmeticException example:");
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        } finally {
            System.out.println("Finally block always runs.");
        }
    }
}
