package org.firstinspires.ftc.teamcode;  //Folder

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // FTC library
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp(name = "Teleop_Test")   //Mode

public class TeleopMode extends LinearOpMode {  // Basic code here

    private Gyroscope imu;
    private DcMotor frontRight;     // Creating all object
    private DcMotor frontLeft;
    private DcMotor rearRight;
    private DcMotor rearLeft;


    public void driveTank(double left, double right){    //Fonction for an easy tank drive(taken from FRC )
        frontRight.setPower(right);
        frontLeft.setPower(left);
        rearRight.setPower(right);
        rearLeft.setPower(left);
    }


    @Override
    public void runOpMode() {   //run while init

        imu = hardwareMap.get(Gyroscope.class, "imu");       // Defining which object is what on the robot
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        rearRight = hardwareMap.get(DcMotor.class, "rearLeft");

        frontRight.setDirection(DcMotor.Direction.FORWARD);   // setting the motor direction
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");  // print in console
        telemetry.update();

        waitForStart();  // After run when start

        telemetry.addData("Status", "Running");   // print in console
        telemetry.update();

        while (opModeIsActive()) {

            double left_axisY = -this.gamepad1.left_stick_y;     //define the joystick variable
            double right_axisY = -this.gamepad1.right_stick_y;

            driveTank(left_axisY, right_axisY);    //make the motor move

            telemetry.addData("Left", left_axisY);
            telemetry.addData("Right", right_axisY);    // print in the console
            telemetry.addData("Gyro", imu);
            telemetry.update();
        }
    }
}
