package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.ArrayList;

@TeleOp(name = "rangeSensorTest")
public class IRSensor extends Library {
    private static ModernRoboticsI2cRangeSensor rangeSensor;
    private int check (){
        if (rangeSensor.getDistance(DistanceUnit.CM) <= 3){
            return 0;
        }
        return 1;
    }

    ArrayList<float[]> tripleValues = new ArrayList<>(); //array must be three long
    public void init() {
        hardwareInit();
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");
    }

    public void loop() {
        float speed = gamepad1.left_stick_y * check();
        float linear = speed;
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

        /** Adam's work*/
        telemetry.addData("cm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
        telemetry.addData("Coefficient", check());
        telemetry.update();
    }
}