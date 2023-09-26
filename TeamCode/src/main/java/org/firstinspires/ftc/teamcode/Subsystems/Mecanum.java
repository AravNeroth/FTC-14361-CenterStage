package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Mecanum{
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    double y, x, rx, rotX, rotY,denominator, max,botHeading, offset = 1.1, leftFrontPower, leftRearPower, rightRearPower, rightFrontPower, speed;

    BNO055IMU imu;
    BNO055IMU.Parameters parameters;

    public Mecanum(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        // Declare our motors
        // Make sure your ID's match your configuration


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

      //  leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      // rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftRear.setDirection(DcMotorEx.Direction.REVERSE);


        imu = hardwareMap.get(BNO055IMU.class, "cIMU");
        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);


    }




    public void fieldCentric(GamepadEx gamepad1) {


        y = gamepad1.getLeftY() * offset;
        x = gamepad1.getLeftX() * offset;
        rx = gamepad1.getRightX();

        botHeading = -imu.getAngularOrientation().firstAngle;

        rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);


        denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);


        leftFrontPower = (1 * (rotY + rotX + rx)) / denominator;
        leftRearPower = (1 * (rotY - rotX + rx)) / denominator;
        rightFrontPower = (1 * (rotY - rotX - rx)) / denominator;
        rightRearPower = (1 * (rotY + rotX - rx)) / denominator;


    }
    public void powerMotors(){
        leftFront.setPower(leftFrontPower );
        leftRear.setPower(leftRearPower * offset);
        rightFront.setPower(rightFrontPower );
        rightRear.setPower(rightRearPower * offset);
    }
    public void slowMotors(int offset){
        leftFront.setPower(leftFrontPower * offset);
        leftRear.setPower(leftRearPower * offset);
        rightFront.setPower(rightFrontPower * offset);
        rightRear.setPower(rightRearPower * offset);
    }

    public void resetIMU(){

        imu.initialize(parameters);

    }


}
