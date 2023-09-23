package org.firstinspires.ftc.teamcode.OpModes.TeleOp.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Webcam;
import org.firstinspires.ftc.teamcode.utilities.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
@Autonomous
public class AutonCloseBlue extends LinearOpMode {
    Robot bot;
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

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewID", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewID);
        bot = new Robot(hardwareMap, telemetry);

        pipeline = new AprilTagDetectionPipeline(tagsize,fx,fy,cx,cy);
        camera.setPipeline(pipeline);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            @Override
            public void onOpened() {
                camera.startStreaming(WIDTH, HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });




        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = pipeline.getLatestDetections();
            if (currentDetections.size() != 0) {
                boolean tagFound = false;
                for(AprilTagDetection tag : currentDetections) {
                    if(tag.id == BLUELEFT || tag.id == BLUEMID || tag.id == BLUERIGHT || tag.id == REDLEFT || tag.id== REDMID || tag.id ==REDRIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                  tagToTelemetry(tagOfInterest);
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }
            telemetry.update();
            sleep(20);
        }

        /* Update the telemetry */
        if(tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :( L ");
            telemetry.update();
        }

        if(tagOfInterest.id == BLUELEFT){
            telemetry.addLine("Blue Left");
        }
        else if(tagOfInterest.id == BLUEMID){
            telemetry.addLine("Blue Mid");
        }
        else if(tagOfInterest.id == BLUERIGHT){
            telemetry.addLine("Blue Right");
        }


    }
    void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.y)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.x)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.z)));
    }
}
