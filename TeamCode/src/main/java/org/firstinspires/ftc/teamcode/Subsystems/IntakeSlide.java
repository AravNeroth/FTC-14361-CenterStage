package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class IntakeSlide
{
    private PIDController intakePID;
    private DcMotorEx intakeSlideMotor;
    private int target;
    private double P, I, D, ticks_in_degrees;


    public IntakeSlide(HardwareMap hardwareMap)
    {
        intakeSlideMotor = hardwareMap.get(DcMotorEx.class, "intakeSlideMotor");

        P = RobotConstants.IntakeSlide.P;
        I = RobotConstants.IntakeSlide.I;
        D = RobotConstants.IntakeSlide.D;

        target = 0;
        // Will change depending on the target.
        ticks_in_degrees = 0.0;
        // Will change depending on the motor used.
    }

    public void resetEncoder()
    {
        intakeSlideMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
    }

    public void setZeroBehavior()
    {
        intakeSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initPID()
    {
        intakePID = new PIDController(P, I, D);

        intakePID.setPID(P, I, D);
    }

    public void setPIDMotorPower(int target)
    {
        int motorPosition = intakeSlideMotor.getCurrentPosition();

        double PID = intakePID.calculate(motorPosition, target);

        double power = PID;

        intakeSlideMotor.setPower(power);
    }

    public double getPosition()
    {
        return intakeSlideMotor.getCurrentPosition();
    }

    public void setPosition(State state, IntakeState extendState)
    {
        switch(extendState)
        {
            case STATION:
                setPIDMotorPower(0);
                break;
            case EXTENDING:
                if(state.equals(state.HIGHIN))
                {
                    setPIDMotorPower(RobotConstants.IntakeSlide.HIGH);
                }
                else if(state.equals(state.MEDIUMIN))
                {
                    setPIDMotorPower(RobotConstants.IntakeSlide.MEDIUM);
                }
                else if(state.equals(state.LOWIN))
                {
                    setPIDMotorPower(RobotConstants.IntakeSlide.LOW);
                }
                break;
            case EXTEND:
                break;

        }
    }
}
