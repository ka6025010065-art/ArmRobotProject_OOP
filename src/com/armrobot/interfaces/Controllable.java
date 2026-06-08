package com.armrobot.interfaces;

import com.armrobot.models.ObjectItem;

public interface Controllable {
    void moveTo(int x, int y);
    boolean pick(ObjectItem item);
    boolean drop();
    void returnHome();
    String getStatus();
}
