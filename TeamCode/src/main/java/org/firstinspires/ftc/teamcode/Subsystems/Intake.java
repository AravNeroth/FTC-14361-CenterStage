package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.Commands.WristState;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class Intake
{
    private IntakeSlide intakeSlide;
    private Claw claw;
    private Arm arm;
    private Robot bot;
    private ActiveIntake activeIntake;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry)
    {
        intakeSlide = new IntakeSlide(hardwareMap);
        arm = new Arm(hardwareMap);
        claw = new Claw(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
        activeIntake = new ActiveIntake(hardwareMap);

    }

    public void setPosition(State state, IntakeState extendState) {
        switch(extendState)
        {
            case EXTENDING:
                intakeSlide.setPosition(state, extendState);

                if(intakeSlide.getPosition()>=0.0)
                {
                    bot.setIntakeState(extendState.EXTEND);
                }

            case EXTEND:
                activeIntake.activateActiveMotor();
                if(bot.getWristState() != WristState.outWrist && claw.getLeftHandPosition() != RobotConstants.Claw.leftOpen && claw.getRightHandPosition() != RobotConstants.Claw.rightOpen && arm.get)
                {
                    arm.setArmIntaking();
                    claw.setWristOutPosition();
                }
                else if()
                {

                }
                break;
            case CONTRACT:
                if()
                {

                }
                break;
        }
    }
}