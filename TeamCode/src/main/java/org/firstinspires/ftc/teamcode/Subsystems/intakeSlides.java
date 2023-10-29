package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.utilities.robotConstants;

import org.firstinspires.ftc.teamcode.Commands.State;


public class intakeSlides {
    private DcMotorEx intakeSlides;
    private State state, intakeState;
    private int target;
    private PIDController intakePID;
    private double P, I, D, ticks_in_degrees;

    private int  HIGHIN = 0, MEDIUMIN = 0, LOWIN=0, EXTENDING = 0, EXTENDEDSHORT, EXTENDEDMID = 0, EXTENDEDLONG = 0;
    public intakeSlides(HardwareMap hardwareMap){
        intakeSlides = hardwareMap.get(DcMotorEx.class, "intakeSlides");

        P = robotConstants.intakeSlides.P;
        I = robotConstants.intakeSlides.I;
        D = robotConstants.intakeSlides.D;

        target = 0;
        //Will change depending on the target
        ticks_in_degrees = 0.0;
        //will change depending on the motor used

      //  intakeSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void resetEncoder(){

        intakeSlides.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void setZeroBehavior(){

        intakeSlides.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
    public State getIntakeSlidesState(){
        return state;
    }

    public void initPID(){
        intakePID = new PIDController(P,I,D);

        intakePID.setPID(P,I,D);
    }

    public void setPIDMotorPower(int target){
        int motorPosition = intakeSlides.getCurrentPosition();

        double PID = intakePID.calculate(motorPosition, target);
    }
    public void setState(State state, IntakeState intakeState){
        switch(state){
            case LOWIN:
                intakeSlides.setTargetPosition(LOWIN);
                break;
            case MEDIUMIN:
                intakeSlides.setTargetPosition(MEDIUMIN);
                break;

        }
    }




}
