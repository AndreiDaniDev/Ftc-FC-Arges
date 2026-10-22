package org.firstinspires.ftc.teamcode.systems;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.controllers.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MathTricks;

@Configurable
public class SliderGlisiere {
    public DcMotor left_motor;
    public DcMotor right_motor;

    public int target_pos_up = -1560;
    public int target_pos_down = 400;

    private int target_pos = target_pos_down;

    public float sync_kp = 0.003f;
    public float slide_kp = 0.001f;

    public SliderGlisiere(HardwareMap hardwareMap){
        /// the right motor has an encoder ///
        left_motor = hardwareMap.dcMotor.get("glisiera_stanga");
        right_motor = hardwareMap.dcMotor.get("glisiera_dreapta");

        left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        left_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setPowers(double power){
        int left_pos = left_motor.getCurrentPosition();
        int right_pos = right_motor.getCurrentPosition();

        int delta = left_pos - right_pos;

        double left_power = power - (sync_kp * delta);
        double right_power = power + (sync_kp * delta);

        left_motor.setPower(MathTricks.doubleClamp(left_power, -1, 1));
        right_motor.setPower(MathTricks.doubleClamp(right_power, -1, 1));

        return;
    }

    public void handleControls(Gamepad gamepad){
        if(gamepad.dpad_left || gamepad.dpad_right){
            this.setPowers(0); /// zero power behaviour
            return; /// exit :) ///
        }

        if(gamepad.dpad_up){ target_pos = target_pos_up; }
        if(gamepad.dpad_down){ target_pos = target_pos_down; }

        int delta = target_pos - left_motor.getCurrentPosition();
        setPowers(MathTricks.doubleClamp(slide_kp * delta, -1, 1));

        return;
    }
}
