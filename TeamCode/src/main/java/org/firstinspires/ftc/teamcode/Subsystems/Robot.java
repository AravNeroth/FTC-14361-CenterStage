package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Robot
{
    public Claw claw;
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    private State state;
    public Arm arm;
    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        arm = new Arm(hardwareMap);
        claw = new Claw(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);
        state = state.LOWIN;
    }

    public void setPosition(State state) {
        claw.setPosition(state);
        arm.setPosition(state);
        outtakeSlide.setPosition(state);
        intakeSlide.setPosition(state);
    }

    public void resetEncoder()
    {
        outtakeSlide.resetEncoder();
        intakeSlide.resetEncoder();
    }

    public void setZeroBehavior()
    {
        outtakeSlide.setZeroBehavior();
        intakeSlide.setZeroBehavior();
    }
    public void initPID()
    {
        outtakeSlide.initPID();
        intakeSlide.initPID();
    }

    public State getState()
    {
        return state;
    }
}
