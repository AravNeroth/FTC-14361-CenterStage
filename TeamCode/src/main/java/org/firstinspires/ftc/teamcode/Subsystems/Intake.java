package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;

public class Intake {

    intakeSlides intakeSlides;
    activeIntake intake;

    public Intake(HardwareMap hardwareMap){
        intakeSlides = new intakeSlides(hardwareMap);
        intake = new activeIntake(hardwareMap);
    }

    public void setIntakeState(State state){
        switch (state) {
            case EXTENDING:
                break;
            case EXTENDEDSHORT:

        }

    }

    public State getIntakeSlidesState(){
        return intakeSlides.getIntakeSlidesState();
    }

    public State getActiveIntakeState(){
        return intake.getActiveIntakeState();
    }



}
