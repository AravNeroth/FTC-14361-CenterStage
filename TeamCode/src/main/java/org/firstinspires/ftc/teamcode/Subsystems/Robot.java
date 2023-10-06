package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ArmState;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.IntakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.WristState;

public class Robot {
    public Claw claw;
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;
    public Mecanum drivetrain;
    private ExtensionState state;
    private OuttakeExtendState outtakeState;
    private IntakeExtendState intakeState;
    private WristState wristState;
    private ClawState clawState;
    private ArmState armState;
    private Arm arm;
    private Intake intake;
    private Outtake outtake;
    private ActiveIntake activeIntake;
    Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        drivetrain = new Mecanum(hardwareMap);
        arm = new Arm(hardwareMap);
        claw = new Claw(hardwareMap);
        activeIntake = new ActiveIntake(hardwareMap);
        outtakeSlide = new OuttakeSlide(hardwareMap);
        intakeSlide = new IntakeSlide(hardwareMap);
        intake = new Intake(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);

        activeIntake.deactivateActiveMotor();
        armState = ArmState.INTAKING;
        clawState = ClawState.CLOSEDCLAW;
    }

    public void setPosition(ExtensionState extendState, IntakeExtendState inExtendState, OuttakeExtendState outExtendState) {
        intake.setPosition(extendState, inExtendState);
        outtake.setPosition(extendState, outExtendState);
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

    public void setIntakeState(IntakeExtendState intakeState)
    {
        this.intakeState = intakeState;
    }

    public IntakeExtendState getIntakeState()
    {
        return intakeState;
    }

    public void setOuttakeState(OuttakeExtendState outtakeState)
    {
        this.outtakeState = outtakeState;
    }

    public OuttakeExtendState getOuttakeState()
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

    public void setArmState(ArmState armState)
    {
        this.armState = armState;
    }

    public ArmState getArmState()
    {
        return armState;
    }

    public void setState(ExtensionState state)
    {
        this.state = state;
    }

    public ExtensionState getState()
    {
        return state;
    }

}
