package com.armrobot.interfaces;

import com.armrobot.models.RobotArm;

public interface RobotSearchable {
    RobotArm searchRobotById(int robotId);
}
