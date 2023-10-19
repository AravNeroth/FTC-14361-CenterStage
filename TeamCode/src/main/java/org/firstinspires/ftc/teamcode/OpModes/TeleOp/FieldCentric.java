package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.*;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.extensionState;
import org.firstinspires.ftc.teamcode.Commands.intakeSlidesState;
import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode
{
    private ElapsedTime runTime;
    private GamepadEx driver, operator;
    private Robot bot;



    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        bot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("It's goobin time");
        telemetry.addLine("Time taken: " + getRuntime()+ " seconds.");
        telemetry.update();
    }

    public void loop()
    {

        telemetry.addLine("Total Runtime: " + getRuntime() + " seconds.");
        telemetry.addLine("Position: " + bot.getIntakeSlideMotorPosition() + " ticks");
        telemetry.update();
        driver.readButtons();
        operator.readButtons();
        bot.drivetrain.drive(driver);
        bot.drivetrain.setMotorPower();




      if(operator.wasJustPressed(Button.DPAD_LEFT)){

            bot.setIntakeState(intakeSlidesState.extending, extensionState.HIGHIN);
        }

       if(operator.wasJustPressed(Button.DPAD_RIGHT)){

            bot.setIntakeState(intakeSlidesState.extending, extensionState.MEDIUMIN);
        }

       if(operator.wasJustPressed(Button.DPAD_DOWN)){
            bot.setIntakeState(intakeSlidesState.extending, extensionState.STATION);
        }


       if(operator.wasJustPressed(Button.LEFT_BUMPER))
        {
            bot.drivetrain.setMotorSlowDownPower();
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