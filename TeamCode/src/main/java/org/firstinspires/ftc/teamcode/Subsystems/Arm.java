package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.Commands.ArmState;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm implements Subsystem
{
    private Servo leftArm,rightArm;
    public Arm (HardwareMap hardwareMap)
    {
        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
        rightArm.setDirection(Servo.Direction.REVERSE);
        //Maybe do to the servos being on different sides. Change later?
    }

    public void setArmIntaking()
    {
        leftArm.setPosition(RobotConstants.Arm.intake);
        rightArm.setPosition(RobotConstants.Arm.intake);
        //Will change later.
    }

    public void setArmOuttaking()
    {
        leftArm.setPosition(RobotConstants.Arm.outtake);
        rightArm.setPosition(RobotConstants.Arm.outtake);
        //Will change later.
    }

    public void setPosition(ArmState armState)
    {
        switch(armState)
        {
            case INTAKING:

            default:
                setArmIntaking();
        }
    }

    public double getLeftArmPosition()
    {
        return leftArm.getPosition();
    }

    public double getRightArmPosition()
    {
        return rightArm.getPosition();
    }

    public double getArmPosition()
    {
        return (leftArm.getPosition() + rightArm.getPosition())/2;
    }
}
