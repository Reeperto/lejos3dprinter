package ev3dprinter;

import ev3dprinter.interfaces.PrintHead;
import ev3dprinter.interfaces.XMotor;
import ev3dprinter.interfaces.YMotor;
import ev3dprinter.interfaces.ZMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Printer {

    static final int motorSpeed = 360;

    // Ratio of degrees per millimeter for each motor
    static final float xMillimeterRatio = 14.0625f;
    static final float yMillimeterRatio = 5f;
    static final float zMillimeterRatio = 240f;

    static final XMotor xAxisMotor = new XMotor(MotorPort.A, SensorPort.S1, xMillimeterRatio, motorSpeed, false);
    static final YMotor yAxisMotor = new YMotor(MotorPort.B, SensorPort.S2, yMillimeterRatio, motorSpeed, false);
    static final ZMotor zAxisMotor = new ZMotor(MotorPort.C, SensorPort.S3, zMillimeterRatio, motorSpeed, false);

    static PrintHead printHead = new PrintHead(xAxisMotor, yAxisMotor, zAxisMotor);

    public static void main(String[] args) {

        xAxisMotor.Calibrate();
        yAxisMotor.Calibrate();
        zAxisMotor.Calibrate();

    }


}
