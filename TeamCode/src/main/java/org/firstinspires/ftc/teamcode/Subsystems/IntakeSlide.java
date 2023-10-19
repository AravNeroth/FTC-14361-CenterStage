package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.arcrobotics.ftclib.controller.PIDController;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Utilities.robotConstants;

public class IntakeSlide
{
    private PIDController intakePID;

    DcMotor intakeSlideMotor;
    private double  outtakeMotors;
    private double P, I, D;
    private final int countsPerRev = 384;

    double power = .7;

    intakeSlidesState intakeSlidesState;
    public IntakeSlide(HardwareMap hardwareMap){

        intakeSlideMotor = hardwareMap.get(DcMotor.class, "intakeSlideMotor");

        intakeSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Stop and reset encoders doesnt work?
        //intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        P = robotConstants.IntakeSlide.P;
        I = robotConstants.IntakeSlide.I;
        D = robotConstants.IntakeSlide.D;


        // Will change depending on the target.

        // Will change depending on the motor used.

        intakePID = new PIDController(P, I, D);


    }



    public void setPIDMotorPower(double target)
    {
        intakePID.setPID(P,I,D);
        int motorPosition = intakeSlideMotor.getCurrentPosition();

        double PID = intakePID.calculate(motorPosition, target);
       // double ff = Math.cos(Math.toRadians(target/ticks_in_degrees)) * f;

      //  double power = PID + ff;

        intakeSlideMotor.setPower(power);
    }


    public void setPosition(extensionState extensionState,intakeSlidesState inExtendState)
    {
        switch(inExtendState){
            case retracted:
                intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.retracted);

                break;
            case extending:
                switch(extensionState){
                    case HIGHIN:
                        intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.fullExtension);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        intakeSlideMotor.setPower(power);
                       // setPIDMotorPower(robotConstants.IntakeSlide.fullExtension);

                        inExtendState = inExtendState.extended;
                        break;
                    case MEDIUMIN:
                       intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.mediumExtension);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                        intakeSlideMotor.setPower(power);

                       //setPIDMotorPower(robotConstants.IntakeSlide.mediumExtension);
                        inExtendState = inExtendState.extended;

                        break;
                    case STATION:


                       intakeSlideMotor.setTargetPosition(robotConstants.IntakeSlide.retracted);
                        intakeSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                        intakeSlideMotor.setPower(power);
                        //PIDMotorPower(robotConstants.IntakeSlide.retracted);


                        inExtendState = inExtendState.retracted;
                        break;


            }
            case extended:
                break;
        }
    }



    public void setPIDPowerTest(){
     setPIDMotorPower(100);
    }

    public void setPower(){
        intakeSlideMotor.setPower(1);
    }

    public double getPosition(){
        double position = intakeSlideMotor.getCurrentPosition();
        return position;
    }

}
