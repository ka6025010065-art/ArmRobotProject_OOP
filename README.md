# ArmRobot Project OOP

A Java project built for the Object Oriented Concept (OOC) course. The project simulates an arm robot control system and demonstrates OOP concepts taught in Weeks 1 to 6.

---

## Project Structure

```
src/
└── armrobot/
    ├── interfaces/
    │   ├── Controllable.java
    │   └── Displayable.java
    ├── model/
    │   ├── ObjectItem.java       (superclass)
    │   ├── Cube.java             (subclass of ObjectItem)
    │   ├── Sphere.java           (subclass of ObjectItem)
    │   ├── User.java             (superclass)
    │   ├── Engineer.java         (subclass of User)
    │   ├── Manager.java          (subclass of User)
    │   └── RobotArm.java
    └── main/
        └── Main.java
```

---

## OOP Concepts Covered

| Week | Topic | Where Applied |
|------|-------|---------------|
| Week 1 | Class, Object, Attribute, Data Type | All model classes |
| Week 2 | Constructor, Encapsulation, Getter, Setter | ObjectItem, User, RobotArm |
| Week 3 | Static keyword, Collections (ArrayList, Set, Map) | RobotArm, ObjectItem, User |
| Week 4 | Object Relationships (Association, Composition) | RobotArm uses User and ObjectItem |
| Week 5 | Interface | Displayable, Controllable |
| Week 6 | Inheritance (Superclass and Subclass) | User, Engineer, Manager, ObjectItem, Cube, Sphere |

---

## Classes

### ObjectItem (Superclass)
Represents a physical object the robot can interact with.
- Shapes allowed: `Cube` or `Sphere`
- Tracks total number of items created using a static counter
- Subclasses: `Cube`, `Sphere`

### Cube (Subclass of ObjectItem)
Inherits id, name, shape from ObjectItem.
- Adds `stack()` method

### Sphere (Subclass of ObjectItem)
Inherits id, name, shape from ObjectItem.
- Adds `roll()` method

### User (Superclass)
Represents a person who controls the robot.
- Tracks total number of users created using a static counter
- Subclasses: `Engineer`, `Manager`

### Engineer (Subclass of User)
Inherits id, name, role from User.
- Adds `operateRobot()` method

### Manager (Subclass of User)
Inherits id, name, role from User.
- Adds `supervise()` method

### RobotArm
The main robot class. It can move, pick, and drop objects.
- Can only pick `Cube` shaped items
- Tracks move history using `ArrayList`
- Tracks unique positions using `Set`
- Counts position visits using `Map`

### Controllable (Interface)
Defines required behavior for any controllable robot.
- `moveTo(int x, int y)`
- `drop()`
- `returnHome()`
- `getStatus()`

### Displayable (Interface)
Defines required behavior for any class that can display its information.
- `displayInfo()`

---

## How to Run

1. Open the project in IntelliJ IDEA or VS Code with Java extension
2. Make sure JDK 11 or higher is installed
3. Run `Main.java` inside `src/armrobot/main/`

---

## Sample Output

```
ARM ROBOT CONTROL SYSTEM -- WEEKS 1 TO 6

ObjectItem [id=1, name=CubeA, shape=Cube]
ObjectItem [id=2, name=CubeB, shape=Cube]
ObjectItem [id=3, name=SphereX, shape=Sphere]

User [id=1, name=Dara, role=Engineer]

ARM-01 -> Moved to (3,5)
  PICK SUCCESS: CubeA (Cube) picked up.

Engineer [id=1, name=Dara, role=Engineer]
Dara is operating the robot arm.

Manager [id=3, name=Bopha, role=Manager]
Bopha is supervising the robot operation.

Cube [id=1, name=CubeA, shape=Cube]
CubeA can be stacked on other cubes.

Sphere [id=3, name=SphereX, shape=Sphere]
SphereX can roll across the surface.
```

---

## Branch Structure

| Branch | Content |
|--------|---------|
| main | Weeks 1 to 5 |
| WEEK-6-Inheritance | Week 6 adds inheritance |

---

## Course Info

**Course:** Object Oriented Concept (OOC)  
**Sample Project:** Arm Robot Control System  
**Weeks Covered:** 1 to 6