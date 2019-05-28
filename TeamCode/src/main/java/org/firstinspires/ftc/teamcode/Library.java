package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.*;

public abstract class Library extends OpMode {
	// Declare Hardware Devices
	public static DcMotor frontLeft, frontRight, backLeft, backRight;
	public static VoltageSensor voltSensor;

	// Declare initializing method
	public void hardwareInit() {
		frontLeft = hardwareMap.dcMotor.get("m0");
		frontRight = hardwareMap.dcMotor.get("m1");
		backLeft = hardwareMap.dcMotor.get("m2");
		backRight = hardwareMap.dcMotor.get("m3");
		voltSensor = hardwareMap.voltageSensor.get("Motor Controller 1");


		//Safety Check: run through the list of voltage sensors; if any of them are below the minimum voltage, exit.
		for (VoltageSensor voltageSensor : hardwareMap.voltageSensor) {
			if (voltageSensor.getVoltage() < WARNING_BATTERY_VOLTAGE) telemetry.addData("WARNING: ", "BATTERY LOW");
			if (voltageSensor.getVoltage() < REPLACE_BATTERY_VOLTAGE) telemetry.addData("WARNING: ", "BATTERY VERY LOW; REPLACE IMMEDIATELY");
		}
	}
	// Declare other helper methods

    // Constants
    static float attenuationfactor;
    static double initial_position = 0;
    static double moveRate = .005;
    static boolean servosMoving = false;
	static int SAMPLES_PER_SECOND = 10;
	static double REPLACE_BATTERY_VOLTAGE = 10;
	static double WARNING_BATTERY_VOLTAGE = 11;
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

		// power settings for motors.
	}
	public static void driveUntil(float distanceInCM) {
		float startPosition = frontLeft.getCurrentPosition();
		float rotations = distanceInCM / 31.4f * 360;//360 if its in degrees
		while (frontLeft.getCurrentPosition() < rotations + startPosition) {
			omni(1, 0, 0);
		}
	}
    public static float maxValue(float array[]) {
        float max = 0f;
        for (float i : array){
            if(i>max){ max = i; }
        }
        return max;
    }
    public static void battery(){
		voltSensor.getVoltage();
		System.out.println("Initial Battery: " + voltSensor);
		try {
			if (voltSensor != voltSensor){
				System.out.println("Battery Level: " + voltSensor);
			}
		} catch(Exception ex) {}
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
			sums[i] /= attenuationfactor;
		}

		drive(sums[0], sums[1], sums[2], sums[3]);

	} /* OMNI IS WHAT WE USE, DRIVE IS FOR OMNI TO USE*/
}