package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import java.util.ArrayList;

@TeleOp(name = "ColorSensorProcessor Test")
public class ColorSensorProcessor_Test extends Library {

    ColorSensorProcessor color;
    public void init() {
        color = new ColorSensorProcessor(hardwareMap.get(NormalizedColorSensor.class, "color1"));
        hardwareInit();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        omni(linear, rotation, side);

        //update color sensor's values
        color.updateValues();

        //Note the hex code
        telemetry.addData("hex code", color.getHexCode());
        telemetry.addData("white? -- 50% tolerance", color.isInRangeFrom(0.5f, "ffffff"));
        telemetry.addData("white? -- 40% tolerance", color.isInRangeFrom(0.4f, "ffffff"));
        telemetry.addData("white? -- 30% tolerance", color.isInRangeFrom(0.3f, "ffffff"));
        telemetry.addData("white? -- 25% tolerance", color.isInRangeFrom(0.25f, "ffffff"));
        telemetry.addData("white? -- 20% tolerance", color.isInRangeFrom(0.2f, "ffffff"));
        telemetry.addData("white? -- 10% tolerance", color.isInRangeFrom(0.1f, "ffffff"));
        telemetry.addData("white? -- 5% tolerance", color.isInRangeFrom(0.05f, "ffffff"));
        telemetry.addData("white? -- 1% tolerance", color.isInRangeFrom(0.01f, "ffffff"));
    }
}