package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.State;

public class Claw {
    DcMotorEx claw;
    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(DcMotorEx.class, "claw");

    }
    public static void setPosition(State state){

    }
}
