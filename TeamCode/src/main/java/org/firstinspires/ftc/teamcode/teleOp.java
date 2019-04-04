package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "teleOp")
public class teleOp extends Library {

    public void init() {
        hardwareInit();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        omni(linear, rotation, side);

        //defining the stuff. linear = straight, rotation = turning, side = skating.
        //Linear - rotation will compensate one side to allow the other side to overrotate

    }
}
