package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.IntakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class IntakeSlide
{
    private PIDController intakePID;
    private DcMotorEx intakeSlideMotor;
    private int target;
    private double P, I, D, F, ticks_in_degrees;


    public IntakeSlide(HardwareMap hardwareMap)
    {
        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");
        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        P = RobotConstants.IntakeSlide.P;
        I = RobotConstants.IntakeSlide.I;
        D = RobotConstants.IntakeSlide.D;
        F = RobotConstants.IntakeSlide.F;

        intakePID = new PIDController(P, I, D);

        intakePID.setPID(P, I, D);

        target = 0;

        ticks_in_degrees = 0.0;

    }

    public void resetEncoder()
    {
        intakeSlideMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
    }

    public void setPIDMotorPower(int target)
    {
        int motorPosition = intakeSlideMotor.getCurrentPosition();
        double PID = intakePID.calculate(motorPosition, target);
        double FF = Math.cos(Math.toRadians(target / ticks_in_degrees)) * F;

        double power = PID * FF;

        intakeSlideMotor.setPower(power);
    }

    public double getPosition()
    {
        return intakeSlideMotor.getCurrentPosition();
    }

    public void setPosition(ExtensionState extendState, IntakeExtendState inExtendState)
    {
        switch(inExtendState)
        {
            case STATION:
                setPIDMotorPower(RobotConstants.IntakeSlide.STATION);
                break;
            case EXTENDING:
                if(extendState.equals(extendState.HIGHIN))
                {
                    setPIDMotorPower(RobotConstants.IntakeSlide.HIGH);
                }
                else if(extendState.equals(extendState.MEDIUMIN))
                {
                    setPIDMotorPower(RobotConstants.IntakeSlide.MEDIUM);
                }
                break;
        }
    }
}
