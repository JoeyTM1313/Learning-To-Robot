package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ColorSensorProcessor;
import java.util.ArrayList;

@Autonomous(name = "Color Sensor Test")
public class ColorSensorProcessor_Test extends Library {

    ColorSensorProcessor color;

    public void init() {
            hardwareInit();
            color = new ColorSensorProcessor(hardwareMap.get(NormalizedColorSensor.class, "sensor_color"));
        }

    public void loop() {
        //gotta update our values!
        color.updateValues();
        if(color.isInRangeFrom(0.2f, "ffffff")) {
            omni(1, 0, 0);
        } else {
            omni(0,0,0);
        }


    }
}
