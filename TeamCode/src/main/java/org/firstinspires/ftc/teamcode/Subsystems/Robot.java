package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeState;
import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.Commands.WristState;

public class Robot {
    public Claw claw;
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    private State state;
    private OuttakeState outtakeState;
    private IntakeState intakeState;
    private WristState wristState;
    private ClawState clawState;
    public Arm arm;
    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        arm = new Arm(hardwareMap);
        claw = new Claw(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);
        clawState = ClawState.CLOSEDCLAW;
    }

    public void setPosition(State state, IntakeState inExtendState, OuttakeState outtakeExtendState) {
        claw.setPosition(state);
        arm.setPosition(state);
        outtakeSlide.setPosition(state, outtakeExtendState);
        intakeSlide.setPosition(state, inExtendState);
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

    public void setIntakeState(IntakeState intakeState)
    {
        this.intakeState = intakeState;
    }

    public IntakeState getIntakeState()
    {
        return intakeState;
    }

    public void setOuttakeState(OuttakeState outtakeState)
    {
        this.outtakeState = outtakeState;
    }

    public OuttakeState getOuttakeState()
    {
        return outtakeState;
    }

    public void setWristState(WristState wristState)
    {
        this.wristState = wristState;
    }

    public WristState getWristState()
    {
        return wristState;
    }

    public void setClawState(ClawState clawState)
    {
        this.clawState = clawState;
    }

    public ClawState getClawState()
    {
        return getClawState();
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public State getState()
    {
        return state;
    }

}
