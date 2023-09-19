package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;

public class outtakeSlides {
    private DcMotorEx outtakeSlides;
    private int HIGHOUT = 0, MEDIUMOUT = 0, LOWOUT = 0, HIGHIN = 0, MEDIUMIN = 0, LOWIN=0;

    public outtakeSlides(HardwareMap hardwareMap){
        outtakeSlides = hardwareMap.get(DcMotorEx.class,"outtakeSlides");
    }

    public void setOuttakeSlidesPosition(State state){
        switch(state){
            case HIGHOUT:
                outtakeSlides.setTargetPosition(HIGHOUT);
                break;
            case MEDIUMOUT:
                outtakeSlides.setTargetPosition(MEDIUMOUT);
                break;
            case LOWIN:
                outtakeSlides.setTargetPosition(LOWIN);
                break;
            case HIGHIN:
            case MEDIUMIN:
                case LOWOUT:
        }

    }

    public double getOuttakeSlidesPosition(){
        return outtakeSlides.getCurrentPosition();
    }

}
