package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.Commands.armState;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm implements Subsystem
{
   /* private Servo leftArm,rightArm;
    public Arm (HardwareMap hardwareMap)
    {
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");

        rightArm.setDirection(Servo.Direction.REVERSE);
        //Maybe do to the servos being on different sides. Change later?
    }

    public void setArmIntaking()
    {
        leftArm.setPosition(0.0);
        rightArm.setPosition(0.0);
        //Will change later.
    }

    public void setArmOutaking()
    {
        leftArm.setPosition(0.0);
        rightArm.setPosition(0.0);
        //Will change later.
    }

    public void setPosition(armState state)
    {
        switch(state) {
            case intaking:
                setArmIntaking();
                break;
            case outaking:
                setArmOutaking();
            default:
                setArmOutaking();
        }
    }*/
}
