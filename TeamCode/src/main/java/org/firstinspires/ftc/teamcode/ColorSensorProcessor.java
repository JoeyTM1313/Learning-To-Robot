/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

public class ColorSensorProcessor {

    /** The colorSensor field will contain a reference to our color sensor hardware object */
    NormalizedColorSensor colorSensor;
    int colorReturned;

    /**
     *
     * @param _colorSensor Reference to the hardwar color sensor that we want to process.
     */
    public ColorSensorProcessor (NormalizedColorSensor _colorSensor) {

        this.colorReturned = 0;

        this.colorSensor = _colorSensor;

        this.updateValues(); // actually execute the sampling code; start up the loop
    }

    //return our color integer.
    public int getColorInt () {
        return this.colorReturned;
    }

    public String getHexCode() {
        return "#" + Integer.toHexString(Color.red(this.colorReturned)) + Integer.toHexString(Color.green(this.colorReturned)) + Integer.toHexString(Color.blue(this.colorReturned));
    }
    public String getHexCode(boolean includeAlpha, boolean includeHashtag) {
        //by default, the alpha section is blank. I had it in an `else` block, but it created an error when reading the variable
        String alphaSection = "";
        if(includeAlpha) {
            alphaSection = Integer.toHexString(Color.alpha(this.colorReturned));
        }
        //See above comment for
        String hashtagSection = "";
        if(includeHashtag) {
            hashtagSection = "#";
        }
        return hashtagSection + Integer.toHexString(Color.red(this.colorReturned)) + Integer.toHexString(Color.green(this.colorReturned)) + alphaSection;
    }

    /**
     * Tests whether the current hex code is `x` percent from a given hex value.
     *
     *
     * @param percent The range (by percent of color change) that'll pass the test. In Decimal Format-- e.g.
     * @param hexCode The hex code which you want to test nearness to.
     *
     *
     * @return Bool representing whether your test pasts or not.
     */
    public boolean isInRangeFrom(float percent, String hexCode) {
        //snip off the hashtag if it's included
        if(hexCode.length() == 7) hexCode = hexCode.substring(1);

        //if the percent is bigger than 1, it should be converted to decimal format
        if(percent > 1 ) percent = percent / 100;

        //parse hexCode
        int targetRed = Integer.parseInt(hexCode.substring(0,2),16);
        int targetGreen = Integer.parseInt(hexCode.substring(2,4),16);
        int targetBlue = Integer.parseInt(hexCode.substring(4,6),16);

        //set values for current hexCode
        int currentRed = Color.red(this.colorReturned);
        int currentGreen = Color.green(this.colorReturned);
        int currentBlue = Color.blue(this.colorReturned);

        float redDiff = Math.abs(targetRed - currentRed) / 255;
        float greenDiff = Math.abs(targetGreen - currentGreen) / 255;
        float blueDiff = Math.abs(targetBlue - currentBlue) / 255;

        float averageDiff = (redDiff + greenDiff + blueDiff) / 3;

        return averageDiff >= percent;
    }
    /**
     * The main method, which actually processes the values and resets the output variables. CALL IN LOOP!
     *
     */
    public void updateValues() {

        // values is a reference to the hsvValues array.
        float[] hsvValues = new float[3];

        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).
        if (this.colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)this.colorSensor).enableLight(true);
        }

        // Loop until we are asked to stop
        // Read the sensor
        NormalizedRGBA colors = this.colorSensor.getNormalizedColors();

        //Convert the color to HSV
        Color.colorToHSV(colors.toColor(), hsvValues);

        //normalize the colors-- make it so brightness won't affect our readout (much)
        float max = Math.max(Math.max(colors.red, colors.green), Math.max(colors.blue, colors.alpha));
        colors.red /= max;
        colors.green /= max;
        colors.blue /= max;

        //set the colorReturned variable so it can be used by the other methods
        this.colorReturned = colors.toColor();
    }
}

