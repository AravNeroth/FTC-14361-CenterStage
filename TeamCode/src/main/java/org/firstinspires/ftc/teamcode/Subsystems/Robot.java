package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Robot {
    public Mecanum drivetrain;

    public Claw claw;
    public Intake intake;


    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drivetrain = new Mecanum(hardwareMap);
        claw = new Claw(hardwareMap);
        intake= new Intake(hardwareMap);
        //outtakeSlides = new outtakeSlides(hardwareMap);

    }

    public void setPosition(State state){


        intake.setIntakeState(state);
       // intakeSlides.setIntakeSlidesPosition(state);

        //outtakeSlides.setOuttakeSlidesPosition(state);
    }

}
