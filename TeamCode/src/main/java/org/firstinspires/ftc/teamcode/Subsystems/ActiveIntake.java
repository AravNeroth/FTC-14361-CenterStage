package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ActiveIntakeState;

public class ActiveIntake
{
    private DcMotorEx activeMotor;
    private ActiveIntakeState activeInState;

    public ActiveIntake(HardwareMap hardwareMap)
    {
        activeMotor = hardwareMap.get(DcMotorEx.class,"activeIntakeMotor");
        activeInState = ActiveIntakeState.OFF;
    }

    public void activateActiveMotor()
    {
        activeMotor.setPower(0.5);
    }

    public void deactivateActiveMotor()
    {
        activeMotor.setPower(0);
    }
}
