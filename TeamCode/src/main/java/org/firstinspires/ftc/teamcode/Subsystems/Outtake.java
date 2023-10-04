package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ArmState;
import org.firstinspires.ftc.teamcode.Commands.ClawState;
import org.firstinspires.ftc.teamcode.Commands.IntakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.WristState;

public class Outtake
{
    private OuttakeSlide outtakeSlide;
    private Claw claw;
    private Arm arm;
    private Robot bot;
    private ActiveIntake activeIntake;

    public Outtake(HardwareMap hardwareMap, Telemetry telemetry)
    {
        outtakeSlide = new OuttakeSlide(hardwareMap);
        claw = new Claw(hardwareMap);
        arm = new Arm(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
    }

    public void setPosition(ExtensionState extendState, OuttakeExtendState outtakeExtendState)
    {
        switch(outtakeExtendState)
        {
            case EXTENDING:
                if(bot.getWristState() != WristState.SIDEWRIST)
                {
                    claw.setWristSidePosition();
                }
                if(bot.getArmState() != ArmState.OUTTAKING)
                {
                    arm.setArmOuttaking();
                }

                outtakeSlide.setPosition(extendState, outtakeExtendState);

                if(outtakeSlide.getPosition()>=0.0)
                {
                    bot.setOuttakeState(outtakeExtendState.EXTENDED);
                }

            case EXTENDED:
                break;
            case GROUND:
                activeIntake.deactivateActiveMotor();
                outtakeSlide.setPosition(extendState, outtakeExtendState);
                claw.setWristSidePosition();
                arm.setArmIntaking();
                claw.setWristOutPosition();
                break;
        }
    }
}