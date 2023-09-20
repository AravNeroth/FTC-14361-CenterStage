package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.*;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Mecanum driveTrain;
    private Robot robot;

    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        driveTrain = new Mecanum(hardwareMap);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        robot.resetEncoder();
        robot.setZeroBehavior();

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime()+ " seconds.");
        telemetry.update();
    }

    public void loop()
    {
        driver.readButtons();
        operator.readButtons();
        driveTrain.drive(driver);
        driveTrain.setMotorPower();

        robot.initPID();


        if(driver.wasJustPressed(Button.LEFT_BUMPER))
        {
            driveTrain.setMotorSlowDownPower();
        }

    }

    public void stop()
    {
        //dawg idk how to stop the damn robot
        telemetry.addLine("Robot Stopped.");
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.update();
    }
}