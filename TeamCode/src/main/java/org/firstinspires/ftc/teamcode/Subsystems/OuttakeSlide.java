package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.IntakeState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeState;
import org.firstinspires.ftc.teamcode.Commands.State;
import org.firstinspires.ftc.teamcode.util.RobotConstants;

public class OuttakeSlide
{
    private DcMotorEx leftOuttakeMotor, rightOuttakeMotor;
    private PIDController outtakePID;
    private int target, outtakeMotors;
    private double P, I, D, ticks_in_degrees;

    public OuttakeSlide(HardwareMap hardwareMap)
    {
        leftOuttakeMotor = hardwareMap.get(DcMotorEx.class, "leftIntakeMotor");
        rightOuttakeMotor = hardwareMap.get(DcMotorEx.class, "rightIntakeMotor");

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
        leftOuttakeMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        rightOuttakeMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
    }

    public void setZeroBehavior()
    {
        leftOuttakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void initPID() {
        outtakePID = new PIDController(P, I, D);

        outtakePID.setPID(P, I, D);
    }

    public void setPIDMotorPower(int target)
    {
        outtakeMotors = (leftOuttakeMotor.getCurrentPosition() + rightOuttakeMotor.getCurrentPosition())/2;

        int motorPosition = outtakeMotors;

        double PID = outtakePID.calculate(motorPosition, target);

        double power = PID;

        leftOuttakeMotor.setPower(power);
        rightOuttakeMotor.setPower(power);
    }

    public double getPosition()
    {
        return leftOuttakeMotor.getCurrentPosition() & rightOuttakeMotor.getCurrentPosition();
    }

    public void setPosition(State state, OuttakeState extendState)
    {
        switch(extendState)
        {
            case STATION:
                setPIDMotorPower(0);
                break;
            case EXTENDING:
                if(state.equals(state.HIGHOUT))
                {
                    setPIDMotorPower(RobotConstants.OuttakeSlide.HIGH);
                }
                else if(state.equals(state.MEDIUMOUT))
                {
                    setPIDMotorPower(RobotConstants.OuttakeSlide.MEDIUM);
                }
                else if(state.equals(state.LOWOUT))
                {
                    setPIDMotorPower(RobotConstants.OuttakeSlide.LOW);
                }
                break;
            case EXTEND:
                break;

        }
    }
}