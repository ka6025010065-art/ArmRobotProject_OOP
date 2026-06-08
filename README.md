# ArmRobot Project OOP (

A Java project built for the Object Oriented Concept (OOC) course. This project simulates an advanced arm robot control system and demonstrates OOP concepts from Week 1 through Week 10.

---

## Project Structure

```
src/com/armrobot/
├── interfaces/
│   ├── Controllable.java
│   ├── Displayable.java
│   ├── ItemSearchable.java
│   └── RobotSearchable.java
├── models/
│   ├── AbstractRobot.java    (Abstract superclass)
│   ├── RobotArm.java         (Subclass of AbstractRobot)
│   ├── MobileRobot.java      (Subclass of AbstractRobot)
│   ├── ObjectItem.java
│   ├── Person.java           (Superclass)
│   ├── User.java             (Subclass of Person)
│   └── RobotTask.java
├── system/
│   └── ArmRobotSystem.java   (System Controller)
└── main/
    └── Main.java
```

---

## OOP Concepts Covered

| Week | Topic | Where Applied |
|------|-------|---------------|
| Week 1 | Class, Object, Attribute, Data Type | All model classes |
| Week 2 | Constructor, Encapsulation, Getter/Setter | ObjectItem, Person, AbstractRobot |
| Week 3 | Static keyword, Collections | ArmRobotSystem (Lists), RobotArm (History) |
| Week 4 | Object Relationships (Composition) | RobotTask uses AbstractRobot |
| Week 5 | Interface | Controllable, Displayable, Searchables |
| Week 6 | Inheritance (Superclass and Subclass) | User extends Person |
| Week 7/8 | Polymorphism | ArmRobotSystem managing different Robots |
| Week 9/10 | Abstract Classes & Exception Handling | AbstractRobot, try-catch in models |

---

## Key Components

### AbstractRobot (Abstract Superclass)
The base class for all robots. Defines the common structure and the abstract `work()` method that all subclasses must implement.

### RobotArm & MobileRobot (Subclasses)
Concrete implementations of robots. 
- `RobotArm`: Fixed-base robot with pick/drop capabilities.
- `MobileRobot`: Capable of movement across different coordinates.

### ArmRobotSystem (The Controller)
The "brain" of the project. It manages the collections of robots, users, and items.
- Provides search functionality via interfaces.
- Coordinates tasks between users and robots.

### Exception Handling
The system uses robust validation to prevent illegal states, such as:
- Moving to negative coordinates.
- Picking items when already holding one.
- Operating robots without a assigned controller.

---

## How to Run

1. Open the project in IntelliJ IDEA, Eclipse, or VS Code.
2. Ensure you have **JDK 11** or higher.
3. Run `Main.java` located in `src/com/armrobot/main/`.

---

## Branch Structure

| Branch | Content |
|--------|---------|
| main | Base implementation |
| Week-9&10-Exception-handling/Abstract-class | Latest features: Abstract classes and Exception Handling |

---

## Course Info

**Course:** Object Oriented Concept (OOC)  
**Project:** Arm Robot Control System (Complete OOP Lifecycle)  
**Weeks Covered:** 1 to 10
