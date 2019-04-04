package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class Library extends OpMode {
    // Declare Hardware Devices
    public static DcMotor topRight, bottomRight, topLeft, bottomLeft;

    // Declare initializing method
    public void hardwareInit() {
        topRight = HardwareMap.
    }
    //Declare other helper methods


    public static void drive(float lf, float rf, float lb, float rb) {
    /*
    Provides basic motor power to all 4 motors.
    @param: float lf, rf, lb & rb all -1<x<1
    @return: void
     */
        frontLeft.setPower(-lf);
        frontRight.setPower(rf);
        backLeft.setPower(-lb);
        backRight.setPower(rb);

        //power settings for motors.
    }

    public static void omni(float l, float r, float s) {
    /*
    Omni-driving function.
    @param: l, linear component, r, rotational component, and s, horizontal component
     */
        float[] forwardMultiplier = {1f, 1f, 1f, 1f};
        float[] rotationalMultiplier = {-1f, 1f, -1f, 1f};
        float[] horizontalMultiplier = {-1f, 1f, 1f, -1f};

        float[] forwardComponent = new float[4];
        float[] rotationalComponent = new float[4];
        float[] eastwestComponent = new float[4];

        for (int i = 0; i < 4; i++) {
            forwardComponent[i] = forwardMultiplier[i] * l;
            rotationalComponent[i] = rotationalMultiplier[i] * r;
            eastwestComponent[i] = horizontalMultiplier[i] * s;
        }

        float[] sums = new float[4];
        for (int i = 0; i < 4; i++) {
            sums[i] += forwardComponent[i] + rotationalComponent[i] + eastwestComponent[i];
        }

        float highest = maxValue(sums);

        if (Math.abs(highest) > 1) {
            attenuationfactor = highest;
        } else {
            attenuationfactor = 1f;
        }

        for (int i = 0; i < 4; i++) {
            sums[i] = sums[i] / attenuationfactor;
        }

        drive(sums[0], sums[1], sums[2], sums[3]);

    } /* OMNI IS WHAT WE USE, DRIVE IS FOR OMNI TO USE*/
}