package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ActiveIntake
{
    private DcMotorEx activeMotor;

    public ActiveIntake(HardwareMap hardwareMap)
    {
        activeMotor = hardwareMap.get(DcMotorEx.class,"activeIntakeMotor");
    }

    public void activateActiveMotor()
    {
        activeMotor.setPower(1);
    }

    public void deactivateActiveMotor()
    {
        activeMotor.setPower(0);
    }
}
