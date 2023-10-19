package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Commands.armState;
import org.firstinspires.ftc.teamcode.Commands.clawState;
import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.outtakeSlidesState;
import org.firstinspires.ftc.teamcode.Commands.wristState;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Robot {
    public Claw claw;
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    public Wrist wrist;
    public armState armState;
    public clawState clawState;
    public intakeSlidesState intakeSlidesState;
    public extensionState extensionState;

    public outtakeSlidesState outtakeSlidesState;
    public wristState wristState;
    public Arm arm;
    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        //arm = new Arm(hardwareMap);
        //claw = new Claw(hardwareMap);
        //wrist = new Wrist(hardwareMap);
        //  outtakeSlide = new OuttakeSlide(hardwareMap);
         intakeSlide = new IntakeSlide(hardwareMap);

    }

    public void setIntakeState(intakeSlidesState intakeState, extensionState extensionState)
    {
       intakeSlide.setPosition(extensionState,intakeState);
    }

    public void setExtensionState(extensionState extensionState)
    {
        this.extensionState = extensionState;
    }

    public intakeSlidesState getIntakeState()
    {
        return intakeSlidesState;
    }

    public extensionState getExtensionState()
    {
        return extensionState;
    }

    public double getIntakeSlideMotorPosition(){
        return intakeSlide.getPosition();
    }


    }




