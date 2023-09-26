package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Robot {
    private Mecanum drivetrain;

    private Claw claw;
    private intakeSlides intakeSlides;
    private outtakeSlides outtakeSlides;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drivetrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        intakeSlides = new intakeSlides(hardwareMap);
        //outtakeSlides = new outtakeSlides(hardwareMap);

    }

    public void setPosition(State state){

        intakeSlides.setIntakeSlidesPosition(state);
        //outtakeSlides.setOuttakeSlidesPosition(state);
    }

}
