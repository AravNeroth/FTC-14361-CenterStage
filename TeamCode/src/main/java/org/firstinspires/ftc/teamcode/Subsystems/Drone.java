package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class Drone
{
    private Servo droneServo;
    public Drone(HardwareMap hardwareMap)
    {
        droneServo = hardwareMap.get(Servo.class, "droneMotor");
    }

    public void launch()
    {
        droneServo.setPosition(RobotConstants.Drone.launchPosition);
    }

    public void load()
    {
        droneServo.setPosition(RobotConstants.Drone.loadedPosition);
    }
}
