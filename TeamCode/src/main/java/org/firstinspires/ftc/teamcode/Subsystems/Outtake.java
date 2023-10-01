package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeState;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Outtake
{
    private OuttakeSlide outtakeSlide;
    private Claw claw;
    private Robot bot;
    private ActiveIntake activeIntake;

    public Outtake(HardwareMap hardwareMap, Telemetry telemetry)
    {
        outtakeSlide = new OuttakeSlide(hardwareMap);
        claw = new Claw(hardwareMap);
        bot = new Robot(hardwareMap, telemetry);
    }

    public void setPosition(State state, OuttakeState extendState) {
        switch(extendState)
        {
            case EXTENDING:
                outtakeSlide.setPosition(state, extendState);

                if(outtakeSlide.getPosition()>=0.0)
                {
                    bot.setOuttakeState(extendState.EXTEND);
                }
                break;
            case EXTEND:
                activeIntake.activateActiveMotor();
                if(claw.() )
                {

                }
                break;
        }
    }
}