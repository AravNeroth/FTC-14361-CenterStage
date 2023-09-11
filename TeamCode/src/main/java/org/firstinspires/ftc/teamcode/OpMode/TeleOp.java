package org.firstinspires.ftc.teamcode.OpMode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TeleOp extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
    }

    public void loop()
    {
        telemetry.addLine("It's morbin time");
        telemetry.addLine("Time taken: " + getRuntime()+ " seconds.");
        telemetry.update();
    }

    public void stop()
    {
        //dawg idk how to stop the damn robot
        telemetry.addLine("Robot Stopped.");
        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.update();
    }
}