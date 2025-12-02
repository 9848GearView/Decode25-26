package org.firstinspires.ftc.teamcode;
//
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mech.ColorSensor;
import org.firstinspires.ftc.teamcode.mech.IntakeV175;
import org.firstinspires.ftc.teamcode.mech.LLMech;
import org.firstinspires.ftc.teamcode.mech.MecanumDrive;
//import org.firstinspires.ftc.teamcode.mech.LLMech;

//this is a new version for the new CIntake system;
//didnt want to deal with the mess of forgetting how i did something

@TeleOp(name="DecodeV175TeleOp", group="Iterative OpMode")
public class DecodeV175TeleOp extends OpMode {
    MecanumDrive chassis = null;
    IntakeV175 cannon = null;
    ColorSensor colSens = null;
    LLMech limelight = null;
    //private Gamepad currGP2 = new Gamepad();
    //private Gamepad prevGP2 = new Gamepad();


    ColorSensor.detectedColor detectedColor;



    private boolean dPadUpPressed;
    private boolean dPadDownPressed;
    private boolean oldDPadUpPressed;
    private boolean oldDPadDownPressed;
    private boolean rStickPressed;
    private boolean oldrStickPressed;

    private boolean aPressed;

    private boolean driveAPressed;

    private double leftX;
    private double leftY;
    private double rightX;


    @Override
    public void init(){
        chassis = new MecanumDrive(hardwareMap);
        cannon = new IntakeV175(hardwareMap);
        colSens = new ColorSensor(hardwareMap);
        limelight = new LLMech(hardwareMap);
        //chassis.resetRobotAngle();//should be commented out to run teleOp after Auto & keep angle
    }

    @Override
    public void loop(){
        dPadUpPressed = gamepad2.dpad_up;

        //controlled turning
        rStickPressed = gamepad1.right_stick_button;
        oldrStickPressed = rStickPressed;

        dPadDownPressed = gamepad2.dpad_down;
        oldDPadDownPressed = dPadDownPressed;
        oldDPadUpPressed = dPadUpPressed;

        aPressed = gamepad2.a;
        driveAPressed = gamepad1.a;

        leftX = gamepad1.left_stick_x;
        leftY = gamepad1.left_stick_y;
        rightX = gamepad1.right_stick_x;

        //CIntake wheel begin
        if (aPressed){
           cannon.CIntake(1);
           //oldAPressed++;
        }
        if (aPressed /*&& oldAPressed == 1*/){ //this should be the off

            //oldAPressed--;
            //cannon.gate(0); //needs servo
        }
        if (gamepad2.b){
            cannon.CIntake(0); //mooved here temp

            //cannon.gate(.5); //needs servo
        }
        if (gamepad2.x) {
            cannon.launch(1);//.13
        }
        if (gamepad2.y) {
            cannon.stopLaunch();
        }
        if (gamepad2.dpad_down) {
            cannon.HIntake(-.5);
        }
        if (gamepad2.dpad_up) {
            cannon.HIntake(.5);
        }
        if (gamepad2.right_bumper){
            cannon.gate(1);
        }
        /*if(gamepad2.dpad_up){
            cannon.HIntake(-.1);
        }


       /* if(gamepad2.dpad_right){
            cannon.launchSmarter(true);
        }
        */
        // (FI) color sensor begin
        detectedColor = colSens.getDetectedColor(telemetry);
        limelight.getLlResult();
        // color sensor end

        //Drive mode code :D
        if (gamepad1.right_bumper){
            chassis.slowTurn(0.1);
        } else if(gamepad1.left_bumper){
            chassis.slowTurn(-0.1);
        } else if (gamepad1.a) {
            chassis.drive(-leftY, leftX, limelight.botCorrection()); //steer_adj, will need to hold a
        } else {
            chassis.drive(-leftY, leftX, rightX); //normal drive
        }

        //chassis.setLightColor();

        //cannon.launchSmarter(gamepad2.right_bumper);

        colSens.getDetectedColor(telemetry);
        limelight.updateLLTelemetry(telemetry);
        telemetry.addData("Methinks the color is", detectedColor);
        telemetry.addData("stick pressed", rStickPressed);
        telemetry.addData("did it stick", oldrStickPressed);
        telemetry.addData("Launcher Velocity", cannon.getLauncherVelocity());
        telemetry.addData("Launch Angle", chassis.getLaunchAngle());
        telemetry.addData("IMU", chassis.getRobotAngle());
        //telemetry.addData();
        telemetry.update();
    }
//

}
