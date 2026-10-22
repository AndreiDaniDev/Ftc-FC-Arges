package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Configurable
public class DriveTrain_simple {
    DcMotor frontLeftMotor, frontRightMotor;
    DcMotor backLeftMotor, backRightMotor;

    ///  init function :) ///
    public DriveTrain_simple(HardwareMap hardwareMap){
        /// init motors :) ///
        frontLeftMotor = hardwareMap.dcMotor.get("FLM");
        frontRightMotor = hardwareMap.dcMotor.get("FRM");

        backRightMotor = hardwareMap.dcMotor.get("BRM");
        backLeftMotor = hardwareMap.dcMotor.get("BLM");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    };

    public void updateDriveTrain(Gamepad gamepad1){
        double xx = +gamepad1.left_stick_x * 1.1;
        double yy = -gamepad1.left_stick_y;

        double rotatexx = -gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(xx) + Math.abs(yy) + Math.abs(rotatexx), 1);
        double frontLeftPower = (yy + xx + rotatexx) / denominator;
        double backLeftPower = (yy - xx + rotatexx) / denominator;
        double frontRightPower = (yy - xx - rotatexx) / denominator;
        double backRightPower = (yy + xx - rotatexx) / denominator;

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        return;
    }
}
