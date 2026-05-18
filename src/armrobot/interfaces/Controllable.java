package armrobot.interfaces;

public interface Controllable {
    void moveTo(int x, int y);
    void drop();
    void returnHome();
    String getStatus();
}
