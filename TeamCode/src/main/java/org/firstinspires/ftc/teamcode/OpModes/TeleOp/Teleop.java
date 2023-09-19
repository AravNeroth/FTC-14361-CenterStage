package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;

@TeleOp (name = "Drive", group = "DriveMode")
public class Teleop extends OpMode {
    private Mecanum drivetrain;
    private GamepadEx pilot, sentry;

    //private PID pid;
    private int referenceAngle = 90;

    @Override
    public void init() {

        drivetrain = new Mecanum(hardwareMap);
        pilot = new GamepadEx(gamepad1);
        sentry = new GamepadEx(gamepad2);

        // pid = new PID(hardwareMap);
    }

    @Override
    public void loop() {


        pilot.readButtons();
        sentry.readButtons();
        drivetrain.fieldCentric(pilot);
        drivetrain.powerMotors();


    }
}
