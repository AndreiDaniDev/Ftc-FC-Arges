package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.systems.DriveTrain_simple;
import org.firstinspires.ftc.teamcode.systems.SliderGlisiere;

@TeleOp(name = "TeleOP - Numai Bile", group = "Linear OpMode")
public class teleop_numa_bile extends LinearOpMode {

    DriveTrain_simple driveTrain;
    SliderGlisiere slider;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("status : ", "initialized :)");
        telemetry.update(); /// status update to driver hub ///

        driveTrain = new DriveTrain_simple(hardwareMap);
        slider = new SliderGlisiere(hardwareMap);

        waitForStart();

        if(isStopRequested()){ return; }

        for(; opModeIsActive(); ) {
            telemetry.addData("pos left_motor : ", slider.left_motor.getCurrentPosition());
            telemetry.addData("pos right_motor : ", slider.right_motor.getCurrentPosition());
            telemetry.update();

            driveTrain.updateDriveTrain(gamepad1);
            slider.handleControls(gamepad1);
        }

        return; /// i like it with this :)
    }
}
