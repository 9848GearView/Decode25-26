///*
// * Copyright (c) 2020 OpenFTC Team
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.mech.ColorSensor;
import org.firstinspires.ftc.teamcode.mech.Intake;
import org.firstinspires.ftc.teamcode.mech.MecanumDrive;

@Autonomous(name = "Small tri" +
        "" +
        "")

public class StAuto extends LinearOpMode {
    MecanumDrive chassis = null;
    Intake cannon = null;
    ColorSensor colSens = null;
    ElapsedTime runtime = null;


    @Override
    public void runOpMode() throws InterruptedException {

        chassis = new MecanumDrive(hardwareMap);
        cannon = new Intake(hardwareMap);
        colSens = new ColorSensor(hardwareMap);
        chassis.resetRobotAngle();

        runtime = new ElapsedTime();

        while (runtime.milliseconds() <= 10000) {

            if (gamepad2.dpad_left) {
                chassis.blueLaunchAngle();
            }
            if (gamepad2.dpad_right) {
                chassis.redLaunchAngle();
            }

            // sets light color
            //chassis.setLightColor();
            telemetry.addData("Launch Angle", chassis.getLaunchAngle());
            telemetry.addData("IMU", chassis.getRobotAngle());
            telemetry.update();
        }

        waitForStart();

        //find angle code here




        waitForStart();
        runtime.reset();

        //launch AHHHHHHH GET VELOCITY FUNNY STUFF
        while (opModeIsActive() && runtime.milliseconds() <= 1) {
            /*switch (lauchState) {
                case IDLE:
                    if ((opModeIsActive() && runtime.milliseconds() <= 1500) {
                    lauchState = LaunchState.SPIN_UP;
            }
            break;
            case SPIN_UP:
                OWLMotor.setVelocity(LAUNCHER_MAX_VELOCITY);
                OWRMotor.setVelocity(LAUNCHER_MAX_VELOCITY);
                if (OWLMotor.getVelocity()> LAUNCHER_MIN_VELOCITY && OWRMotor.getVelocity() > LAUNCHER_MIN_VELOCITY) {
                    lauchState = LaunchState.LAUNCH;
                }
                case LAUNCH:


            }*/
          /*  //shooting code that works
            while (opModeIsActive() && runtime.milliseconds() <= 1500) {
                OWRMotor.setPower(0.5);
                OWLMotor.setPower(0.5);
            }

           */
        runtime.reset();

        while (opModeIsActive() && runtime.milliseconds() <= 5000) {
            //cannon.launchSmarter(true);
            //OWRMotor.setPower(0.5);
            cannon.launch(0.125);
        }

        runtime.reset();

        while (opModeIsActive() && runtime.milliseconds() <= 5000) {
                //cannon.launchSmarter(true);
                //OWRMotor.setPower(0.5);
                cannon.intake(1);
            }
        runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() <= 5000) {
                //cannon.launchSmarter(true);
                //OWRMotor.setPower(0.5);
                cannon.launch(0);
                cannon.intake(0);
            }
            runtime.reset();
            //moveout of the funny box
        while (opModeIsActive() &&  runtime.milliseconds() < 750) {
           chassis.drive(-0.2,0,0);
        }

        chassis.drive(0,0,0);
            while (opModeIsActive() && runtime.milliseconds() <= 5000) {
                //cannon.launchSmarter(true);
                //OWRMotor.setPower(0.5);
                cannon.launch(0);
                cannon.intake(0);
            }
            runtime.reset();
            chassis.drive(0,0,0);
        }//closes method
}
} //closes class
