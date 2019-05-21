package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.ArrayList;

@TeleOp(name = "Touch Sensor Test")
public class TouchSensor_Test extends Library {

    TouchSensor touchSensor;
    public void init() {
        touchSensor = hardwareMap.touchSensor.get("touch1");
        hardwareInit();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        omni(linear, rotation, side);

        //Note the sensor values
        telemetry.addData("touch sensor pressed?", touchSensor.isPressed());
        telemetry.addData("touch sensor pressure:", touchSensor.getValue());
    }
}