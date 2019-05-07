package org.firstinspires.ftc.teamcode;
import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.android.dx.util.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
import java.util.ArrayList;

@TeleOp(name = "IamaMeatPopsicle")
public class teleOp extends Library {
    ElapsedTime time = new ElapsedTime();

    FileOutputStream fileOutputStream;
    {
        try {
            fileOutputStream = new FileOutputStream(new File("./text.txt"));
        } catch (FileNotFoundException e) {
            telemetry.addData(e.getMessage(),"");
        }
    }
//    if(OutputStreamWriter != null){
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//    }

    public void init() {
        hardwareInit();
    }

    public void loop() {
        float linear = gamepad1.left_stick_y;
        float side = gamepad1.left_stick_x;
        float rotation = gamepad1.right_stick_x;
        boolean aPressed = gamepad1.b;
        int currentLineIndex = 0;
        //defining the stuff. linear = straight, rotation = turning, side = skating.
        //Linear - rotation will compensate one side to allow the other side to overrate
        omni(linear, rotation, side);
        if(aPressed){
            ArrayList<String> keyframes = LineReader.readFile("./test.txt");
            telemetry.addData("List Length:", keyframes.size());

            //telemetry.addData("Line" + currentLineIndex, keyframes.get(currentLineIndex));
            //currentLineIndex++;
        }
        telemetry.addData("Current Time:",time.milliseconds());
        if(time.milliseconds() % 20 == 0) {
            telemetry.addData("Adding data...","x");
            try {
                OutputStream OutputStream = new FileOutputStream("./test.txt");
                OutputStreamWriter writer = new OutputStreamWriter(OutputStream);
                String linearF = Float.toString(linear);
                String sideF = Float.toString(side);
                String rotationF = Float.toString(rotation);
                //Every SAMPLES_PER_SECOND
                //Save floatArray values in a file in the format of "l|s|r"
                writer.write(linearF + "|" + sideF + "|" + rotationF);
                telemetry.addData("Success:", "yes");
            } catch(Exception ex) {
                telemetry.addData("Exception:", ex);
            }
        }

    }
}