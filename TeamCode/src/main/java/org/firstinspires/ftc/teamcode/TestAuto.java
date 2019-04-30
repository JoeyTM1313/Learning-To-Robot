package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.LineReader;

@Autonomous(name = "Auto")
public class TestAuto extends Library {

    ElapsedTime time = new ElapsedTime();
    ArrayList<String> keyframes = LineReader.readFile("./test.txt");
    boolean hasRead = false;
    int currentLineIndex = 0;

    public void init() {
        hardwareInit();
    }

    public void loop() {
        if(time.milliseconds() % (1000/SAMPLES_PER_SECOND) == 0 && currentLineIndex < keyframes.size()) {
            telemetry.addData("Time at which we are doing stuff:", time.milliseconds());
            telemetry.addData("Line" + currentLineIndex, keyframes.get(currentLineIndex));

            currentLineIndex++;

            String currentLine = keyframes.get(currentLineIndex);
            
            String[] lrs = currentLine.split("\\|", 3);

            omni(Float.parseFloat(lrs[0]), Float.parseFloat(lrs[2]), Float.parseFloat(lrs[1]));
        }
    }
}