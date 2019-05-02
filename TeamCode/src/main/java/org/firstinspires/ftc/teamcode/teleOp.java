package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

@TeleOp(name = "IamaMeatPopsicle")
public class teleOp extends Library {

    ArrayList<float[]> tripleValues = new ArrayList<>(); //array must be three long
    public void init() {
        hardwareInit();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        omni(linear, rotation, side);
        float[] floatArray = new float[3];
        floatArray[0]=linear;
        floatArray[1]=side;
        floatArray[2]=rotation;  //array of three to save values into arrayList tripleValues
        tripleValues.add(floatArray);
        telemetry.addData("l,s,r respectively ",floatArray[0]);
        telemetry.addData("l,s,r respectively ",floatArray[1]);
        telemetry.addData("l,s,r respectively ",floatArray[2]);
        //Every SAMPLES_PER_SECOND
            //Save floatArray values in a file in the format of "l s r"


        //defining the stuff. linear = straight, rotation = turning, side = skating.
        //Linear - rotation will compensate one side to allow the other side to overrate
    }
}