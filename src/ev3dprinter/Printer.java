package ev3dprinter;

import ev3dprinter.motors.PrintHead;
import ev3dprinter.motors.XMotor;
import ev3dprinter.motors.YMotor;
import ev3dprinter.motors.ZMotor;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Printer {

    // Speed variables
    static final float mainSpeed = 360;
    static final float xAxisSpeed = 0.5f * mainSpeed;
    static final float yAxisSpeed = mainSpeed;
    static final float zAxisSpeed = 2 * mainSpeed;

    // Ratio variables (Degrees per 1 mm movement)
    static final float xRatio = 5f;
    static final float yRatio = 14.0625f;
    static final float zRatio = 240f;

    // Motor variables
    static final XMotor xAxisMotor = new XMotor(MotorPort.A, SensorPort.S1, xAxisSpeed, xRatio,false);
    static final YMotor yAxisMotor = new YMotor(MotorPort.B, SensorPort.S2, yAxisSpeed, yRatio,true);
    static final ZMotor zAxisMotor = new ZMotor(MotorPort.C, SensorPort.S3, zAxisSpeed, zRatio,false);
    static final PrintHead printHead = new PrintHead(xAxisMotor, yAxisMotor, zAxisMotor);

    public static void main(String[] args) {

        printHead.calibrate();

        Button.waitForAnyPress();

    }


}
