package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Mecanum;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.intakeSlides;
import org.firstinspires.ftc.teamcode.Subsystems.outtakeSlides;

@TeleOp (name = "Drive", group = "DriveMode")
public class Teleop extends OpMode {
    private Mecanum drivetrain;
    private GamepadEx pilot, sentry;
    private Claw claw;
    private intakeSlides intakeSlides;
    private outtakeSlides outtakeSlides;



    //private PID pid;
    private int referenceAngle = 90;

    @Override
    public void init() {

        drivetrain = new Mecanum(hardwareMap);
        pilot = new GamepadEx(gamepad1);
        sentry = new GamepadEx(gamepad2);
        claw = new Claw(hardwareMap);
        intakeSlides = new intakeSlides(hardwareMap);
        outtakeSlides = new outtakeSlides(hardwareMap);


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
