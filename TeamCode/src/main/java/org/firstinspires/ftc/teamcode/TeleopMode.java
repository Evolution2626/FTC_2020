package org.firstinspires.ftc.teamcode;  //Folder

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleop_Test")   //Mode

public class TeleopMode extends LinearOpMode {  // Basic code here

   // private Gyroscope imu;
    private DcMotor frontRight;     // Creating all object
    private DcMotor frontLeft;
    private DcMotor rearRight;
    private DcMotor rearLeft;
    private Servo pince;


    public void driveTank(double left, double right){    //Fonction for an easy tank drive(taken from FRC )
        frontRight.setPower(right);
        frontLeft.setPower(left);
        rearRight.setPower(right);
        rearLeft.setPower(left);
    }

    public static double map(double x, double in_min, double in_max, double out_min, double out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    @Override
    public void runOpMode() {   //run while init

       // imu = hardwareMap.get(Gyroscope.class, "imu");       // Defining which object is what on the robot
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rearRight = hardwareMap.dcMotor.get("rearRight");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        pince = hardwareMap.get(Servo.class, "servo");


        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);   // setting the motor direction
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");  // print in console
        telemetry.update();

        waitForStart();  // After run when start

        telemetry.addData("Status", "Running");   // print in console
        telemetry.update();

        if(opModeIsActive()) {

            while (opModeIsActive()) {

                double left_axisY = -this.gamepad1.left_stick_y;     //define the joystick variable
                double right_axisY = -this.gamepad1.right_stick_y;
                double right_trigger = this.gamepad1.right_trigger;
                double left_trigger = this.gamepad1.left_trigger;

                double servoPosition = map(right_trigger - left_trigger, -1, 1, 500, 2500) ;

                driveTank(left_axisY, right_axisY);    //make the motor move


                pince.setPosition(servoPosition);


                telemetry.addData("Left", left_axisY);
                telemetry.addData("Right", right_axisY);    // print in the console
                //   telemetry.addData("Gyro", imu);
                telemetry.update();
            }
        }
    }
}
