package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Intake
{
    private IntakeSlide intakeSlide;
    private Claw claw;
    private Robot bot;
    private ActiveIntake activeIntake;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry)
    {
        intakeSlide = new IntakeSlide(hardwareMap);
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
                break;
            case EXTEND:
                activeIntake.activateActiveMotor();
                // if(claw.getPosition())
                break;
        }
    }
}