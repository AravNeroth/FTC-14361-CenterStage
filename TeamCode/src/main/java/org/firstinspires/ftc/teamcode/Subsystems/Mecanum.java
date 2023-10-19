package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import static java.lang.Math.ceil;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.bosch.BNO055IMU;

import java.text.DecimalFormat;

public class Mecanum implements Subsystem
{
    private DcMotorEx leftFront, leftRear, rightFront, rightRear;
    private double leftFrontPower, leftRearPower, rightFrontPower, rightRearPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;
    private double slowOffset = 0.5;
    DecimalFormat df = new DecimalFormat("#.##"); // This rounds to two decimal places


    RevIMU imu;


    public Mecanum (HardwareMap hardwareMap)
    {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);

        imu = new RevIMU(hardwareMap);
        imu.init();
        
        // this is making a new object called 'parameters' that we use to hold the angle the imu is at
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // Technically this is the default, however specifying it is clearer
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // Without this, data retrieving from the IMU throws an
        // Without this, data retrieving from the IMU throws an exception
        imu.init();
    }

    public void resetIMU()
    {
        imu.reset();
    }

    public void drive (GamepadEx gamepad1)
    {
        y = gamepad1.getLeftY();
        x = gamepad1.getLeftX();
        rx = gamepad1.getRightX();;

        double botHeading = Math.toRadians(imu.getHeading()+180);

        rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        leftFrontPower = (rotY + rotX + rx) / denominator;
        leftRearPower = (rotY - rotX + rx) / denominator;
        rightFrontPower = (rotY - rotX - rx) / denominator;
        rightRearPower = (rotY + rotX - rx) / denominator;
    }

    public void setMotorPower()
    {


        leftFront.setPower(leftFrontPower * offset + .1);
        leftRear.setPower(leftRearPower * offset + .1);
        rightFront.setPower(rightFrontPower * offset + .1);
        rightRear.setPower(rightRearPower * offset + .1);
    }

    public void setMotorSlowDownPower()
    {
        leftFront.setPower(leftFrontPower * slowOffset);
        leftRear.setPower(leftRearPower * slowOffset);
        rightFront.setPower(rightFrontPower * slowOffset);
        rightRear.setPower(rightRearPower * slowOffset);
    }

    public double round(double num) {
        String LFR = df.format(leftFrontPower);
        String LRP = df.format(leftRearPower);
        String RFP = df.format(rightFrontPower);
        String RRP = df.format(rightRearPower);

        double LFRvalue = Double.parseDouble(LFR);
        double LRPvalue = Double.parseDouble(LRP);
        double RFPvalue = Double.parseDouble(RFP);
        double RRPvalue = Double.parseDouble(RRP);



    }
}
