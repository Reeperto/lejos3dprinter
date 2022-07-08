package ev3dprinter;

import ev3dprinter.interfaces.EMotor;
import ev3dprinter.interfaces.XMotor;
import ev3dprinter.interfaces.YMotor;
import ev3dprinter.interfaces.ZMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Printer {

    static final int mainSpeed = 360;

    static final float xMillimeterRatio = 5f;
    static final float yMillimeterRatio = 14.0625f;
    static final float zMillimeterRatio = 240f;
    static final int xSpeed = Math.round(0.5f * mainSpeed);
    static final XMotor xAxisMotor = new XMotor(MotorPort.A, SensorPort.S1, xMillimeterRatio, xSpeed, false, false);
    static final int ySpeed = mainSpeed;
    static final YMotor yAxisMotor = new YMotor(MotorPort.B, SensorPort.S2, yMillimeterRatio, ySpeed, false, true);
    static final int zSpeed = 2 * mainSpeed;
    static final ZMotor zAxisMotor = new ZMotor(MotorPort.C, SensorPort.S3, zMillimeterRatio, zSpeed, true, false);
    // Ratio of degrees per millimeter for each motor
    static final EMotor eAxisMotor = new EMotor(MotorPort.D, (float) mainSpeed);

    public static void main(String[] args) {

        xAxisMotor.calibrate();
        yAxisMotor.calibrate();
        zAxisMotor.calibrate();

    }


}
