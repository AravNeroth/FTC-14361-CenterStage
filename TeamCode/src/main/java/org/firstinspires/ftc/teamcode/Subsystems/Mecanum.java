package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.bosch.BNO055IMU;

public class Mecanum implements Subsystem
{
    private DcMotorEx leftFront, leftRear, rightFront, rightRear;
    private double leftFrontPower, leftRearPower, rightFrontPower, rightRearPower, rotY, rotX, rx, x, y, denominator;
    private double offset = 1.1;
    private double slowOffset = 0.5;
    private RevIMU imu;

    public Mecanum (HardwareMap hardwareMap)
    {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        leftRear.setDirection(DcMotorEx.Direction.REVERSE);
        leftFront.setDirection(DcMotorEx.Direction.REVERSE);

        // Retrieve the IMU from the hardware map
        imu = (RevIMU) hardwareMap.get(BNO055IMU.class, "imu");
        // Makes a new object titled 'parameters' usd to hold the angle of the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // Without this, data retrieving from the IMU throws an exception
        imu.init();
    }

    public void resetIMU()
    {
        imu.reset();
    }

    public void drive (GamepadEx gamepad1)
    {
        y = -gamepad1.getLeftY();
        x = gamepad1.getLeftX();
        rx = gamepad1.getRightX();;

        double botHeading = Math.toRadians(-imu.getHeading()+180);

        rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        leftFrontPower = (rotY + rotX + rx) / denominator;
        leftRearPower = (rotY - rotX + rx) / denominator;
        rightFrontPower = (rotY - rotX - rx) / denominator;
        rightRearPower = (rotY + rotX - rx) / denominator;
    }

    public void setMotorPower(double leftFrontPower, double leftRearPower, double rightFrontPower, double rightRearPower)
    {
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }

    public void setMotorSlowDownPower()
    {
        leftFront.setPower(leftFrontPower * slowOffset);
        leftRear.setPower(leftRearPower * slowOffset);
        rightFront.setPower(rightFrontPower * slowOffset);
        rightRear.setPower(rightRearPower * slowOffset);
    }
}
