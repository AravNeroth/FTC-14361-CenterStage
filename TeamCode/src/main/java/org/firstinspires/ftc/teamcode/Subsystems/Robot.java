package org.firstinspires.ftc.teamcode.Subsystems;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Commands.State;

public class Robot
{
    public Claw claw;
    public IntakeSlide intakeSlide;
    public OuttakeSlide outtakeSlide;

    public Robot()
    {

    }

    public void setPosition(State state)
    {
        claw.setPosition(state);
        intakeSlide.setPosition(state);
        outtakeSlide.setPosition(state);

    }
}
