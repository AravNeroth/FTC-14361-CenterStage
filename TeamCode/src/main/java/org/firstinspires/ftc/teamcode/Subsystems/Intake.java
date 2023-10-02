package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ArmState;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.IntakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.WristState;

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

    public void setPosition(ExtensionState state, IntakeExtendState extendState) {
        switch(extendState)
        {
            case EXTENDING:
                intakeSlide.setPosition(state, extendState);
                activeIntake.activateActiveMotor();

                if(intakeSlide.getPosition()>=0.0)
                {
                    bot.setIntakeState(extendState.EXTENDED);
                }

            case EXTENDED:
                if(bot.getArmState() != ArmState.INTAKING)
                {
                    arm.setArmIntaking();
                }
                if(bot.getWristState() != WristState.OUTWRIST)
                {
                    claw.setWristOutPosition();
                }
                if((bot.getClawState() != ClawState.OPENCLAW)||(bot.getClawState() != ClawState.LEFTOPEN)||(bot.getClawState() != ClawState.RIGHTOPEN))
                {
                    claw.close();
                }
                break;
            case STATION:
                activeIntake.deactivateActiveMotor();
                intakeSlide.setPosition(state, extendState);
                break;
        }
    }
}