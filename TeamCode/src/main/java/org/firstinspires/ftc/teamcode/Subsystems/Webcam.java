package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.firstinspires.ftc.teamcode.util.AprilTagDetectionPipeline;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Webcam {

    OpenCvCamera camera;
    AprilTagDetectionPipeline pipeline;

    //Lens intrinsics
    //UNITS ARE IN PIXELS
    //Calibrated based on your own webcam
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    //TAG ID 1,2,3,4,5,6 from the 36h1ll family
    int BLUELEFT = 1, BLUEMID = 2, BLUERIGHT = 3;
    int REDLEFT = 4, REDMID = 5, REDRIGHT = 6;

    AprilTagDetection tagOfInterest = null;

    // UNITS ARE METERS
    double tagsize = 0.166;
    public static final int WIDTH = 640, HEIGHT = 360;

    static final double FEET_PER_METER = 3.28084;

    public Webcam(HardwareMap hardwareMap) {
        int cameraMonitorViewID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewID", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewID);


        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(WIDTH, HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });
    }
}