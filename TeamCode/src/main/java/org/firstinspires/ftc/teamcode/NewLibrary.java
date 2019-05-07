package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.*;

public abstract class NewLibrary extends OpMode {
    // Declare Hardware Devices
    public static DcMotor frontLeft, frontRight, backLeft, backRight;

    // Declare initializing method
    public void hardwareInit() {
        frontLeft = hardwareMap.dcMotor.get("m0");
        frontRight = hardwareMap.dcMotor.get("m1");
//        backLeft = hardwareMap.dcMotor.get("m2");
//        backRight = hardwareMap.dcMotor.get("m3");
    }
    // Declare other helper methods

    // Constants
    static float attenuationfactor;
    static double initial_position = 0;
    static double moveRate = .005;
    static boolean servosMoving = false;
    static int SAMPLES_PER_SECOND = 10;
    public static void drive(float lf, float rf) {
		/*
		Provides basic motor power to all 4 motors.
		@param: float lf, rf, lb & rb all -1<x<1
		@return: void
		 */
        frontLeft.setPower(-lf);
        frontRight.setPower(rf);
//        backLeft.setPower(-lb);
//        backRight.setPower(rb);

        // power settings for motors.
    }
    public static float maxValue(float array[]) {
        float max = 0f;
        for (float i: array){
            if(i>max){ max = i; }
        }
        return max;
    }

    //commented just incase 2 motors breaks this
//    public static void omni(float l, float r, float s) {
//    /*
//    Omni-driving function.
//    @param: l, linear component, r, rotational component, and s, horizontal component
//     */
//        float[] forwardMultiplier = {1f, 1f, 1f, 1f};
//        float[] rotationalMultiplier = {-1f, 1f, -1f, 1f};
//        float[] horizontalMultiplier = {-1f, 1f, 1f, -1f};
//
//        float[] forwardComponent = new float[4];
//        float[] rotationalComponent = new float[4];
//        float[] eastwestComponent = new float[4];
//
//        for (int i = 0; i < 4; i++) {
//            forwardComponent[i] = forwardMultiplier[i] * l;
//            rotationalComponent[i] = rotationalMultiplier[i] * r;
//            eastwestComponent[i] = horizontalMultiplier[i] * s;
//        }
//
//        float[] sums = new float[4];
//        for (int i = 0; i < 4; i++) {
//            sums[i] += forwardComponent[i] + rotationalComponent[i] + eastwestComponent[i];
//        }
//
//        float highest = maxValue(sums);
//
//        if (Math.abs(highest) > 1) {
//            attenuationfactor = highest;
//        } else {
//            attenuationfactor = 1f;
//        }
//
//        for (int i = 0; i < 4; i++) {
//            sums[i] /= attenuationfactor;
//        }
//
//        drive(sums[0], sums[1], sums[2], sums[3]);
//
//    } /* OMNI IS WHAT WE USE, DRIVE IS FOR OMNI TO USE*/
}