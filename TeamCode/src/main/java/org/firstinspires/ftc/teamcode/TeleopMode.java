package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class TeleopMode extends LinearOpMode {

    private Gyroscope imu;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor rearRight;
    private DcMotor rearLeft;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    public void driveTank(double left, double right){
        frontRight.setPower(right);
        frontLeft.setPower(left);
        rearRight.setPower(right);
        rearLeft.setPower(left);
    }


    @Override
    public void runOpMode() {

        imu = hardwareMap.get(Gyroscope.class, "imu");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        rearRight = hardwareMap.get(DcMotor.class, "rearLeft");


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status", "Running");
        telemetry.update();
        while (opModeIsActive()) {


            double left_axisY = -this.gamepad1.left_stick_y;
            double right_axisY = -this.gamepad1.right_stick_y;

            driveTank(left_axisY, right_axisY);

            telemetry.addData("Left", left_axisY);
            telemetry.addData("Right", right_axisY);
            telemetry.update();

        }
    }
}
