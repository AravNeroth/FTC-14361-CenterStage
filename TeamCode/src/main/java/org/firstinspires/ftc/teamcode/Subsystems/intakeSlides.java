package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;


public class intakeSlides {
    private DcMotorEx intakeSlides;

    private int HIGHOUT = 0, MEDIUMOUT = 0, LOWOUT = 0, HIGHIN = 0, MEDIUMIN = 0, LOWIN=0;
    public intakeSlides(HardwareMap hardwareMap){
        intakeSlides = hardwareMap.get(DcMotorEx.class, "intakeSlides");
        intakeSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void setIntakeSlidesPosition(State state){
        switch(state){
            case HIGHOUT:
            case MEDIUMOUT:
            case LOWOUT:
            case HIGHIN:
                intakeSlides.setTargetPosition(HIGHIN);
                break;
            case MEDIUMIN:
                intakeSlides.setTargetPosition(MEDIUMIN);
                break;
            case LOWIN:
                intakeSlides.setTargetPosition(LOWIN);
                break;
        }
    }

    public double getIntakeSlidesPosition(){
        return intakeSlides.getCurrentPosition();
    }




}
