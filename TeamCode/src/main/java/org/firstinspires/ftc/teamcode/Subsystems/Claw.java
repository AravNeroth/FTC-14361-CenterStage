package org.firstinspires.ftc.teamcode.Subsystems;
import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw implements Subsystem
{
    private Servo leftHand, rightHand, wrist;

    public Claw(HardwareMap hardwareMap) {
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand = hardwareMap.servo.get("rightHand");
        wrist = hardwareMap.servo.get("wrist");

    }

    public void inWristRotation()
    {
        wrist.setPosition(RobotConstants.Claw.inWristRotation);
    }

    public void outWristRotation()
    {

    }

    public void leftOpen() {
        leftHand.setPosition(RobotConstants.Claw.deposit);
        // Will change variables later
    }

    public void rightOpen() {
        rightHand.setPosition(RobotConstants.Claw.deposit);
        // Will change variables later
    }

    public void open()
    {
        leftHand.setPosition(RobotConstants.Claw.deposit);
        rightHand.setPosition(RobotConstants.Claw.deposit);
        // Will change variables later
    }

    public void leftClose()
    {
        leftHand.setPosition(RobotConstants.Claw.close);
        // Will change variables later
    }

    public void rightClose()
    {
        rightHand.setPosition(RobotConstants.Claw.close);
        // Will change variables later
    }

    public void close()
    {
        leftHand.setPosition(RobotConstants.Claw.close);
        rightHand.setPosition(RobotConstants.Claw.close);
        // Will change variables later
    }

    public void setPosition(State state)
    {
        switch(state) {
            case HIGHIN:
            case MEDIUMIN:
            case LOWIN:
            case HIGHOUT:
            case MEDIUMOUT:
            case LOWOUT:
            default:
                close();
        }
    }

    public double getLeftHandPosition()
    {
        return leftHand.getPosition();
    }

    public double getRightHandPosition()
    {
        return rightHand.getPosition();
    }

    public void setWristOutPosition()
    {
        wrist.setPosition(RobotConstants.Claw.outWristRotation);
    }

    public void setWristSidePosition()
    {
        wrist.setPosition(RobotConstants.Claw.sideWristRotation);
    }

    public double getWristPosition()
    {
        return wrist.getPosition();
    }

}