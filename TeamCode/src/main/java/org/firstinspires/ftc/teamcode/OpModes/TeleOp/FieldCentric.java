package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadKeys.*;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ExtensionState;
import org.firstinspires.ftc.teamcode.Commands.IntakeExtendState;
import org.firstinspires.ftc.teamcode.Commands.OuttakeExtendState;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSlide;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSlide;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp

public class FieldCentric extends OpMode {
    private ElapsedTime runTime;
    private ExtensionState extendState;
    //private IntakeExtendState intakeExtendState;
    //private OuttakeExtendState outtakeExtendState;
    private GamepadEx driver, operator;
    private Mecanum driveTrain;
    //private Robot bot;
    private HardwareMap hardwareMap;

    public void init()
    {
        runTime = new ElapsedTime();
        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);
        driveTrain = new Mecanum(hardwareMap);
        //bot = new Robot(hardwareMap, telemetry);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        //bot.resetEncoder();
        //bot.setZeroBehavior();

        telemetry.addLine("It's goobin time");
        telemetry.update();
    }

    public void loop()
    {
        driver.readButtons();
        //operator.readButtons();
        driveTrain.drive(driver);
        driveTrain.setMotorPower();

        //bot.initPID();

        // ---------------------------- DRIVER CODE ---------------------------- //
        //if(driver.wasJustPressed(Button.LEFT_BUMPER))
        //{
        //    driveTrain.setMotorSlowDownPower();
        //}
        //if(driver.wasJustPressed(Button.Y))
        //{
        //    driveTrain.resetIMU();
        //}
        // ---------------------------- OPERATOR CODE ---------------------------- //
        //if(operator.wasJustPressed(Button.DPAD_UP))
        //{
        //  bot.setPosition(extendState.HIGHOUT, intakeExtendState.STATION,outtakeExtendState.EXTENDING);
        //}
        //if(operator.wasJustPressed(Button.DPAD_DOWN))
        //{
        //    bot.setPosition(extendState.GROUND, intakeExtendState.STATION,outtakeExtendState.EXTENDING);
        //}
        //if(operator.wasJustPressed(Button.DPAD_LEFT))
        //{

        //}
        //if(operator.wasJustPressed(Button.DPAD_RIGHT))
        //{

        //}
        //if(operator.wasJustPressed(Button.Y))
       //{

        //}
        //if(operator.wasJustPressed(Button.X))
        //{

        //}
        //if(operator.wasJustPressed(Button.B))
        //{

        //}
        //if(operator.wasJustPressed(Button.A))
        //{

        //}
        //if(operator.wasJustPressed(Button.LEFT_BUMPER))
        //{

        //}
        //if(operator.wasJustPressed(Button.RIGHT_BUMPER))
        //{

        //}
        //if(operator.gamepad.left_trigger > 0.1)
        //{

        //}
        //if(operator.gamepad.right_trigger > 0.1)
        //{

        //}
    }
}