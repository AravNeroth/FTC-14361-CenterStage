package org.firstinspires.ftc.teamcode.OpMode;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.*;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

public class TeleOp extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;

    private Mecanum drive;
    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        Mecanum drive = new Mecanum(hardwareMap);

        telemetry.addLine("It's gorbin time");
        telemetry.addLine("Time taken: " + getRuntime()+ " seconds.");
        telemetry.update();
    }

    public void loop()
    {
        driver.readButtons();
        operator.readButtons();

        if(driver.wasJustPressed(Button.LEFT_BUMPER))
        {
            drive.setMotorSlowDownPower();
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