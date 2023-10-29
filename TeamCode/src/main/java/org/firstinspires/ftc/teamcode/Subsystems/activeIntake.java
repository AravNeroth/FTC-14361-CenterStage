package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.utilities.robotConstants;

public class activeIntake {
    DcMotorEx activeIntake;
    private State state;
    private int  HIGHIN = 0, MEDIUMIN = 0, LOWIN=0, EXTENDING = 0, EXTENDEDSHORT, EXTENDEDMID = 0, EXTENDEDLONG = 0;
    public activeIntake(HardwareMap hardwareMap){
        activeIntake = hardwareMap.get(DcMotorEx.class, "activeIntake");
    }

    public State getActiveIntakeState(){
        return state;
    }
    public void setState(State state){
        switch(state){
            case LOWIN:
                activeIntake.setTargetPosition(LOWIN);
                break;
            case MEDIUMIN:
                activeIntake.setTargetPosition(MEDIUMIN);
                break;

        }
    }

}
